package com.example.mybatis.exception;

public class StudentException extends RuntimeException {

    private String retCd ;  //异常对应的返回码
    private String msgDes;  //异常对应的描述信息

    public StudentException (String retCd, String msgDes) {
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }



}
