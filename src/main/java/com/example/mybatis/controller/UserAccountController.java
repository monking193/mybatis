package com.example.mybatis.controller;

import com.example.mybatis.entity.TransferDTO;
import com.example.mybatis.interf.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.Result;

@RestController
@RequestMapping("/usr/userAccount")
public class UserAccountController {

    @Autowired
    private IUserAccountService userAccountService;

    @PostMapping("/transfter")
    public Result transfter(TransferDTO dto) throws Exception {
        return userAccountService.transfter(dto);
    }

    @RequestMapping("/testAround")
    public void test(){
        userAccountService.testAroundAop();
    }


}
