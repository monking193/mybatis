package com.example.mybatis.dao;

import com.example.mybatis.entity.EntityInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;


@Mapper
@Component
public interface GenericMapper {

    public int insertDBEntity(EntityInfo entityInfo);

    public int insertDBEntityBatch(List<EntityInfo> entityInfo);

    public int updateDBEntityByKey(EntityInfo entityInfo);

    public int deleteDBEntityByKey(EntityInfo entityInfo);
}
