package com.example.mybatis.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class EntityInfo implements Serializable,Cloneable{
    /**
     *
     */
    private static final long serialVersionUID = 8986658628198395689L;
    private String className;
    private String database;
    private String tableName;
    private List<EntityInfoCol> cols = new LinkedList<EntityInfoCol>();
    private EntityInfoCol keyCol;
    private Object generatedKey; //用于接收 生成的自增主键

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getDatabase() {
        return database;
    }
    public void setDatabase(String database) {
        this.database = database;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public List<EntityInfoCol> getCols() {
        return cols;
    }
    public void setCols(List<EntityInfoCol> cols) {
        this.cols = cols;
    }

    public Object getGeneratedKey() {
        return generatedKey;
    }
    public void setGeneratedKey(Object generatedKey) {
        this.generatedKey = generatedKey;
    }
    public EntityInfoCol getColByEntityColName(String name){
        for(EntityInfoCol eachCol:cols){
            if(eachCol.getEntityColName().equals(name)){
                return eachCol;
            }
        }
        return null;
    }

    public EntityInfoCol getKeyCol(){
        keyCol=null;
        for(EntityInfoCol eachCol:cols){
            if(eachCol.getIsKeyColumn()){
                keyCol = eachCol;
            }
        }
        return keyCol;
    }

    public boolean hasKeyCol(){
        return getKeyCol()!=null;
    }
    @Override
    public EntityInfo clone() throws CloneNotSupportedException {
        EntityInfo a=(EntityInfo) super.clone();
        List<EntityInfoCol> cols = new LinkedList<EntityInfoCol>();
        for(EntityInfoCol orgCol:this.cols){
            cols.add(orgCol.clone());
        }
        a.setCols(cols);
        return a;
    }
    @Override
    public String toString() {
        return "EntityInfo [className=" + className + ", database=" + database
                + ", tableName=" + tableName + ", cols=" + cols
                + ", generatedKey=" + generatedKey + "]";
    }
}
