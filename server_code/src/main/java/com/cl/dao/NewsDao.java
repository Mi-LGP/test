package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.NewsEntity;
import com.cl.entity.view.NewsView;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 广告信息
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:25
 */
public interface NewsDao extends BaseMapper<NewsEntity> {

  List<NewsView> selectListView(@Param("ew") Wrapper<NewsEntity> wrapper);

  List<NewsView> selectListView(Pagination page, @Param("ew") Wrapper<NewsEntity> wrapper);

  NewsView selectView(@Param("ew") Wrapper<NewsEntity> wrapper);

  List<Map<String, Object>> selectValue(
      @Param("params") Map<String, Object> params, @Param("ew") Wrapper<NewsEntity> wrapper);

  List<Map<String, Object>> selectTimeStatValue(
      @Param("params") Map<String, Object> params, @Param("ew") Wrapper<NewsEntity> wrapper);

  List<Map<String, Object>> selectGroup(
      @Param("params") Map<String, Object> params, @Param("ew") Wrapper<NewsEntity> wrapper);
}
