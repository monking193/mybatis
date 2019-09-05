package com.example.mybatis.exception;


import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CommonException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String test1(HttpServletRequest req, Exception e){
        Map<String,Object> result = new HashMap<String,Object>();
        if (e.getClass().equals(HttpMediaTypeNotSupportedException.class)) {
           return  "不支持的格式类型";
        } else if (e.getClass().equals(HttpRequestMethodNotSupportedException.class)){
           return "不支持的请求方法";
        }
        return e.getMessage();
    }

    @ExceptionHandler(BindException.class)
    public Map<String,Object> test2(BindException e){
        System.out.print(e.getMessage());
        Map<String,Object> result = new HashMap<String,Object>();


        return result;
    }

}
