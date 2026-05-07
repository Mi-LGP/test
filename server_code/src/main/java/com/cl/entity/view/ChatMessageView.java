package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.ChatMessageEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 消息表 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("chat_message")
public class ChatMessageView extends ChatMessageEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  public ChatMessageView() {}

  public ChatMessageView(ChatMessageEntity chatMessageEntity) {
    try {
      BeanUtils.copyProperties(this, chatMessageEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
