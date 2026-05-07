package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.PeisongyuanEntity;
import com.cl.entity.view.PeisongyuanView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 配送员
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface PeisongyuanDao extends BaseMapper<PeisongyuanEntity> {

  List<PeisongyuanView> selectListView(@Param("ew") Wrapper<PeisongyuanEntity> wrapper);

  List<PeisongyuanView> selectListView(
      Pagination page, @Param("ew") Wrapper<PeisongyuanEntity> wrapper);

  PeisongyuanView selectView(@Param("ew") Wrapper<PeisongyuanEntity> wrapper);
}
