package com.example.mybatis.interf;


import com.example.mybatis.entity.TransferDTO;
import result.Result;

public interface IUserAccountService {
    /**
     * 转账
     * @param dto
     * @return
     * @throws Exception
     */
    public Result transfter(TransferDTO dto) throws Exception;

    public void testAroundAop();

}
