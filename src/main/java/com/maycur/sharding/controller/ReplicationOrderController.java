package com.maycur.sharding.controller;

import com.maycur.sharding.entity.OrderEntity;
import com.maycur.sharding.service.ReplicationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 读写分离的简单测试，共三个库，一主二从，master / slave0 / slave1
 */
@RestController
@RequestMapping("/replication-order")
public class ReplicationOrderController {

    @Autowired private ReplicationOrderService orderService;

    /**
     * 创建order
     * 1. 在创建前先查询，此时路由到slave进行查询，未查询到
     * 2. 创建order，路由到master库
     * 3. 创建后查询，此时因为前面的写操作，所以后续所有的读操作都会路由到master，所以也能查到
     * 4. 再次查询，也会被路由到master，所以也能被查到
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
     * 查询某个order，会路由到slave。如果我们在slave0里插入数据，多次调用此接口将会看到，
     * 一次有数据一次没数据，则证明了sharding-jdbc利用round-robin算法把请求平均分配到了不同的slave库
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public OrderEntity queryOne(@PathVariable("orderId") Long orderId) {
        return orderService.queryOne(orderId);
    }

    /**
     * 查询某个order连续两次，如果orderId相同，会发现只查询了一次；而如果orderId不同，则会路由到两个不同的slave库进行查询
     * @param orderId
     */
    @GetMapping("/twice/{orderId}")
    public void queryTwice(@PathVariable("orderId") Long orderId) {
        orderService.queryTwice(orderId);
    }

    /**
     * 查询某个order，利用hint manager，强制路由到写库进行查询
     * @param orderId
     * @return
     */
    @GetMapping("/hint/{orderId}")
    public OrderEntity queryOneWithHint(@PathVariable("orderId") Long orderId) {
        return orderService.queryOneWithHint(orderId);
    }
}
