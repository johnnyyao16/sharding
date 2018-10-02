# Sharding-JDBC Demo
本示例主要针对sharding-jdbc做的分库分表以及读写分离的相关尝试，主要分为以下三部分：

### Order
order主要为了验证简单的分表，将order id作为分片键，根据order id的值对分表个数取余数，以此来确定路由到具体的实际表

### Fee
fee主要为了验证相对复杂的分表：
* 将ent code作为分片键，引入Hash一致性算法，让不同的ent code尽可能平均地分配到不同的实际表中去
* 关联fee allocation，引入binding table概念
* 批量插入

### Order Replication
order replication主要为了验证读写分离
