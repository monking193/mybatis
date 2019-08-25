package com.example.mybatis.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDTO {
    /**
     * 转账人
     */
    private Long fromUserId;
    /**
     * 给谁转账
     */
    private Long toUserId;

    /**
     * 转账金额
     */
    private BigDecimal amount;


    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
