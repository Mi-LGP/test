package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.ShangpinxinxiDao;
import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.view.ShangpinxinxiView;
import com.cl.service.ShangpinxinxiService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("shangpinxinxiService")
public class ShangpinxinxiServiceImpl extends ServiceImpl<ShangpinxinxiDao, ShangpinxinxiEntity>
    implements ShangpinxinxiService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<ShangpinxinxiEntity> page =
        this.selectPage(
            new Query<ShangpinxinxiEntity>(params).getPage(),
            new EntityWrapper<ShangpinxinxiEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinxinxiEntity> wrapper) {
    Page<ShangpinxinxiView> page = new Query<ShangpinxinxiView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<ShangpinxinxiView> selectListView(Wrapper<ShangpinxinxiEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public ShangpinxinxiView selectView(Wrapper<ShangpinxinxiEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }

  @Override
  public List<ShangpinxinxiView> selectHotRanking() {
    return baseMapper.selectHotRanking();
  }
}
