package com.maycur.sharding.dao.mapper;

import com.maycur.sharding.entity.Fee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeMapper {
    void insert(@Param("fee") Fee fee);

    List<Fee> selectAllByEntCode(@Param("entCode") String entCode);

    Fee selectOne(@Param("entCode") String entCode, @Param("feeCode") String feeCode);

    void update(@Param("fee") Fee fee);

    void delete(@Param("entCode") String entCode, @Param("feeCode") String feeCode);

    List<Fee> selectSomeByEntCode(@Param("entCode") String entCode, @Param("feeCodes") List<String> feeCodes);

    List<Fee> selectByEntCodes(@Param("entCodes") List<String> entCodes);

    List<Fee> selectFeeWithAllocation(@Param("entCode") String entCode);

    List<Fee> selectReimburseFeeWithAllocation(@Param("entCode") String entCode);

    void batchInsert(@Param("fees") List<Fee> fees);
}
