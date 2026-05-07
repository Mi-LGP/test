package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.ChatMessageEntity;
import com.cl.entity.view.ChatMessageView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 消息表
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface ChatMessageDao extends BaseMapper<ChatMessageEntity> {

  List<ChatMessageView> selectListView(@Param("ew") Wrapper<ChatMessageEntity> wrapper);

  List<ChatMessageView> selectListView(
      Pagination page, @Param("ew") Wrapper<ChatMessageEntity> wrapper);

  ChatMessageView selectView(@Param("ew") Wrapper<ChatMessageEntity> wrapper);
}
