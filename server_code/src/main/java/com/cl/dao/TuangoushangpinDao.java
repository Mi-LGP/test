package com.cl.dao;

import com.cl.entity.TuangoushangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.TuangoushangpinView;


/**
 * 团购商品
 * 
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
public interface TuangoushangpinDao extends BaseMapper<TuangoushangpinEntity> {
	
	List<TuangoushangpinView> selectListView(@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);

	List<TuangoushangpinView> selectListView(Pagination page,@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);
	
	TuangoushangpinView selectView(@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);


}
