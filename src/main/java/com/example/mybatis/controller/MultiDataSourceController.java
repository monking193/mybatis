package com.example.mybatis.controller;


import com.example.mybatis.entity.Student;
import com.example.mybatis.service.MultiDataSourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MultiDataSourceController {

    @Autowired
    private MultiDataSourceServiceImpl multiDataSourceService;

    @RequestMapping(value = "/getStudent",method = RequestMethod.GET)
    public void getStudentById (String[] args) {
        Student student = multiDataSourceService.getDataSouce(1);
        System.out.print(student);

        Student student1 = multiDataSourceService.getDataSouce1(1);
        System.out.print(student1);
    }

    @RequestMapping(value = "/batchDelete",method = RequestMethod.GET)
    public void batchDelete() {
        ArrayList<String> ids = new ArrayList<>();
        for (int i = 0;i < 3000;i++) {
            ids.add(String.valueOf(i));
        }
        multiDataSourceService.testDelete(ids);
    }

}
