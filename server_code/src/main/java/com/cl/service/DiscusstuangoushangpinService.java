package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.DiscusstuangoushangpinEntity;
import com.cl.entity.view.DiscusstuangoushangpinView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 团购商品评论表
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
public interface DiscusstuangoushangpinService extends IService<DiscusstuangoushangpinEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<DiscusstuangoushangpinView> selectListView(Wrapper<DiscusstuangoushangpinEntity> wrapper);

  DiscusstuangoushangpinView selectView(@Param("ew") Wrapper<DiscusstuangoushangpinEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<DiscusstuangoushangpinEntity> wrapper);
}
