package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.StoreupEntity;
import com.cl.entity.view.StoreupView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 我的收藏
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface StoreupDao extends BaseMapper<StoreupEntity> {

  List<StoreupView> selectListView(@Param("ew") Wrapper<StoreupEntity> wrapper);

  List<StoreupView> selectListView(Pagination page, @Param("ew") Wrapper<StoreupEntity> wrapper);

  StoreupView selectView(@Param("ew") Wrapper<StoreupEntity> wrapper);
}
