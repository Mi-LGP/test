package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.TuangouActivityEntity;
import com.cl.utils.PageUtils;
import java.util.Map;

/**
 * 拼团活动
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface TuangouActivityService extends IService<TuangouActivityEntity> {

  PageUtils queryPage(Map<String, Object> params);

  PageUtils queryPage(Map<String, Object> params, Wrapper<TuangouActivityEntity> wrapper);
}
