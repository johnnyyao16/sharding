package com.maycur.sharding.service;

import com.maycur.sharding.dao.mapper.OrderMapper;
import com.maycur.sharding.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class OrderService {
    @Autowired private OrderMapper orderMapper;

    public void createOrder(Long orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserId(orderId);
        orderEntity.setUserName("UserName_" + orderId);
        orderMapper.insert(orderEntity);
    }

    public List<OrderEntity> queryAll() {
        return orderMapper.selectAll();
    }

    public OrderEntity queryOne(Long orderId) {
        return orderMapper.selectOne(orderId);
    }

    public void remove(Long orderId) {
        orderMapper.delete(orderId);
    }

    public void update(Long orderId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserId(orderId);
        orderEntity.setUserName("UserName_Updated_" + orderId);
        orderMapper.update(orderEntity);
    }
}
