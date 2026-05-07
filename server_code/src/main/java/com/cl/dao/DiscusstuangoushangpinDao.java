package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.DiscusstuangoushangpinEntity;
import com.cl.entity.view.DiscusstuangoushangpinView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 团购商品评论表
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
public interface DiscusstuangoushangpinDao extends BaseMapper<DiscusstuangoushangpinEntity> {

  List<DiscusstuangoushangpinView> selectListView(
      @Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);

  List<DiscusstuangoushangpinView> selectListView(
      Pagination page, @Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);

  DiscusstuangoushangpinView selectView(@Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);
}
