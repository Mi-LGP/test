package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cl.entity.UsersEntity;
import com.cl.entity.view.UsersView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:25
 */
public interface UsersDao extends BaseMapper<UsersEntity> {

  List<UsersView> selectListView(@Param("ew") Wrapper<UsersEntity> wrapper);

  List<UsersView> selectListView(Pagination page, @Param("ew") Wrapper<UsersEntity> wrapper);

  UsersView selectView(@Param("ew") Wrapper<UsersEntity> wrapper);
}
