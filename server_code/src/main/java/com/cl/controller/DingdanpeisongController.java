package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.DingdanpeisongEntity;
import com.cl.entity.view.DingdanpeisongView;
import com.cl.service.DingdanpeisongService;
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
 * 订单配送 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@RestController
@RequestMapping("/dingdanpeisong")
public class DingdanpeisongController {
  @Autowired private DingdanpeisongService dingdanpeisongService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params,
      DingdanpeisongEntity dingdanpeisong,
      HttpServletRequest request) {
    String tableName = request.getSession().getAttribute("tableName").toString();
    if (tableName.equals("yonghu")) {
      dingdanpeisong.setYonghu((String) request.getSession().getAttribute("username"));
    }
    if (tableName.equals("shangjia")) {
      dingdanpeisong.setShangjia((String) request.getSession().getAttribute("username"));
    }
    if (tableName.equals("peisongyuan")) {
      dingdanpeisong.setPeisongyuan((String) request.getSession().getAttribute("username"));
    }
    EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();

    PageUtils page =
        dingdanpeisongService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpeisong), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params,
      DingdanpeisongEntity dingdanpeisong,
      HttpServletRequest request) {
    EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();

    PageUtils page =
        dingdanpeisongService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dingdanpeisong), params), params));
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(DingdanpeisongEntity dingdanpeisong) {
    EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();
    ew.allEq(MPUtil.allEQMapPre(dingdanpeisong, "dingdanpeisong"));
    return R.ok().put("data", dingdanpeisongService.selectListView(ew));
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(DingdanpeisongEntity dingdanpeisong) {
    EntityWrapper<DingdanpeisongEntity> ew = new EntityWrapper<DingdanpeisongEntity>();
    ew.allEq(MPUtil.allEQMapPre(dingdanpeisong, "dingdanpeisong"));
    DingdanpeisongView dingdanpeisongView = dingdanpeisongService.selectView(ew);
    return R.ok("查询订单配送成功").put("data", dingdanpeisongView);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    DingdanpeisongEntity dingdanpeisong = dingdanpeisongService.selectById(id);
    dingdanpeisong =
        dingdanpeisongService.selectView(new EntityWrapper<DingdanpeisongEntity>().eq("id", id));
    return R.ok().put("data", dingdanpeisong);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    DingdanpeisongEntity dingdanpeisong = dingdanpeisongService.selectById(id);
    dingdanpeisong =
        dingdanpeisongService.selectView(new EntityWrapper<DingdanpeisongEntity>().eq("id", id));
    return R.ok().put("data", dingdanpeisong);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(dingdanpeisong);
    dingdanpeisongService.insert(dingdanpeisong);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(dingdanpeisong);
    dingdanpeisongService.insert(dingdanpeisong);
    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  public R update(@RequestBody DingdanpeisongEntity dingdanpeisong, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(dingdanpeisong);
    dingdanpeisongService.updateById(dingdanpeisong); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    dingdanpeisongService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
  }
}
