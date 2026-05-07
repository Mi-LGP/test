package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.TuangoushangpinEntity;
import com.cl.entity.view.TuangoushangpinView;
import com.cl.service.StoreupService;
import com.cl.service.TuangoushangpinService;
import com.cl.utils.MPUtil;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import java.text.SimpleDateFormat;
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
 * 团购商品 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@RestController
@RequestMapping("/tuangoushangpin")
public class TuangoushangpinController {
  @Autowired private TuangoushangpinService tuangoushangpinService;

  @Autowired private StoreupService storeupService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params,
      TuangoushangpinEntity tuangoushangpin,
      HttpServletRequest request) {
    String tableName = request.getSession().getAttribute("tableName").toString();
    if (tableName.equals("shangjia")) {
      tuangoushangpin.setShangjia((String) request.getSession().getAttribute("username"));
    }
    EntityWrapper<TuangoushangpinEntity> ew = new EntityWrapper<TuangoushangpinEntity>();

    PageUtils page =
        tuangoushangpinService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuangoushangpin), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params,
      TuangoushangpinEntity tuangoushangpin,
      HttpServletRequest request) {
    EntityWrapper<TuangoushangpinEntity> ew = new EntityWrapper<TuangoushangpinEntity>();

    PageUtils page =
        tuangoushangpinService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, tuangoushangpin), params), params));
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(TuangoushangpinEntity tuangoushangpin) {
    EntityWrapper<TuangoushangpinEntity> ew = new EntityWrapper<TuangoushangpinEntity>();
    ew.allEq(MPUtil.allEQMapPre(tuangoushangpin, "tuangoushangpin"));
    return R.ok().put("data", tuangoushangpinService.selectListView(ew));
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(TuangoushangpinEntity tuangoushangpin) {
    EntityWrapper<TuangoushangpinEntity> ew = new EntityWrapper<TuangoushangpinEntity>();
    ew.allEq(MPUtil.allEQMapPre(tuangoushangpin, "tuangoushangpin"));
    TuangoushangpinView tuangoushangpinView = tuangoushangpinService.selectView(ew);
    return R.ok("查询团购商品成功").put("data", tuangoushangpinView);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    TuangoushangpinEntity tuangoushangpin = tuangoushangpinService.selectById(id);
    tuangoushangpin =
        tuangoushangpinService.selectView(new EntityWrapper<TuangoushangpinEntity>().eq("id", id));
    return R.ok().put("data", tuangoushangpin);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    TuangoushangpinEntity tuangoushangpin = tuangoushangpinService.selectById(id);
    tuangoushangpin =
        tuangoushangpinService.selectView(new EntityWrapper<TuangoushangpinEntity>().eq("id", id));
    return R.ok().put("data", tuangoushangpin);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(@RequestBody TuangoushangpinEntity tuangoushangpin, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangoushangpin);
    tuangoushangpinService.insert(tuangoushangpin);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(@RequestBody TuangoushangpinEntity tuangoushangpin, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangoushangpin);
    tuangoushangpinService.insert(tuangoushangpin);
    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  public R update(@RequestBody TuangoushangpinEntity tuangoushangpin, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(tuangoushangpin);
    tuangoushangpinService.updateById(tuangoushangpin); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    tuangoushangpinService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
  }

  /** 提醒接口 */
  @RequestMapping("/remind/{columnName}/{type}")
  public R remindCount(
      @PathVariable("columnName") String columnName,
      HttpServletRequest request,
      @PathVariable("type") String type,
      @RequestParam Map<String, Object> map) {
    map.put("column", columnName);
    map.put("type", type);

    if (type.equals("2")) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Calendar c = Calendar.getInstance();
      Date remindStartDate = null;
      Date remindEndDate = null;
      if (map.get("remindstart") != null) {
        Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, remindStart);
        remindStartDate = c.getTime();
        map.put("remindstart", sdf.format(remindStartDate));
      }
      if (map.get("remindend") != null) {
        Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, remindEnd);
        remindEndDate = c.getTime();
        map.put("remindend", sdf.format(remindEndDate));
      }
    }

    Wrapper<TuangoushangpinEntity> wrapper = new EntityWrapper<TuangoushangpinEntity>();
    if (map.get("remindstart") != null) {
      wrapper.ge(MPUtil.camelToSnake(columnName), map.get("remindstart"));
    }
    if (map.get("remindend") != null) {
      wrapper.le(MPUtil.camelToSnake(columnName), map.get("remindend"));
    }

    String tableName = request.getSession().getAttribute("tableName").toString();
    if (tableName.equals("shangjia")) {
      wrapper.eq("shangjia", (String) request.getSession().getAttribute("username"));
    }

    int count = tuangoushangpinService.selectCount(wrapper);
    return R.ok().put("count", count);
  }
}
