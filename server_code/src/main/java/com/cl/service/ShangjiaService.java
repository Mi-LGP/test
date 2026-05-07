package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.ShangjiaEntity;
import com.cl.entity.view.ShangjiaView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 商家
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface ShangjiaService extends IService<ShangjiaEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<ShangjiaView> selectListView(Wrapper<ShangjiaEntity> wrapper);

  ShangjiaView selectView(@Param("ew") Wrapper<ShangjiaEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<ShangjiaEntity> wrapper);
}
