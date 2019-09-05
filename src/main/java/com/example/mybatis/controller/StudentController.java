package com.example.mybatis.controller;

import com.example.mybatis.dao.GenericMapper;
import com.example.mybatis.dao.StudentMapper;
import com.example.mybatis.entity.Student;
import com.example.mybatis.entity.StudentEntity;
import com.example.mybatis.exception.StudentException;
import com.example.mybatis.service.GenericDaoImpl;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/getPage",method = RequestMethod.GET,produces = "application/json")
    public List<Student> getPageList(int pageSize,int pageNum){
        PageHelper.startPage(pageNum,pageSize);
        List <Student> page = studentMapper.getAll();
        return page;
    }

    @PostMapping(value = "/getMyStudent",produces="application/json")
    @ResponseBody
    public String getStudent (@Validated @RequestBody Student student,BindingResult result) {
        try{
            if (result != null && result.hasErrors()) {
                StringBuilder errorMessageBuilder = new StringBuilder();
                result.getAllErrors().forEach(error -> errorMessageBuilder.append("[").append(error.getObjectName())
                        .append("-").append(error.getDefaultMessage()).append("]"));

                return errorMessageBuilder.toString();
            }
            return student.toString();
        } catch (Exception e) {
             if (e.getClass().equals(HttpMediaTypeNotSupportedException.class)) {
                 System.out.print(new StudentException("00","不支持的格式类型"));
             } else if (e.getClass().equals(HttpRequestMethodNotSupportedException.class)){
                 System.out.print(new StudentException("01","不支持的请求方法"));
             }
        }
        return null;
    }


}
