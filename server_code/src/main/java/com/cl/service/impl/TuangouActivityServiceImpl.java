package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.TuangouActivityDao;
import com.cl.entity.TuangouActivityEntity;
import com.cl.service.TuangouActivityService;
import com.cl.entity.view.TuangouActivityView;
import org.apache.ibatis.annotations.Param;

@Service("tuangouactivityService")
public class TuangouActivityServiceImpl extends ServiceImpl<TuangouActivityDao, TuangouActivityEntity> implements TuangouActivityService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TuangouActivityEntity> page = this.selectPage(
                new Query<TuangouActivityEntity>(params).getPage(),
                new EntityWrapper<TuangouActivityEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<TuangouActivityEntity> wrapper) {
		  Page<TuangouActivityView> page =new Query<TuangouActivityView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page, wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
}
