package com.example.mybatis.dao;

import com.example.mybatis.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Mapper
@Component
public interface StudentMapper {


    public Student getStudentById(long id);

    public int insertStudent(Student student);

    public  void deleteById(long id);

    public void updateStudentById(Student student);

    public List<Student> getAll();

    public HashMap<String,Object> getMyData(HashMap<String,String> param);
}
