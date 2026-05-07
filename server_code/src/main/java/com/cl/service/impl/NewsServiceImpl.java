package com.cl.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.dao.NewsDao;
import com.cl.entity.NewsEntity;
import com.cl.entity.view.NewsView;
import com.cl.service.NewsService;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

  @Override
  public PageUtils queryPage(Map<String, Object> params) {
    Page<NewsEntity> page =
        this.selectPage(new Query<NewsEntity>(params).getPage(), new EntityWrapper<NewsEntity>());
    return new PageUtils(page);
  }

  @Override
  public PageUtils queryPage(Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
    Page<NewsView> page = new Query<NewsView>(params).getPage();
    page.setRecords(baseMapper.selectListView(page, wrapper));
    PageUtils pageUtil = new PageUtils(page);
    return pageUtil;
  }

  @Override
  public List<NewsView> selectListView(Wrapper<NewsEntity> wrapper) {
    return baseMapper.selectListView(wrapper);
  }

  @Override
  public NewsView selectView(Wrapper<NewsEntity> wrapper) {
    return baseMapper.selectView(wrapper);
  }

  @Override
  public List<Map<String, Object>> selectValue(
      Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
    return baseMapper.selectValue(params, wrapper);
  }

  @Override
  public List<Map<String, Object>> selectTimeStatValue(
      Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
    return baseMapper.selectTimeStatValue(params, wrapper);
  }

  @Override
  public List<Map<String, Object>> selectGroup(
      Map<String, Object> params, Wrapper<NewsEntity> wrapper) {
    return baseMapper.selectGroup(params, wrapper);
  }
}
