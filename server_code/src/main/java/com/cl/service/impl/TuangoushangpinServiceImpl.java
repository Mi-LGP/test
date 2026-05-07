package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.TuangoushangpinDao;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.entity.view.TuangoushangpinView;
import com.cl.service.TuangoushangpinService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("tuangoushangpinService")
public class TuangoushangpinServiceImpl
    extends ServiceImpl<TuangoushangpinDao, TuangoushangpinEntity>
    implements TuangoushangpinService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<TuangoushangpinEntity> page =
        this.selectPage(
            new Query<TuangoushangpinEntity>(params).getPage(),
            new EntityWrapper<TuangoushangpinEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Wrapper<TuangoushangpinEntity> wrapper) {
    Page<TuangoushangpinView> page = new Query<TuangoushangpinView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<TuangoushangpinView> selectListView(Wrapper<TuangoushangpinEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public TuangoushangpinView selectView(Wrapper<TuangoushangpinEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }
}
