package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.DiscussshangpinxinxiEntity;
import com.cl.entity.view.DiscussshangpinxinxiView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息评论表
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
public interface DiscussshangpinxinxiDao extends BaseMapper<DiscussshangpinxinxiEntity> {

  List<DiscussshangpinxinxiView> selectListView(
      @Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

  List<DiscussshangpinxinxiView> selectListView(
      Pagination page, @Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);

  DiscussshangpinxinxiView selectView(@Param("ew") Wrapper<DiscussshangpinxinxiEntity> wrapper);
}
