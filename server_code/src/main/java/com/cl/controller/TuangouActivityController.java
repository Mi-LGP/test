package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.TuangouActivityEntity;
import com.cl.service.TuangouActivityService;
import com.cl.utils.MPUtil;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拼团活动 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@RestController
@RequestMapping("/tuangouactivity")
public class TuangouActivityController {
  @Autowired private TuangouActivityService tuangouactivityService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params,
      TuangouActivityEntity tuangouactivity,
      HttpServletRequest request) {
    EntityWrapper<TuangouActivityEntity> ew = new EntityWrapper<TuangouActivityEntity>();

    PageUtils page =
        tuangouactivityService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuangouactivity), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params,
      TuangouActivityEntity tuangouactivity,
      HttpServletRequest request) {
    EntityWrapper<TuangouActivityEntity> ew = new EntityWrapper<TuangouActivityEntity>();

    // 手动添加查询条件，避免 MPUtil 产生表别名问题
    if (params.get("goodsid") != null) {
      ew.eq("goodsid", params.get("goodsid"));
    }
    if (params.get("status") != null) {
      ew.eq("status", params.get("status"));
    }

    PageUtils page = tuangouactivityService.queryPage(params, ew);
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(TuangouActivityEntity tuangouactivity) {
    PageUtils page =
        tuangouactivityService.queryPage(
            new java.util.HashMap<>(), new EntityWrapper<TuangouActivityEntity>());
    return R.ok().put("data", page);
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(TuangouActivityEntity tuangouactivity) {
    EntityWrapper<TuangouActivityEntity> ew = new EntityWrapper<TuangouActivityEntity>();
    ew.allEq(MPUtil.allEQMapPre(tuangouactivity, "tuangouactivity"));
    TuangouActivityEntity tuangouactivityEntity = tuangouactivityService.selectOne(ew);
    return R.ok("查询拼团活动成功").put("data", tuangouactivityEntity);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    TuangouActivityEntity tuangouactivity = tuangouactivityService.selectById(id);
    return R.ok().put("data", tuangouactivity);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    TuangouActivityEntity tuangouactivity = tuangouactivityService.selectById(id);
    return R.ok().put("data", tuangouactivity);
  }

  /** 根据拼团编号查询 */
  @IgnoreAuth
  @RequestMapping("/getByGroupono")
  public R getByGroupono(@RequestParam String groupono) {
    TuangouActivityEntity tuangouactivity =
        tuangouactivityService.selectOne(
            new EntityWrapper<TuangouActivityEntity>().eq("groupono", groupono));
    if (tuangouactivity == null) {
      return R.error("未找到拼团活动");
    }
    return R.ok().put("data", tuangouactivity);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(@RequestBody TuangouActivityEntity tuangouactivity, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangouactivity);
    tuangouactivityService.insert(tuangouactivity);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(@RequestBody TuangouActivityEntity tuangouactivity, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangouactivity);
    tuangouactivityService.insert(tuangouactivity);
    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  public R update(@RequestBody TuangouActivityEntity tuangouactivity, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangouactivity);
    tuangouactivityService.updateById(tuangouactivity); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    tuangouactivityService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
  }
}
