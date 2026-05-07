package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.CartEntity;
import com.cl.entity.view.CartView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 购物车
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface CartDao extends BaseMapper<CartEntity> {

  List<CartView> selectListView(@Param("ew") Wrapper<CartEntity> wrapper);

  List<CartView> selectListView(Pagination page, @Param("ew") Wrapper<CartEntity> wrapper);

  CartView selectView(@Param("ew") Wrapper<CartEntity> wrapper);
}
