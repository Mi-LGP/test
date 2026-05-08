package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.ShangpinxinxiEntity;
import com.cl.entity.view.ShangpinxinxiView;
import com.cl.service.ShangpinxinxiService;
import com.cl.service.StoreupService;
import com.cl.utils.MPUtil;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 商品信息 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@RestController
@RequestMapping("/shangpinxinxi")
public class ShangpinxinxiController {
  @Autowired private ShangpinxinxiService shangpinxinxiService;

  @Autowired private StoreupService storeupService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params,
      ShangpinxinxiEntity shangpinxinxi,
      HttpServletRequest request) {
    String tableName = request.getSession().getAttribute("tableName").toString();
    if (tableName.equals("shangjia")) {
      shangpinxinxi.setShangjia((String) request.getSession().getAttribute("username"));
    }

    EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<ShangpinxinxiEntity>();

    PageUtils page =
        shangpinxinxiService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinxinxi), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params,
      ShangpinxinxiEntity shangpinxinxi,
      HttpServletRequest request) {
    EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<ShangpinxinxiEntity>();

    PageUtils page =
        shangpinxinxiService.queryPage(
            params,
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinxinxi), params), params));
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(ShangpinxinxiEntity shangpinxinxi) {
    EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<ShangpinxinxiEntity>();
    ew.allEq(MPUtil.allEQMapPre(shangpinxinxi, "shangpinxinxi"));
    return R.ok().put("data", shangpinxinxiService.selectListView(ew));
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(ShangpinxinxiEntity shangpinxinxi) {
    EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<ShangpinxinxiEntity>();
    ew.allEq(MPUtil.allEQMapPre(shangpinxinxi, "shangpinxinxi"));
    ShangpinxinxiView shangpinxinxiView = shangpinxinxiService.selectView(ew);
    return R.ok("查询商品信息成功").put("data", shangpinxinxiView);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    ShangpinxinxiEntity shangpinxinxi = shangpinxinxiService.selectById(id);
    shangpinxinxi =
        shangpinxinxiService.selectView(new EntityWrapper<ShangpinxinxiEntity>().eq("id", id));
    return R.ok().put("data", shangpinxinxi);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    ShangpinxinxiEntity shangpinxinxi = shangpinxinxiService.selectById(id);
    shangpinxinxi =
        shangpinxinxiService.selectView(new EntityWrapper<ShangpinxinxiEntity>().eq("id", id));
    return R.ok().put("data", shangpinxinxi);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(@RequestBody ShangpinxinxiEntity shangpinxinxi, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(shangpinxinxi);
    shangpinxinxiService.insert(shangpinxinxi);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(@RequestBody ShangpinxinxiEntity shangpinxinxi, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(shangpinxinxi);
    shangpinxinxiService.insert(shangpinxinxi);
    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  public R update(@RequestBody ShangpinxinxiEntity shangpinxinxi, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(shangpinxinxi);
    shangpinxinxiService.updateById(shangpinxinxi); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    shangpinxinxiService.deleteBatchIds(Arrays.asList(ids));
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

    Wrapper<ShangpinxinxiEntity> wrapper = new EntityWrapper<ShangpinxinxiEntity>();
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

    int count = shangpinxinxiService.selectCount(wrapper);
    return R.ok().put("count", count);
  }

  /** 总数量 */
  @RequestMapping("/count")
  public R count(
      @RequestParam Map<String, Object> params,
      ShangpinxinxiEntity shangpinxinxi,
      HttpServletRequest request) {
    String tableName = request.getSession().getAttribute("tableName").toString();
    if (tableName.equals("shangjia")) {
      shangpinxinxi.setShangjia((String) request.getSession().getAttribute("username"));
    }
    EntityWrapper<ShangpinxinxiEntity> ew = new EntityWrapper<ShangpinxinxiEntity>();
    int count =
        shangpinxinxiService.selectCount(
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, shangpinxinxi), params), params));
    return R.ok().put("data", count);
  }

  /** 热销排行 */
  @IgnoreAuth
  @RequestMapping("/hotRanking")
  public R hotRanking() {
    return R.ok().put("data", shangpinxinxiService.selectHotRanking());
  }
}
