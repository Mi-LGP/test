package com.cl.task;

import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.service.ShangpinxinxiService;
import com.cl.service.TuangoushangpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryCheckTask {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ShangpinxinxiService shangpinxinxiService;

    @Autowired
    private TuangoushangpinService tuangoushangpinService;

    private static final String STOCK_KEY_PREFIX = "stock:";

    /**
     * 每小时执行一次库存校验，确保最终一致性
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void checkInventoryConsistency() {
        System.out.println("Starting inventory consistency check...");

        // 校验普通商品库存
        List<ShangpinxinxiEntity> list = shangpinxinxiService.selectList(null);
        for (ShangpinxinxiEntity item : list) {
            String key = STOCK_KEY_PREFIX + "shangpinxinxi:" + item.getId();
            Object redisStockObj = redisTemplate.opsForValue().get(key);
            if (redisStockObj != null) {
                int redisStock = Integer.parseInt(redisStockObj.toString());
                if (item.getAlllimittimes() != null && item.getAlllimittimes() != redisStock) {
                    System.out.println("Inconsistency found for product " + item.getId() + 
                                       ". MySQL: " + item.getAlllimittimes() + ", Redis: " + redisStock);
                    // 以 Redis 为准更新 MySQL (或者根据业务需求决定)
                    item.setAlllimittimes(redisStock);
                    shangpinxinxiService.updateById(item);
                }
            }
        }

        // 校验团购商品库存
        List<TuangoushangpinEntity> tuangouList = tuangoushangpinService.selectList(null);
        for (TuangoushangpinEntity item : tuangouList) {
            String key = STOCK_KEY_PREFIX + "tuangoushangpin:" + item.getId();
            Object redisStockObj = redisTemplate.opsForValue().get(key);
            if (redisStockObj != null) {
                int redisStock = Integer.parseInt(redisStockObj.toString());
                if (item.getAlllimittimes() != null && item.getAlllimittimes() != redisStock) {
                    System.out.println("Inconsistency found for tuangou product " + item.getId() + 
                                       ". MySQL: " + item.getAlllimittimes() + ", Redis: " + redisStock);
                    item.setAlllimittimes(redisStock);
                    tuangoushangpinService.updateById(item);
                }
            }
        }

        System.out.println("Inventory consistency check completed.");
    }
}
