package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.OrdersEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 商品订单 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("orders")
public class OrdersView extends OrdersEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  public OrdersView() {}

  private String yonghu;

  public OrdersView(OrdersEntity ordersEntity) {
    try {
      BeanUtils.copyProperties(this, ordersEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public String getYonghu() {
    return yonghu;
  }

  public void setYonghu(String yonghu) {
    this.yonghu = yonghu;
  }
}
