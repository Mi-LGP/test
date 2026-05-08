package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.view.ShangpinxinxiView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface ShangpinxinxiDao extends BaseMapper<ShangpinxinxiEntity> {

  List<ShangpinxinxiView> selectListView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

  List<ShangpinxinxiView> selectListView(
      Pagination page, @Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

  ShangpinxinxiView selectView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

  List<ShangpinxinxiView> selectHotRanking();
}
