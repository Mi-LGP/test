package com.cl.service;

import com.cl.utils.R;

public interface InventoryService {
    /**
     * 预热库存到 Redis
     * @param tablename 表名
     * @param productId 商品ID
     * @param stock 初始库存
     */
    void preheatStock(String tablename, Long productId, Integer stock);

    /**
     * 原子性扣减库存
     * @param tablename 表名
     * @param productId 商品ID
     * @param amount 扣减数量
     * @return 1: 成功, 0: 库存不足, -1: 库存不存在
     */
    Long deductStock(String tablename, Long productId, Integer amount);

    /**
     * 增加库存（回滚时使用）
     * @param tablename 表名
     * @param productId 商品ID
     * @param amount 增加数量
     */
    void addStock(String tablename, Long productId, Integer amount);
}
