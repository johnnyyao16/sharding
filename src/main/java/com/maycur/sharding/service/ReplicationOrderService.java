package com.maycur.sharding.service;

import com.maycur.sharding.dao.mapper.ReplicationOrderMapper;
import com.maycur.sharding.entity.OrderEntity;
import io.shardingsphere.core.api.HintManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ReplicationOrderService {
    @Autowired private ReplicationOrderMapper orderMapper;

    /**
     * 1. insert前先查询，查不到
     * 2. insert后查询，因为insert会路由到master，所以后续所有的读都应该路由到master
     *
     * @param orderId
     */
    public void createOrder(Long orderId) {
        OrderEntity resultBeforeInsert = queryOne(orderId);
        assert resultBeforeInsert != null;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(orderId);
        orderEntity.setUserId(orderId);
        orderEntity.setUserName("UserName_" + orderId);
        orderMapper.insert(orderEntity);
        OrderEntity resultAfterInsert = queryOne(orderId);
        log.debug("Search order entity first time after insert: {}", resultAfterInsert);
        assert resultAfterInsert != null;
        OrderEntity resultAfterInsert2 = queryOne(orderId);
        log.debug("Search order entity second time after insert: {}", resultAfterInsert2);
        assert resultAfterInsert2 != null && resultAfterInsert2.equals(resultAfterInsert);
    }

    public List<OrderEntity> queryAll() {
        return orderMapper.selectAll();
    }

    public OrderEntity queryOne(Long orderId) {
        return orderMapper.selectOne(orderId);
    }

    /**
     * 1. 对于在一个事务里查询两次，如果两次的id相同，则sharding-jdbc会缓存结果，不再重复查询
     * 2. 如果真的需要做两次查询，可以看到两次次路由到了不同的slave数据源。
     * 我们在同一个slave数据源中插入两条数据，在下面的查询中会出现一个查到一个查不到的情况，而不会出现两个都查到或者两个都查不到的情况
     *
     * @param orderId
     */
    public void queryTwice(Long orderId) {
        OrderEntity firstResult = queryOne(orderId);
        OrderEntity secondResult = queryOne(11112l);
        assert (firstResult != null && secondResult == null) || (firstResult == null && secondResult != null);
    }

    public OrderEntity queryOneWithHint(Long orderId) {
        HintManager hintManager = HintManager.getInstance();
        hintManager.setMasterRouteOnly();
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
