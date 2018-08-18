package com.maycur.sharding.controller;

import com.google.common.collect.Lists;
import com.maycur.sharding.entity.Fee;
import com.maycur.sharding.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分片策略：
 * 1. 实际节点数只有两个(t_fee_0和t_fee_1，t_fee_allocation_0和t_fee_allocation_1)，
 * 每个节点虚拟出各5个虚拟节点，利用环状Hash来最大程度分散
 * 2. 根据entCode的hash值，找到hash环上的对应虚拟节点
 * 3. 根据找到的虚拟节点，找出对应的实际节点
 */
@RestController
@RequestMapping(value = "/fee")
public class FeeController {
    @Autowired private FeeService feeService;

    /**
     * 新增或更新操作，根据entCode找出对应的数据表
     *
     * @param fee
     * @return
     */
    @PostMapping
    public String saveFee(@RequestBody Fee fee) {
        feeService.saveFee(fee);
        return "Successful!";
    }

    /**
     * 删除操作，根据entCode找出对应的数据表
     *
     * @param entCode
     * @param feeCode
     * @return
     */
    @DeleteMapping("/{entCode}/{feeCode}")
    public String removeFee(@PathVariable("entCode") String entCode, @PathVariable("feeCode") String feeCode) {
        feeService.remove(entCode, feeCode);
        return "Successful!";
    }

    /**
     * 查询某个企业所有的fee，根据entCode找出对应的数据表，只会查询一次
     *
     * @param entCode
     * @return
     */
    @GetMapping("/{entCode}")
    public List<Fee> queryByEntCode(@PathVariable("entCode") String entCode) {
        return feeService.queryByEntCode(entCode);
    }

    /**
     * 查询某个具体的fee，根据entCode找出对应的数据表，只会查询一次
     *
     * @param entCode
     * @return
     */
    @GetMapping("/{entCode}/{feeCode}")
    public Fee queryOne(@PathVariable("entCode") String entCode, @PathVariable("feeCode") String feeCode) {
        return feeService.queryOne(entCode, feeCode);
    }

    /**
     * 根据feeCodes批量查询fee，根据entCode找出对应的数据表，只会查询一次
     *
     * @param entCode
     * @return
     */
    @GetMapping("/query/{entCode}/{feeCodes}")
    public List<Fee> querySome(@PathVariable("entCode") String entCode, @PathVariable("feeCodes") String feeCodes) {
        return feeService.querySome(entCode, Lists.newArrayList(feeCodes.split(",")));
    }

    /**
     * 根据entCodes查询多个企业下的fee，因为有多个entCode，所有会查询多次，路由到不同的数据表，然后将结果合并
     *
     * @param entCodes
     * @return
     */
    @GetMapping("/query/{entCodes}")
    public List<Fee> queryByEntCodes(@PathVariable("entCodes") String entCodes) {
        return feeService.queryByEntCodes(Lists.newArrayList(entCodes.split(",")));
    }

    /**
     * 保存fee并且同时保存fee allocation，并且最后将fee以及fee allocation一并查询出来
     * 1. 保存fee和fee allocation都会根据entCode路由到不同的数据表中进行操作
     * 2. 查询的时候，因为有fee和fee_allocation的join，默认情况下，在where clause里有fee.ent_code = xxx，
     * 则对于fee表，会根据entCode路由到某张数据表里进行一次查询，而不是查询多次。
     * 而对于fee allocation表则不会根据entCode进行路由。有两种办法：
     * a). 在where clause也加上fee_allocation.ent_code = xxx
     * b). 在配置文件里设置binding table：sharding.jdbc.config.sharding.binding-tables[0]=t_fee,t_fee_allocation
     * 这样就认为fee_allocation和fee绑定了，默认会使用fee的路由规则
     * 3. 在查询的时候，还在fee表前join里reimbruse表，reimbruse表并没有进行分片，由此也表明了和其他未分片的表进行join对结果不影响
     *
     * @return
     */
    @GetMapping("/with-allocation")
    public List<Fee> saveFeeWithAllocations() {
        return feeService.saveFeeWithAllocations();
    }

    /**
     * 批量保存fee，由于fee里的entCode不同，可以看到路由到不同的数据表里进行保存
     */
    @PostMapping("/batch-fee")
    public void batchSave() {
        feeService.batchSave();
    }
}
