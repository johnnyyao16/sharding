package com.maycur.sharding.util;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ShardingHashUtil {
    private static final int VIRTUAL_NODE_COUNT = 5;
    private static final String VIRTUAL_NODE_NAME = "Virtual_Node";
    private static Map<String, SortedMap<Integer, Integer>> shardingMap = Maps.newConcurrentMap();

    public static SortedMap<Integer, Integer> getShardingMapByNode(String nodeName, int nodeCount) {
        if (shardingMap.containsKey(nodeName)) {
            return shardingMap.get(nodeName);
        } else {
            SortedMap<Integer, Integer> virtualNodes = new TreeMap<Integer, Integer>();
            for (int nodeIndex = 0; nodeIndex < nodeCount; nodeIndex++) {
                for (int virtualIndex = 0; virtualIndex < VIRTUAL_NODE_COUNT; virtualIndex++) {
                    virtualNodes.put(Hashing.md5().newHasher().putString(nodeName, Charsets.UTF_8).putInt(nodeIndex)
                        .putString(VIRTUAL_NODE_NAME, Charsets.UTF_8).putInt(virtualIndex).hash().asInt(), nodeIndex);
                }
            }
            return virtualNodes;
        }
    }
}
