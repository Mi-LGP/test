package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.ChatFriendEntity;
import com.cl.entity.view.ChatFriendView;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 好友表
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
public interface ChatFriendDao extends BaseMapper<ChatFriendEntity> {

  List<ChatFriendView> selectListView(@Param("ew") Wrapper<ChatFriendEntity> wrapper);

  List<ChatFriendView> selectListView(
      Pagination page, @Param("ew") Wrapper<ChatFriendEntity> wrapper);

  ChatFriendView selectView(@Param("ew") Wrapper<ChatFriendEntity> wrapper);

  List<ChatFriendView> selectFriendListView(Pagination page, Map<String, Object> params);
}
