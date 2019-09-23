package com.example.mybatis.service;

import com.example.mybatis.dao.StudentMapper;
import com.example.mybatis.dao1.Student1Mapper;
import com.example.mybatis.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MultiDataSourceServiceImpl {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private Student1Mapper student1Mapper;

    public Student getDataSouce (long id){
        return studentMapper.getStudentById(id);
    }

    public Student getDataSouce1(long id) {
        return student1Mapper.getStudentById1(id);
    }

    public void testDelete(ArrayList<String> ids) {
        studentMapper.batchDelete(ids);
    }
}
