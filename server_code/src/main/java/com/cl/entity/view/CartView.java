package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.CartEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 购物车 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("cart")
public class CartView extends CartEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  public CartView() {}

  public CartView(CartEntity cartEntity) {
    try {
      BeanUtils.copyProperties(this, cartEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
