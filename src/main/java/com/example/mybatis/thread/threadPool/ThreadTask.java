package com.example.mybatis.thread.threadPool;

import com.example.mybatis.dao.StudentMapper;
import com.example.mybatis.entity.Student;

import java.util.List;

public class ThreadTask implements Runnable{

    private StudentMapper studentMapper;
    List<Student> students;

    public ThreadTask (StudentMapper studentMapper,List<Student> students) {
        this.studentMapper = studentMapper;
        this.students = students;
    }

    public void run() {
        try {
            studentMapper.batchInsert(students);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
