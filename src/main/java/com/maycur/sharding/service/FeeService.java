package com.maycur.sharding.service;

import com.google.common.collect.Lists;
import com.maycur.sharding.dao.mapper.FeeAllocationMapper;
import com.maycur.sharding.dao.mapper.FeeMapper;
import com.maycur.sharding.entity.Fee;
import com.maycur.sharding.entity.FeeAllocation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
public class FeeService {

    @Autowired private FeeMapper feeMapper;
    @Autowired private FeeAllocationMapper feeAllocationMapper;

    public void saveFee(Fee fee) {
        if (StringUtils.isNotBlank(fee.getFeeCode())) {
            feeMapper.update(fee);
        } else {
            fee.setFeeCode(RandomStringUtils.randomAlphabetic(10));
            feeMapper.insert(fee);
        }
    }

    public List<Fee> queryByEntCode(String entCode) {
        return feeMapper.selectAllByEntCode(entCode);
    }

    public Fee queryOne(String entCode, String feeCode) {
        return feeMapper.selectOne(entCode, feeCode);
    }

    public void remove(String entCode, String feeCode) {
        feeMapper.delete(entCode, feeCode);
    }

    public List<Fee> querySome(String entCode, List<String> feeCodes) {
        return feeMapper.selectSomeByEntCode(entCode, feeCodes);
    }

    public List<Fee> queryByEntCodes(List<String> entCodes) {
        return feeMapper.selectByEntCodes(entCodes);
    }

    public List<Fee> saveFeeWithAllocations() {
        Fee fee0 = new Fee();
        fee0.setFeeCode("feeCode0");
        fee0.setEntCode("entCode0");
        fee0.setFeeAmount(new BigDecimal(100));
        fee0.setReimburseDataCode("reimburseDataCode");
        fee0.setUserCode("userCode");

        Fee fee1 = new Fee();
        fee1.setFeeCode("feeCode1");
        fee1.setEntCode("entCode1");
        fee1.setFeeAmount(new BigDecimal(101));
        fee1.setReimburseDataCode("reimburseDataCode");
        fee1.setUserCode("userCode");

        FeeAllocation feeAllocation00 = new FeeAllocation();
        feeAllocation00.setAmount(new BigDecimal(90));
        feeAllocation00.setEntCode("entCode0");
        feeAllocation00.setFeeAllocationCode("allocationCode00");
        feeAllocation00.setFeeCode("feeCode0");
        feeAllocationMapper.insert(feeAllocation00);
        FeeAllocation feeAllocation01 = new FeeAllocation();
        feeAllocation01.setAmount(new BigDecimal(10));
        feeAllocation01.setEntCode("entCode0");
        feeAllocation01.setFeeAllocationCode("allocationCode01");
        feeAllocation01.setFeeCode("feeCode0");
        feeAllocationMapper.insert(feeAllocation01);

        FeeAllocation feeAllocation10 = new FeeAllocation();
        feeAllocation10.setAmount(new BigDecimal(50));
        feeAllocation10.setEntCode("entCode1");
        feeAllocation10.setFeeAllocationCode("allocationCode10");
        feeAllocation10.setFeeCode("feeCode1");
        feeAllocationMapper.insert(feeAllocation10);
        FeeAllocation feeAllocation11 = new FeeAllocation();
        feeAllocation11.setAmount(new BigDecimal(61));
        feeAllocation11.setEntCode("entCode1");
        feeAllocation11.setFeeAllocationCode("allocationCode11");
        feeAllocation11.setFeeCode("feeCode1");
        feeAllocationMapper.insert(feeAllocation11);

        feeMapper.insert(fee0);
        feeMapper.insert(fee1);

        return feeMapper.selectReimburseFeeWithAllocation("entCode1");
    }

    public void batchSave() {
        Fee fee0 = new Fee();
        fee0.setFeeCode("feeCode0");
        fee0.setEntCode("entCode0");
        fee0.setFeeAmount(new BigDecimal(100));
        fee0.setReimburseDataCode("reimburseDataCode");
        fee0.setUserCode("userCode");

        Fee fee1 = new Fee();
        fee1.setFeeCode("feeCode2");
        fee1.setEntCode("entCode100000");
        fee1.setFeeAmount(new BigDecimal(101));
        fee1.setReimburseDataCode("reimburseDataCode");
        fee1.setUserCode("userCode");

        feeMapper.batchInsert(Lists.newArrayList(fee0, fee1));
    }


}
