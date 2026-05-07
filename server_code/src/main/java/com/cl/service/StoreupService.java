package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.StoreupEntity;
import com.cl.entity.view.StoreupView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 我的收藏
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface StoreupService extends IService<StoreupEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<StoreupView> selectListView(Wrapper<StoreupEntity> wrapper);

  StoreupView selectView(@Param("ew") Wrapper<StoreupEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<StoreupEntity> wrapper);
}
