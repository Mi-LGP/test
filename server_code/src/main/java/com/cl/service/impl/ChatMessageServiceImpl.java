package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.ChatMessageDao;
import com.cl.entity.ChatMessageEntity;
import com.cl.entity.view.ChatMessageView;
import com.cl.service.ChatMessageService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("chatMessageService")
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageDao, ChatMessageEntity>
    implements ChatMessageService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<ChatMessageEntity> page =
        this.selectPage(
            new Query<ChatMessageEntity>(params).getPage(), new EntityWrapper<ChatMessageEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Wrapper<ChatMessageEntity> wrapper) {
    Page<ChatMessageView> page = new Query<ChatMessageView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<ChatMessageView> selectListView(Wrapper<ChatMessageEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public ChatMessageView selectView(Wrapper<ChatMessageEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }
}
