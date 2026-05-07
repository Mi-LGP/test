package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.DiscusstuangoushangpinEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 团购商品评论表 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
@TableName("discusstuangoushangpin")
public class DiscusstuangoushangpinView extends DiscusstuangoushangpinEntity
    implements Serializable {
  private static final long serialVersionUID = 1L;

  public DiscusstuangoushangpinView() {}

  public DiscusstuangoushangpinView(DiscusstuangoushangpinEntity discusstuangoushangpinEntity) {
    try {
      BeanUtils.copyProperties(this, discusstuangoushangpinEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
