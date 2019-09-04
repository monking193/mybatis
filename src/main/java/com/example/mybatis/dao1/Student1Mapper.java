package com.example.mybatis.dao1;

import com.example.mybatis.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface Student1Mapper {
    public Student getStudentById1(long id);
}
