package com.example.mybatis.service;

import com.example.mybatis.entity.Student;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class JunitServiceImpl {


    private void test1(Student student){
        System.out.println(student.getName());
        System.out.println(student.getSto_no());
    }

    private void test2(Map<String,String> data) {
        Iterator iter = data.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            System.out.println(key);
            System.out.println(val);
        }
    }

    private void test3(List<String> data) {
        for (String temp:data) {
            System.out.println(temp);
        }
    }

    private void test4(Set<String> data) {
        for (String temp:data) {
            System.out.println(temp);
        }
    }

    private void test5(int[] data) {
        for (int i = 0;i<data.length;i++) {
            System.out.println(data[i]);
        }
    }

    private void test6(byte[] data) {
        for (int i = 0;i<data.length;i++) {
            System.out.println(data[i]);
        }
    }


}
