package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.DiscusstuangoushangpinEntity;
import com.cl.entity.view.DiscusstuangoushangpinView;
import com.cl.service.DiscusstuangoushangpinService;
import com.cl.utils.MPUtil;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 团购商品评论表 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:27
 */
@RestController
@RequestMapping("/discusstuangoushangpin")
public class DiscusstuangoushangpinController {
  @Autowired private DiscusstuangoushangpinService discusstuangoushangpinService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params,
      DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request) {
    EntityWrapper<DiscusstuangoushangpinEntity> ew =
        new EntityWrapper<DiscusstuangoushangpinEntity>();

    PageUtils page =
        discusstuangoushangpinService.queryPage(
            params,
            MPUtil.sort(
                MPUtil.between(MPUtil.likeOrEq(ew, discusstuangoushangpin), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params,
      DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request) {
    EntityWrapper<DiscusstuangoushangpinEntity> ew =
        new EntityWrapper<DiscusstuangoushangpinEntity>();

    PageUtils page =
        discusstuangoushangpinService.queryPage(
            params,
            MPUtil.sort(
                MPUtil.between(MPUtil.likeOrEq(ew, discusstuangoushangpin), params), params));
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(DiscusstuangoushangpinEntity discusstuangoushangpin) {
    EntityWrapper<DiscusstuangoushangpinEntity> ew =
        new EntityWrapper<DiscusstuangoushangpinEntity>();
    ew.allEq(MPUtil.allEQMapPre(discusstuangoushangpin, "discusstuangoushangpin"));
    return R.ok().put("data", discusstuangoushangpinService.selectListView(ew));
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(DiscusstuangoushangpinEntity discusstuangoushangpin) {
    EntityWrapper<DiscusstuangoushangpinEntity> ew =
        new EntityWrapper<DiscusstuangoushangpinEntity>();
    ew.allEq(MPUtil.allEQMapPre(discusstuangoushangpin, "discusstuangoushangpin"));
    DiscusstuangoushangpinView discusstuangoushangpinView =
        discusstuangoushangpinService.selectView(ew);
    return R.ok("查询团购商品评论表成功").put("data", discusstuangoushangpinView);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    DiscusstuangoushangpinEntity discusstuangoushangpin =
        discusstuangoushangpinService.selectById(id);
    discusstuangoushangpin =
        discusstuangoushangpinService.selectView(
            new EntityWrapper<DiscusstuangoushangpinEntity>().eq("id", id));
    return R.ok().put("data", discusstuangoushangpin);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    DiscusstuangoushangpinEntity discusstuangoushangpin =
        discusstuangoushangpinService.selectById(id);
    discusstuangoushangpin =
        discusstuangoushangpinService.selectView(
            new EntityWrapper<DiscusstuangoushangpinEntity>().eq("id", id));
    return R.ok().put("data", discusstuangoushangpin);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(
      @RequestBody DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request) {
    // ValidatorUtils.validateEntity(discusstuangoushangpin);
    discusstuangoushangpinService.insert(discusstuangoushangpin);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(
      @RequestBody DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request) {
    // ValidatorUtils.validateEntity(discusstuangoushangpin);
    discusstuangoushangpinService.insert(discusstuangoushangpin);
    return R.ok();
  }

  /** 获取用户密保 */
  @RequestMapping("/security")
  @IgnoreAuth
  public R security(@RequestParam String username) {
    DiscusstuangoushangpinEntity discusstuangoushangpin =
        discusstuangoushangpinService.selectOne(
            new EntityWrapper<DiscusstuangoushangpinEntity>().eq("", username));
    return R.ok().put("data", discusstuangoushangpin);
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  @IgnoreAuth
  public R update(
      @RequestBody DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request) {
    // ValidatorUtils.validateEntity(discusstuangoushangpin);
    discusstuangoushangpinService.updateById(discusstuangoushangpin); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    discusstuangoushangpinService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
  }

  /** 前端智能排序 */
  @IgnoreAuth
  @RequestMapping("/autoSort")
  public R autoSort(
      @RequestParam Map<String, Object> params,
      DiscusstuangoushangpinEntity discusstuangoushangpin,
      HttpServletRequest request,
      String pre) {
    EntityWrapper<DiscusstuangoushangpinEntity> ew =
        new EntityWrapper<DiscusstuangoushangpinEntity>();
    Map<String, Object> newMap = new HashMap<String, Object>();
    Map<String, Object> param = new HashMap<String, Object>();
    Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry<String, Object> entry = it.next();
      String key = entry.getKey();
      String newKey = entry.getKey();
      if (pre.endsWith(".")) {
        newMap.put(pre + newKey, entry.getValue());
      } else if (StringUtils.isEmpty(pre)) {
        newMap.put(newKey, entry.getValue());
      } else {
        newMap.put(pre + "." + newKey, entry.getValue());
      }
    }
    params.put("sort", "clicktime");
    params.put("order", "desc");
    PageUtils page =
        discusstuangoushangpinService.queryPage(
            params,
            MPUtil.sort(
                MPUtil.between(MPUtil.likeOrEq(ew, discusstuangoushangpin), params), params));
    return R.ok().put("data", page);
  }
}
