package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.ConfigEntity;
import com.cl.entity.view.ConfigView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 轮播图
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:25
 */
public interface ConfigDao extends BaseMapper<ConfigEntity> {

  List<ConfigView> selectListView(@Param("ew") Wrapper<ConfigEntity> wrapper);

  List<ConfigView> selectListView(Pagination page, @Param("ew") Wrapper<ConfigEntity> wrapper);

  ConfigView selectView(@Param("ew") Wrapper<ConfigEntity> wrapper);
}
