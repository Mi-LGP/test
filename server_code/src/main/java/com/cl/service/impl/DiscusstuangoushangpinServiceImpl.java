package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.DiscusstuangoushangpinDao;
import com.cl.entity.DiscusstuangoushangpinEntity;
import com.cl.entity.view.DiscusstuangoushangpinView;
import com.cl.service.DiscusstuangoushangpinService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("discusstuangoushangpinService")
public class DiscusstuangoushangpinServiceImpl
    extends ServiceImpl<DiscusstuangoushangpinDao, DiscusstuangoushangpinEntity>
    implements DiscusstuangoushangpinService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<DiscusstuangoushangpinEntity> page =
        this.selectPage(
            new Query<DiscusstuangoushangpinEntity>(params).getPage(),
            new EntityWrapper<DiscusstuangoushangpinEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(
      Map<String, Object> params, Wrapper<DiscusstuangoushangpinEntity> wrapper) {
    Page<DiscusstuangoushangpinView> page = new Query<DiscusstuangoushangpinView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<DiscusstuangoushangpinView> selectListView(
      Wrapper<DiscusstuangoushangpinEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public DiscusstuangoushangpinView selectView(Wrapper<DiscusstuangoushangpinEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }
}
