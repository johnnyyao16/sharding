package com.maycur.sharding.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Reimburse {
    private String entCode;
    private String reimburseDataCode;
    private String reimburseName;

    public String getEntCode() {
        return entCode;
    }

    public void setEntCode(String entCode) {
        this.entCode = entCode;
    }

    public String getReimburseDataCode() {
        return reimburseDataCode;
    }

    public void setReimburseDataCode(String reimburseDataCode) {
        this.reimburseDataCode = reimburseDataCode;
    }

    public String getReimburseName() {
        return reimburseName;
    }

    public void setReimburseName(String reimburseName) {
        this.reimburseName = reimburseName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("entCode", entCode).append("reimburseDataCode", reimburseDataCode)
            .append("reimburseName", reimburseName).toString();
    }
}
