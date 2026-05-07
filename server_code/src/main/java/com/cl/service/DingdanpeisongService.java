package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.DingdanpeisongEntity;
import com.cl.entity.view.DingdanpeisongView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 订单配送
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface DingdanpeisongService extends IService<DingdanpeisongEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<DingdanpeisongView> selectListView(Wrapper<DingdanpeisongEntity> wrapper);

  DingdanpeisongView selectView(@Param("ew") Wrapper<DingdanpeisongEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<DingdanpeisongEntity> wrapper);
}
