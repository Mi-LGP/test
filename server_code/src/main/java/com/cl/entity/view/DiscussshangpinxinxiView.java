package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.DiscussshangpinxinxiEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 商品信息评论表 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
@TableName("discussshangpinxinxi")
public class DiscussshangpinxinxiView extends DiscussshangpinxinxiEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  public DiscussshangpinxinxiView() {}

  public DiscussshangpinxinxiView(DiscussshangpinxinxiEntity discussshangpinxinxiEntity) {
    try {
      BeanUtils.copyProperties(this, discussshangpinxinxiEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
