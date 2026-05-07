package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.entity.view.TuangoushangpinView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 团购商品
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface TuangoushangpinDao extends BaseMapper<TuangoushangpinEntity> {

  List<TuangoushangpinView> selectListView(@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);

  List<TuangoushangpinView> selectListView(
      Pagination page, @Param("ew") Wrapper<TuangoushangpinEntity> wrapper);

  TuangoushangpinView selectView(@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);
}
