package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.ShangpinxinxiEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 商品信息 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("shangpinxinxi")
public class ShangpinxinxiView extends ShangpinxinxiEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Double score;

  public Double getScore() {
    return score;
  }

  public void setScore(Double score) {
    this.score = score;
  }

  public ShangpinxinxiView() {}

  public ShangpinxinxiView(ShangpinxinxiEntity shangpinxinxiEntity) {
    try {
      BeanUtils.copyProperties(this, shangpinxinxiEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
