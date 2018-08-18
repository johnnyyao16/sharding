package com.maycur.sharding.strategy;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

@Slf4j
public class HashModStrategy implements PreciseShardingAlgorithm<String> {
    private static final int SHARDING_NUMBER = 2;

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        int hash = shardingValue.getValue().hashCode();
        hash = hash ^ (hash >>> 16);
        String shardingTableName =
            new StringBuilder(shardingValue.getLogicTableName()).append("_").append((SHARDING_NUMBER - 1) & hash)
                .toString();
        for (String each : availableTargetNames) {
            if (StringUtils.equals(each, shardingTableName)) {
                log.debug("the target database name: {}", each);
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

}
