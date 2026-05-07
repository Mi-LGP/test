package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.OrdersEntity;
import com.cl.entity.view.OrdersView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 商品订单
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface OrdersService extends IService<OrdersEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<OrdersView> selectListView(Wrapper<OrdersEntity> wrapper);

  OrdersView selectView(@Param("ew") Wrapper<OrdersEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);

  List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);

  List<Map<String, Object>> selectTimeStatValue(
      Map<String, Object> params, Wrapper<OrdersEntity> wrapper);

  List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<OrdersEntity> wrapper);
}
