package com.maycur.sharding.controller;

import com.maycur.sharding.entity.OrderEntity;
import com.maycur.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分片策略：orderId对2取余数
 */
@RestController
public class OrderController {

    @Autowired private OrderService orderService;

    /**
     * 新增操作，可以看到根据orderId对2取余数，路由到不同的order表中进行创建
     *
     * @param orderId
     * @return
     */
    @PostMapping("/{orderId}")
    public String createOrder(@PathVariable("orderId") Long orderId) {
        orderService.createOrder(orderId);
        return "Successful!";
    }

    /**
     * 删除操作，可以看到根据orderId对2取余数，路由到不同的order表中进行删除
     *
     * @param orderId
     * @return
     */
    @DeleteMapping("/{orderId}")
    public String removeOrder(@PathVariable("orderId") Long orderId) {
        orderService.remove(orderId);
        return "Successful!";
    }

    /**
     * 查询所有order记录，由于没有分片键orderId，所以会在两个order表里都进行查询操作，将结果集合并
     *
     * @return
     */
    @GetMapping("/")
    public List<OrderEntity> queryAll() {
        return orderService.queryAll();
    }

    /**
     * 查询单条order记录，可以看到根据orderId对2取余数，路由到不同的order表中进行查询
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public OrderEntity queryOne(@PathVariable("orderId") Long orderId) {
        return orderService.queryOne(orderId);
    }

    /**
     * 更新操作，可以看到根据orderId对2取余数，路由到不同的order表中进行更新
     *
     * @param orderId
     * @return
     */
    @PutMapping("/{orderId}")
    public String update(@PathVariable("orderId") Long orderId) {
        orderService.update(orderId);
        return "Successful!";
    }
}
