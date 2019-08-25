package com.example.mybatis.service;


import annoation.TryAgainException;
import com.example.mybatis.dao.UserAccountMapper;
import com.example.mybatis.entity.TransferDTO;
import com.example.mybatis.entity.UserAccount;
import com.example.mybatis.interf.IUserAccountService;
import annoation.IsTryAgain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import result.ApiException;
import result.ApiResultEnum;
import result.Consts;
import result.Result;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional
public class UserAccountService implements IUserAccountService {

    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 转账
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    @IsTryAgain
    public Result transfter(TransferDTO dto) throws Exception {
        updateAccount(dto.getFromUserId(),dto.getAmount(), Consts.ACCOUNT_OUT);
        updateAccount(dto.getToUserId(),dto.getAmount(), Consts.ACCOUNT_IN);
        return Result.ok();
    }

    public void testAroundAop(){
        System.out.print("aopAroundTest");
        throw new TryAgainException(ApiResultEnum.ERROR_TRY_AGAIN);
    }


    /**
     * 更新账户余额
     * @param userId 账户用户ID
     * @param amount    操作金额数量
     * @param state     转入--1 ，转出 -- 0
     * @throws Exception
     */
    public void updateAccount( Long userId, BigDecimal amount,int state) throws Exception{
        UserAccount userAccount = userAccountMapper.getUserAccountByUserId(userId);
        if(userAccount == null){
            throw new ApiException(ApiResultEnum.ACCOUNT_NOT_FOUND);
        }
        BigDecimal afterBalance = BigDecimal.ZERO;
        if(state == Consts.ACCOUNT_OUT){
            if(userAccount.getBalance().compareTo(amount)<0){
                throw new ApiException(ApiResultEnum.ACCOUNT_NOT_SUFFICIENT);
            }
            afterBalance = userAccount.getBalance().subtract(amount);
        }else if(state == Consts.ACCOUNT_IN){
            afterBalance = userAccount.getBalance().add(amount);
        }
        userAccount.setBalance(afterBalance);
        userAccount.setModifyBy(userId);
        userAccount.setModifyTime(new Date());
        if(!userAccountMapper.updateAccount(userAccount)){
            //如果更新失败就抛出去，重试
            System.out.print("-------开始重试------");
            throw new TryAgainException(ApiResultEnum.ERROR_TRY_AGAIN);
        }
    }
}
