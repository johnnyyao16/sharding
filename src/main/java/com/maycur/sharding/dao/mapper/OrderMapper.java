package com.maycur.sharding.dao.mapper;

import com.maycur.sharding.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    void insert(@Param("order") OrderEntity order);

    List<OrderEntity> selectAll();

    OrderEntity selectOne(Long orderId);

    void update(@Param("order") OrderEntity order);

    void delete(Long orderId);

}
