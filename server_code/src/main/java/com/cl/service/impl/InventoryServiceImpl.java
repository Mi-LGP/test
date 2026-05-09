package com.cl.service.impl;

import com.cl.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String STOCK_KEY_PREFIX = "stock:";
    
    private String luaScript = 
            "local stockKey = KEYS[1] " +
            "local deductAmount = tonumber(ARGV[1]) " +
            "local currentStock = redis.call('get', stockKey) " +
            "if not currentStock then " +
            "    return -1 " +
            "end " +
            "currentStock = tonumber(currentStock) " +
            "if currentStock >= deductAmount then " +
            "    redis.call('decrby', stockKey, deductAmount) " +
            "    return 1 " +
            "else " +
            "    return 0 " +
            "end";

    private DefaultRedisScript<Long> script;

    @PostConstruct
    public void init() {
        script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptText(luaScript);
    }

    @Override
    public void preheatStock(String tablename, Long productId, Integer stock) {
        redisTemplate.opsForValue().set(STOCK_KEY_PREFIX + tablename + ":" + productId, stock);
    }

    @Override
    public Long deductStock(String tablename, Long productId, Integer amount) {
        return redisTemplate.execute(script, Collections.singletonList(STOCK_KEY_PREFIX + tablename + ":" + productId), amount);
    }

    @Override
    public void addStock(String tablename, Long productId, Integer amount) {
        redisTemplate.opsForValue().increment(STOCK_KEY_PREFIX + tablename + ":" + productId, amount);
    }
}
