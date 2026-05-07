package com.cl.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.cl.entity.ChatFriendEntity;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.BeanUtils;

/**
 * 好友表 后端返回视图实体辅助类 （通常后端关联的表或者自定义的字段需要返回使用）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
@TableName("chat_friend")
public class ChatFriendView extends ChatFriendEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  public ChatFriendView() {}

  public ChatFriendView(ChatFriendEntity chatFriendEntity) {
    try {
      BeanUtils.copyProperties(this, chatFriendEntity);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private String content;

  private int notreadnum;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getNotreadnum() {
    return notreadnum;
  }

  public void setNotreadnum(int notreadnum) {
    this.notreadnum = notreadnum;
  }
}
