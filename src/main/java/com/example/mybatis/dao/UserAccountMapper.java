package com.example.mybatis.dao;

import com.example.mybatis.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserAccountMapper {
    public boolean updateAccount(UserAccount userAccount) throws Exception;
    public UserAccount getUserAccountByUserId(Long userId) throws Exception;
}
