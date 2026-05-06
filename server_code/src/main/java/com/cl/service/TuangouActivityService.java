package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.entity.TuangouActivityEntity;
import com.baomidou.mybatisplus.service.IService;
import java.util.List;
import java.util.Map;
import com.cl.utils.PageUtils;
import com.cl.entity.view.TuangouActivityView;
import org.apache.ibatis.annotations.Param;

/**
 * 拼团活动
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
public interface TuangouActivityService extends IService<TuangouActivityEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    PageUtils queryPage(Map<String, Object> params,Wrapper<TuangouActivityEntity> wrapper);
    
}