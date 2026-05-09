package com.cl.config;

import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.service.InventoryService;
import com.cl.service.ShangpinxinxiService;
import com.cl.service.TuangoushangpinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryStartupRunner implements CommandLineRunner {

    @Autowired
    private ShangpinxinxiService shangpinxinxiService;

    @Autowired
    private TuangoushangpinService tuangoushangpinService;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public void run(String... args) throws Exception {
        // 加载普通商品库存
        List<ShangpinxinxiEntity> list = shangpinxinxiService.selectList(null);
        for (ShangpinxinxiEntity item : list) {
            if (item.getAlllimittimes() != null) {
                inventoryService.preheatStock("shangpinxinxi", item.getId(), item.getAlllimittimes());
            }
        }
        
        // 加载团购商品库存
        List<TuangoushangpinEntity> tuangouList = tuangoushangpinService.selectList(null);
        for (TuangoushangpinEntity item : tuangouList) {
            if (item.getAlllimittimes() != null) {
                inventoryService.preheatStock("tuangoushangpin", item.getId(), item.getAlllimittimes());
            }
        }
        System.out.println("Inventory preheat completed.");
    }
}
