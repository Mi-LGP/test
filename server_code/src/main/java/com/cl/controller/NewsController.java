package com.cl.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.entity.NewsEntity;
import com.cl.entity.view.NewsView;
import com.cl.service.NewsService;
import com.cl.utils.MPUtil;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import java.text.SimpleDateFormat;
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
 * 广告信息 后端接口
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:25
 */
@RestController
@RequestMapping("/news")
public class NewsController {
  @Autowired private NewsService newsService;

  /** 后台列表 */
  @RequestMapping("/page")
  public R page(
      @RequestParam Map<String, Object> params, NewsEntity news, HttpServletRequest request) {
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();

    PageUtils page =
        newsService.queryPage(
            params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
    return R.ok().put("data", page);
  }

  /** 前端列表 */
  @IgnoreAuth
  @RequestMapping("/list")
  public R list(
      @RequestParam Map<String, Object> params, NewsEntity news, HttpServletRequest request) {
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();

    PageUtils page =
        newsService.queryPage(
            params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
    return R.ok().put("data", page);
  }

  /** 列表 */
  @RequestMapping("/lists")
  public R list(NewsEntity news) {
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    ew.allEq(MPUtil.allEQMapPre(news, "news"));
    return R.ok().put("data", newsService.selectListView(ew));
  }

  /** 查询 */
  @RequestMapping("/query")
  public R query(NewsEntity news) {
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    ew.allEq(MPUtil.allEQMapPre(news, "news"));
    NewsView newsView = newsService.selectView(ew);
    return R.ok("查询广告信息成功").put("data", newsView);
  }

  /** 后端详情 */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    NewsEntity news = newsService.selectById(id);
    if (null == news.getClickNumber()) {
      news.setClickNumber(0);
    }
    news.setClickNumber(news.getClickNumber() + 1);
    newsService.updateById(news);
    news = newsService.selectView(new EntityWrapper<NewsEntity>().eq("id", id));
    return R.ok().put("data", news);
  }

  /** 前端详情 */
  @IgnoreAuth
  @RequestMapping("/detail/{id}")
  public R detail(@PathVariable("id") Long id) {
    NewsEntity news = newsService.selectById(id);
    if (null == news.getClickNumber()) {
      news.setClickNumber(0);
    }
    news.setClickNumber(news.getClickNumber() + 1);
    newsService.updateById(news);
    news = newsService.selectView(new EntityWrapper<NewsEntity>().eq("id", id));
    return R.ok().put("data", news);
  }

  /** 后端保存 */
  @RequestMapping("/save")
  public R save(@RequestBody NewsEntity news, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(news);
    newsService.insert(news);
    return R.ok();
  }

  /** 前端保存 */
  @RequestMapping("/add")
  public R add(@RequestBody NewsEntity news, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(news);
    newsService.insert(news);
    return R.ok();
  }

  /** 修改 */
  @RequestMapping("/update")
  @Transactional
  public R update(@RequestBody NewsEntity news, HttpServletRequest request) {
    // ValidatorUtils.validateEntity(news);
    newsService.updateById(news); // 全部更新
    return R.ok();
  }

  /** 删除 */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    newsService.deleteBatchIds(Arrays.asList(ids));
    return R.ok();
  }

  /** （按值统计） */
  @RequestMapping("/value/{xColumnName}/{yColumnName}")
  public R value(
      @PathVariable("yColumnName") String yColumnName,
      @PathVariable("xColumnName") String xColumnName,
      HttpServletRequest request) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("xColumn", MPUtil.camelToSnake(xColumnName));
    params.put("yColumn", MPUtil.camelToSnake(yColumnName));
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    List<Map<String, Object>> result = MPUtil.snakeListToCamel(newsService.selectValue(params, ew));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Map<String, Object> m : result) {
      for (String k : m.keySet()) {
        if (m.get(k) instanceof Date) {
          m.put(k, sdf.format((Date) m.get(k)));
        }
      }
    }

    Collections.sort(
        result,
        (map1, map2) -> {
          // 假设 total 总是存在并且是数值类型
          Number total1 = (Number) map1.get("total");
          Number total2 = (Number) map2.get("total");
          if (total1 == null) {
            total1 = 0;
          }
          if (total2 == null) {
            total2 = 0;
          }
          String order = request.getParameter("order");
          if (StringUtils.isNotBlank(order) && order.equals("desc")) {
            return Double.compare(total2.doubleValue(), total1.doubleValue());
          }
          return Double.compare(total1.doubleValue(), total2.doubleValue());
        });

    return R.ok().put("data", result);
  }

  /** （按值统计(多)） */
  @RequestMapping("/valueMul/{xColumnName}")
  public R valueMul(
      @PathVariable("xColumnName") String xColumnName,
      @RequestParam String yColumnNameMul,
      HttpServletRequest request) {
    String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("xColumn", MPUtil.camelToSnake(xColumnName));
    List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String, Object>>>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    for (int i = 0; i < yColumnNames.length; i++) {
      params.put("yColumn", yColumnNames[i]);
      List<Map<String, Object>> result =
          MPUtil.snakeListToCamel(newsService.selectValue(params, ew));
      for (Map<String, Object> m : result) {
        for (String k : m.keySet()) {
          if (m.get(k) instanceof Date) {
            m.put(k, sdf.format((Date) m.get(k)));
          }
        }
      }
      result2.add(result);
    }
    return R.ok().put("data", result2);
  }

  /** （按值统计）时间统计类型 */
  @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
  public R valueDay(
      @PathVariable("yColumnName") String yColumnName,
      @PathVariable("xColumnName") String xColumnName,
      @PathVariable("timeStatType") String timeStatType,
      HttpServletRequest request) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("xColumn", MPUtil.camelToSnake(xColumnName));
    params.put("yColumn", MPUtil.camelToSnake(yColumnName));
    params.put("timeStatType", timeStatType);
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    List<Map<String, Object>> result =
        MPUtil.snakeListToCamel(newsService.selectTimeStatValue(params, ew));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Map<String, Object> m : result) {
      for (String k : m.keySet()) {
        if (m.get(k) instanceof Date) {
          m.put(k, sdf.format((Date) m.get(k)));
        }
      }
    }
    return R.ok().put("data", result);
  }

  /** （按值统计）时间统计类型(多) */
  @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
  public R valueMulDay(
      @PathVariable("xColumnName") String xColumnName,
      @PathVariable("timeStatType") String timeStatType,
      @RequestParam String yColumnNameMul,
      HttpServletRequest request) {
    String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("xColumn", xColumnName);
    params.put("timeStatType", timeStatType);
    List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String, Object>>>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    for (int i = 0; i < yColumnNames.length; i++) {
      params.put("yColumn", yColumnNames[i]);
      List<Map<String, Object>> result =
          MPUtil.snakeListToCamel(newsService.selectTimeStatValue(params, ew));
      for (Map<String, Object> m : result) {
        for (String k : m.keySet()) {
          if (m.get(k) instanceof Date) {
            m.put(k, sdf.format((Date) m.get(k)));
          }
        }
      }
      result2.add(result);
    }
    return R.ok().put("data", result2);
  }

  /** 分组统计 */
  @RequestMapping("/group/{columnName}")
  public R group(@PathVariable("columnName") String columnName, HttpServletRequest request) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("column", MPUtil.camelToSnake(columnName));
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    List<Map<String, Object>> result = MPUtil.snakeListToCamel(newsService.selectGroup(params, ew));
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    for (Map<String, Object> m : result) {
      for (String k : m.keySet()) {
        if (m.get(k) instanceof Date) {
          m.put(k, sdf.format((Date) m.get(k)));
        }
      }
    }
    return R.ok().put("data", result);
  }

  /** 总数量 */
  @RequestMapping("/count")
  public R count(
      @RequestParam Map<String, Object> params, NewsEntity news, HttpServletRequest request) {
    EntityWrapper<NewsEntity> ew = new EntityWrapper<NewsEntity>();
    int count =
        newsService.selectCount(
            MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, news), params), params));
    return R.ok().put("data", count);
  }
}
