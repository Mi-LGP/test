package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.entity.view.TuangoushangpinView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 团购商品
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface TuangoushangpinService extends IService<TuangoushangpinEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<TuangoushangpinView> selectListView(Wrapper<TuangoushangpinEntity> wrapper);

  TuangoushangpinView selectView(@Param("ew") Wrapper<TuangoushangpinEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<TuangoushangpinEntity> wrapper);
}
