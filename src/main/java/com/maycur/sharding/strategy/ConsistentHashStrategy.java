package com.maycur.sharding.strategy;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.maycur.sharding.util.ShardingHashUtil;
import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.SortedMap;

@Slf4j
public class ConsistentHashStrategy implements PreciseShardingAlgorithm<String> {

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        SortedMap<Integer, Integer> virtualNodes =
            ShardingHashUtil.getShardingMapByNode(shardingValue.getLogicTableName(), availableTargetNames.size());
        SortedMap<Integer, Integer> subMap = virtualNodes
            .tailMap(Hashing.md5().newHasher().putString(shardingValue.getValue(), Charsets.UTF_8).hash().asInt());

        String shardingTableName = new StringBuilder(shardingValue.getLogicTableName()).append("_").append(
            (subMap == null || subMap.isEmpty()) ?
                virtualNodes.get(virtualNodes.firstKey()) :
                subMap.get(subMap.firstKey())).toString();
        for (String each : availableTargetNames) {
            if (StringUtils.equals(each, shardingTableName)) {
                log.debug("the target database name: {}", each);
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

}
