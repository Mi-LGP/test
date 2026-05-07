package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.DingdanpeisongEntity;
import com.cl.entity.view.DingdanpeisongView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 订单配送
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface DingdanpeisongDao extends BaseMapper<DingdanpeisongEntity> {

  List<DingdanpeisongView> selectListView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);

  List<DingdanpeisongView> selectListView(
      Pagination page, @Param("ew") Wrapper<DingdanpeisongEntity> wrapper);

  DingdanpeisongView selectView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);
}
