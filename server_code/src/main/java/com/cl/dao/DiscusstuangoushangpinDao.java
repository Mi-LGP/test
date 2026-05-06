package com.cl.dao;

import com.cl.entity.DiscusstuangoushangpinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscusstuangoushangpinView;


/**
 * 团购商品评论表
 * 
 * @author 
 * @email 
 * @date 2025-04-06 15:12:27
 */
public interface DiscusstuangoushangpinDao extends BaseMapper<DiscusstuangoushangpinEntity> {
	
	List<DiscusstuangoushangpinView> selectListView(@Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);

	List<DiscusstuangoushangpinView> selectListView(Pagination page,@Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);
	
	DiscusstuangoushangpinView selectView(@Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);


}
