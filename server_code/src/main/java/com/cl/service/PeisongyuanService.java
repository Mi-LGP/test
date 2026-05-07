package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.entity.PeisongyuanEntity;
import com.cl.entity.view.PeisongyuanView;
import com.cl.utils.PageUtils;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 配送员
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
public interface PeisongyuanService extends IService<PeisongyuanEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<PeisongyuanView> selectListView(Wrapper<PeisongyuanEntity> wrapper);

  PeisongyuanView selectView(@Param("ew") Wrapper<PeisongyuanEntity> wrapper);

  PageUtils queryPage(Map<String, Object> params, Wrapper<PeisongyuanEntity> wrapper);
}
