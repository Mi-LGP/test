package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.MenuEntity;
import com.cl.entity.view.MenuView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:25
 */
public interface MenuDao extends BaseMapper<MenuEntity> {

  List<MenuView> selectListView(@Param("ew") Wrapper<MenuEntity> wrapper);

  List<MenuView> selectListView(Pagination page, @Param("ew") Wrapper<MenuEntity> wrapper);

  MenuView selectView(@Param("ew") Wrapper<MenuEntity> wrapper);
}
