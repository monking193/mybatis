package com.example.mybatis.controller;

import com.example.mybatis.dao.SynanoInterface;

public class SynanoController {

    static SynanoInterface synanoInterface = new SynanoInterface() {
        @Override
        public void test() {
            System.out.print("test -------");
        }

        @Override
        public String test1() {
            return "test1---------";
        }
    };


    public  static void main (String[] args){
        synanoInterface.test();
        synanoInterface.test1();
    }


}
