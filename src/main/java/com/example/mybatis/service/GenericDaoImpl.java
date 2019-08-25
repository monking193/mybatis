package com.example.mybatis.service;

import annoation.DBColumn;
import annoation.DBEntity;
import annoation.KeyColumn;
import com.example.mybatis.dao.GenericMapper;
import com.example.mybatis.entity.EntityInfo;
import com.example.mybatis.entity.EntityInfoCol;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenericDaoImpl {

    private final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);

    private Map<String, EntityInfo> cache=new HashMap<String, EntityInfo>();


    private EntityInfo getInfoNoValue(Class clasz){

        String className=clasz.getName();
        //使用类名从缓存读取
        if(cache.get(className) != null){
            try {
                return cache.get(className).clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        //如果没有则检查注解
        checkDBEntity(clasz);

        EntityInfo info=new EntityInfo();
        info.setClassName(className);

        //获取表信息
        DBEntity dbEntity=(DBEntity) clasz.getAnnotation(DBEntity.class);
        if(StringUtils.isNotBlank(dbEntity.database())){
            info.setDatabase(dbEntity.database());
        }
        info.setTableName(dbEntity.value());
        //获取字段信息
        Field[] fields=clasz.getDeclaredFields();
        for(Field eachField:fields){
            EntityInfoCol curCol=new EntityInfoCol();
            //读取注解
            DBColumn dbColumn = eachField.getAnnotation(DBColumn.class);
            if(dbColumn==null) continue;
            String entityColName = eachField.getName();//class 字段名
            String dbColName =  dbColumn.value(); //db字段名
            String insertIfNull=dbColumn.insertIfNull().trim();
            String updateIfNull= dbColumn.updateIfNull().trim();
            //表字段默认与class字段相同
            if(StringUtils.isBlank(dbColName)){
                dbColName=entityColName;
            }

            curCol.setEntityColName(entityColName);
            curCol.setDbColName(dbColName);
            if(StringUtils.isNotBlank(insertIfNull)){
                curCol.setInsertIfNull(insertIfNull);
            }
            if(StringUtils.isNotBlank(updateIfNull)){
                curCol.setUpdateIfNull(updateIfNull);
            }

            KeyColumn keyColumn = eachField.getAnnotation(KeyColumn.class);
            if(keyColumn!=null){
                curCol.setIsKeyColumn(true);
                curCol.setUseGeneratedKeys(keyColumn.useGeneratedKeys());
            }else {
                curCol.setIsKeyColumn(false);
                curCol.setUseGeneratedKeys(false);
            }

            info.getCols().add(curCol);
        }
        cache.put(className, info);
        return info;
    }

    private EntityInfo getInfoWithValue(Object entity,GenericMapper generivMapper){
        EntityInfo config= getInfoNoValue(entity.getClass());
        for(EntityInfoCol colConfg:config.getCols()){
            try {
                colConfg.setValue(PropertyUtils.getProperty(entity, colConfg.getEntityColName()));
            } catch (Exception e) {
                throw new IllegalArgumentException("read property from entity error");
            }
        }
        return config;
    }



    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void checkDBEntity(Class clazz){
        DBEntity dbEntity=(DBEntity) clazz.getAnnotation(DBEntity.class);
        if(dbEntity==null)
            throw new IllegalArgumentException("not dbEntity ,plase check your beans with [DBEntity] annotation");

    }


    /* (non-Javadoc)
     * @see com.kingdee.finance.p2p.service.generic.GenericService#saveDBEntity(java.lang.Object)
     */
    public int saveDBEntity(Object entity,GenericMapper generivMapper){
        EntityInfo info=getInfoWithValue(entity,generivMapper);

        if(info.getKeyCol().getValue() == null){
            return insertDBEntity(entity,generivMapper);
        }else {
            return updateDBEntityByKey(entity,generivMapper);
        }
    }

    /* (non-Javadoc)
     * @see com.kingdee.finance.p2p.service.generic.GenericService#insertDBEntity(java.lang.Object)
     */
    public int insertDBEntity(Object entity,GenericMapper generivMapper){
        EntityInfo info=getInfoWithValue(entity,generivMapper);
        int rows= generivMapper.insertDBEntity(info);
        //使用生成的自增长主键
        Object generatedKey=info.getGeneratedKey();
        EntityInfoCol col = info.getKeyCol();
        try {
            //原主键为空才填入  否则不填
            if(col.getValue()==null){
                BeanUtils.setProperty(entity, col.getEntityColName(), generatedKey);
            }
        } catch (Exception e) {
            throw new RuntimeException("set generatedKey error",e.fillInStackTrace());
        }
        return rows;
    }

    /* (non-Javadoc)
     * @see com.kingdee.finance.p2p.service.generic.GenericService#insertDBEntityBatch(java.util.List)
     */
    public <T> int insertDBEntityBatch(List<T> entitys,GenericMapper generivMapper){
        List<EntityInfo> infos = new LinkedList<EntityInfo>();
        for(Object each:entitys){
            infos.add(getInfoWithValue(each,generivMapper));
        }
        int rows= generivMapper.insertDBEntityBatch(infos);
        return rows;
    }



    /* (non-Javadoc)
     * @see com.kingdee.finance.p2p.service.generic.GenericService#updateDBEntityByKey(java.lang.Object)
     */
    public int updateDBEntityByKey(Object entity,GenericMapper generivMapper){
        EntityInfo info=getInfoWithValue(entity,generivMapper);
        if(!info.hasKeyCol()||info.getKeyCol().getValue()==null){
            throw new IllegalArgumentException("update cannot done when key property is null");
        }
        int rows = generivMapper.updateDBEntityByKey(info);
        return rows;
    }


    /* (non-Javadoc)
     * @see com.kingdee.finance.p2p.service.generic.GenericService#deleteDBEntityByKey(java.lang.Object)
     */
    public int deleteDBEntityByKey(Object entity,GenericMapper generivMapper){
        EntityInfo info = getInfoWithValue(entity,generivMapper);
        if(!info.hasKeyCol()){
            throw new IllegalArgumentException("update cannot done when key property is null");
        }
        int rows = generivMapper.deleteDBEntityByKey(info);
        return rows;
    }

    private <T> T getFirst(List<T> list){
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }




}
