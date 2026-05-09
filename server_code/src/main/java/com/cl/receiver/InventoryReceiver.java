package com.cl.receiver;

import com.alibaba.fastjson.JSON;
import com.cl.config.RabbitMQConfig;
import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.service.ShangpinxinxiService;
import com.cl.service.TuangoushangpinService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InventoryReceiver {

    @Autowired
    private ShangpinxinxiService shangpinxinxiService;

    @Autowired
    private TuangoushangpinService tuangoushangpinService;

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_QUEUE)
    public void receiveUpdateInventory(String message) {
        try {
            Map<String, Object> map = JSON.parseObject(message, Map.class);
            String tablename = (String) map.get("tablename");
            Long goodid = Long.valueOf(map.get("goodid").toString());
            Integer buynumber = Integer.valueOf(map.get("buynumber").toString());
            String action = (String) map.get("action"); // "deduct" or "add"

            if ("shangpinxinxi".equals(tablename)) {
                ShangpinxinxiEntity product = shangpinxinxiService.selectById(goodid);
                if (product != null && product.getAlllimittimes() != null) {
                    if ("deduct".equals(action)) {
                        product.setAlllimittimes(product.getAlllimittimes() - buynumber);
                    } else {
                        product.setAlllimittimes(product.getAlllimittimes() + buynumber);
                    }
                    shangpinxinxiService.updateById(product);
                }
            } else if ("tuangoushangpin".equals(tablename)) {
                TuangoushangpinEntity product = tuangoushangpinService.selectById(goodid);
                if (product != null && product.getAlllimittimes() != null) {
                    if ("deduct".equals(action)) {
                        product.setAlllimittimes(product.getAlllimittimes() - buynumber);
                    } else {
                        product.setAlllimittimes(product.getAlllimittimes() + buynumber);
                    }
                    tuangoushangpinService.updateById(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
