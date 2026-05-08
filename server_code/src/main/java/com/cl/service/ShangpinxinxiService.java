package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.view.ShangpinxinxiView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface ShangpinxinxiService extends IService<ShangpinxinxiEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<ShangpinxinxiView> selectListView(Wrapper<ShangpinxinxiEntity> wrapper);

  ShangpinxinxiView selectView(@Param("ew") Wrapper<ShangpinxinxiEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<ShangpinxinxiEntity> wrapper);

  List<ShangpinxinxiView> selectHotRanking();
}
