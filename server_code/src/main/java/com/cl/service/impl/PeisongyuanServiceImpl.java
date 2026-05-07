package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.PeisongyuanDao;
import com.cl.entity.PeisongyuanEntity;
import com.cl.entity.view.PeisongyuanView;
import com.cl.service.PeisongyuanService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("peisongyuanService")
public class PeisongyuanServiceImpl extends ServiceImpl<PeisongyuanDao, PeisongyuanEntity>
    implements PeisongyuanService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<PeisongyuanEntity> page =
        this.selectPage(
            new Query<PeisongyuanEntity>(params).getPage(), new EntityWrapper<PeisongyuanEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Wrapper<PeisongyuanEntity> wrapper) {
    Page<PeisongyuanView> page = new Query<PeisongyuanView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<PeisongyuanView> selectListView(Wrapper<PeisongyuanEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public PeisongyuanView selectView(Wrapper<PeisongyuanEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }
}
