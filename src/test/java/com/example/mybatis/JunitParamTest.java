package com.example.mybatis;

import com.example.mybatis.entity.Student;
import com.example.mybatis.service.JunitServiceImpl;
import org.junit.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class JunitParamTest {

    @Autowired
    private JunitServiceImpl junitService;

    @Test
    public void test1() throws Exception{
        Student student = new Student();
        student.setName("666");
        student.setSto_no("11251130005");
        Method method = junitService.getClass().getDeclaredMethod("test1",student.getClass());
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,student);
    }

    @Test
    public  void test2() throws Exception{
        Map<String,String> data = new HashMap<>();
        data.put("1","test1");
        data.put("2","test2");
        Method method = junitService.getClass().getDeclaredMethod("test2",Map.class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,data);
    }

    @Test
    public void test3() throws Exception{
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        Method method = junitService.getClass().getDeclaredMethod("test3", List.class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,data);
    }

    @Test
    public void test4() throws Exception{
        Set<String> data = new HashSet<>();
        data.add("3");
        data.add("4");
        Method method = junitService.getClass().getDeclaredMethod("test4", Set.class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,data);
    }

    @Test
    public void test5() throws Exception{
        int[] data = {5,6};
        Method method = junitService.getClass().getDeclaredMethod("test5", int[].class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,data);
    }

    @Test
    public void test6() throws Exception{
        byte[] data = {7,8};
        Method method = junitService.getClass().getDeclaredMethod("test6", byte[].class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(junitService,data);
    }



}
