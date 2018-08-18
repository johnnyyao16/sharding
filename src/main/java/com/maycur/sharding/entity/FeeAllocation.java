package com.maycur.sharding.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class FeeAllocation {
    private String entCode;
    private String feeCode;
    private String feeAllocationCode;
    private BigDecimal amount;

    private BigDecimal feeAmount;
    private String userCode;
    private String reimburseDataCode;

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public String getFeeCode() {
        return feeCode;
    }

    public void setFeeCode(String feeCode) {
        this.feeCode = feeCode;
    }

    public String getFeeAllocationCode() {
        return feeAllocationCode;
    }

    public void setFeeAllocationCode(String feeAllocationCode) {
        this.feeAllocationCode = feeAllocationCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getReimburseDataCode() {
        return reimburseDataCode;
    }

    public void setReimburseDataCode(String reimburseDataCode) {
        this.reimburseDataCode = reimburseDataCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entCode", entCode).append("feeCode", feeCode)
            .append("feeAllocationCode", feeAllocationCode).append("amount", amount).append("feeAmount", feeAmount)
            .append("userCode", userCode).append("reimburseDataCode", reimburseDataCode).toString();
    }
}
