package com.maycur.sharding.dao.mapper;

import com.maycur.sharding.entity.Fee;
import com.maycur.sharding.entity.FeeAllocation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeAllocationMapper {
    void insert(@Param("allocation") FeeAllocation allocation);

    List<Fee> selectByFeeCode(@Param("entCode") String entCode, @Param("feeCode") String feeCode);

}
