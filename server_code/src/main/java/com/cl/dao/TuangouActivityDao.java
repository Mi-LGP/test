package com.cl.dao;

import com.cl.entity.TuangouActivityEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TuangouActivityView;

/**
 * 拼团活动 Dao 接口
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
public interface TuangouActivityDao extends BaseMapper<TuangouActivityEntity> {
	
	List<TuangouActivityView> selectListView(com.baomidou.mybatisplus.plugins.pagination.Pagination page,@Param("ew") Wrapper<TuangouActivityEntity> wrapper);
 
	TuangouActivityView selectView(@Param("ew") Wrapper<TuangouActivityEntity> wrapper);
}