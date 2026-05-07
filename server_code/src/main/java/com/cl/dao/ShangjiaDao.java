package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.ShangjiaEntity;
import com.cl.entity.view.ShangjiaView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 商家
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface ShangjiaDao extends BaseMapper<ShangjiaEntity> {

  List<ShangjiaView> selectListView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);

  List<ShangjiaView> selectListView(Pagination page, @Param("ew") Wrapper<ShangjiaEntity> wrapper);

  ShangjiaView selectView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);
}
