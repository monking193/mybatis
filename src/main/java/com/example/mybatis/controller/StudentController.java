package com.example.mybatis.controller;

import com.example.mybatis.dao.GenericMapper;
import com.example.mybatis.dao.StudentMapper;
import com.example.mybatis.entity.Student;
import com.example.mybatis.entity.StudentEntity;
import com.example.mybatis.service.GenericDaoImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GenericMapper genericMapper;

    @RequestMapping(value = "/getStudentById",method = RequestMethod.GET)
    public Student getStudentById(long id){
        return studentMapper.getStudentById(id);
    }

    @RequestMapping(value = "/deleteStudent")
    public void deleteById(long id){
        studentMapper.deleteById(id);
    }

    @RequestMapping(value = "/getInsertId")
    public String getInsertId(){
        Student student = new Student();
        student.setAddress("2343");
        student.setMoney(66666666);
        student.setName("djd");
        student.setSto_no("18850542239");
        student.setTel("18030040892");
        int num = studentMapper.insertStudent(student);
        System.out.print(num);
        return String.valueOf(student.getId());
    }

    @RequestMapping(value = "/getMyData")
    public HashMap<String,Object> getMyData(){
        HashMap<String,String> param = new HashMap<>();
        param.put("id","0000000001");
        param.put("name","dsfdf");
        HashMap<String,Object> data = studentMapper.getMyData(param);
        return data;
    }



    @RequestMapping("/insert")
    public void insert(){
        GenericDaoImpl genericDao = new GenericDaoImpl();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setAddress("海贼王和之国九里");
        studentEntity.setMoney(666666);
        studentEntity.setSto_no("1125113005");
        studentEntity.setName("路飞");
        genericDao.saveDBEntity(studentEntity,genericMapper);
    }

    @RequestMapping(value = "/deleteStudentById",method = RequestMethod.GET)
    public void deleteStudentById(int id){
        GenericDaoImpl genericDao = new GenericDaoImpl();
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(id);
        genericDao.deleteDBEntityByKey(studentEntity,genericMapper);
    }


    @RequestMapping(value = "/getPage",method = RequestMethod.GET)
    public List<Student> getPageList(int pageSize,int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List <Student> page = studentMapper.getAll();
        return page;
    }

}
