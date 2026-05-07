package com.cl.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.entity.TuangouActivityEntity;
import com.cl.entity.view.TuangouActivityView;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 拼团活动 Dao 接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface TuangouActivityDao extends BaseMapper<TuangouActivityEntity> {

  List<TuangouActivityView> selectListView(
      com.baomidou.mybatisplus.plugins.pagination.Pagination page,
      @Param("ew") Wrapper<TuangouActivityEntity> wrapper);

  TuangouActivityView selectView(@Param("ew") Wrapper<TuangouActivityEntity> wrapper);
}
