package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品信息 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("shangpinxinxi")
public class ShangpinxinxiEntity<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  public ShangpinxinxiEntity() {}

  public ShangpinxinxiEntity(T t) {
    try {
      BeanUtils.copyProperties(this, t);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /** 主键id */
  @TableId(type = IdType.AUTO)
  private Long id;

  /** 商品编号 */
  private String shangpinbianhao;

  /** 商品名称 */
  private String shangpinmingcheng;

  /** 商品图片 */
  private String shangpintupian;

  /** 商品详情 */
  private String shangpinxiangqing;

  /** 规格 */
  private String guige;

  /** 保质期 */
  private String baozhiqi;

  /** 商品分类 */
  private String shangpinfenlei;

  /** 商家 */
  private String shangjia;

  /** 价格 */
  private Double price;

  /** 收藏数 */
  private Integer storeupNumber;

  /** 评论数 */
  private Integer discussNumber;

  /** 单限 */
  private Integer onelimittimes;

  /** 库存 */
  private Integer alllimittimes;

  @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat
  private Date addtime;

  public Date getAddtime() {
    return addtime;
  }

  public void setAddtime(Date addtime) {
    this.addtime = addtime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /** 设置：商品编号 */
  public void setShangpinbianhao(String shangpinbianhao) {
    this.shangpinbianhao = shangpinbianhao;
  }

  /** 获取：商品编号 */
  public String getShangpinbianhao() {
    return shangpinbianhao;
  }

  /** 设置：商品名称 */
  public void setShangpinmingcheng(String shangpinmingcheng) {
    this.shangpinmingcheng = shangpinmingcheng;
  }

  /** 获取：商品名称 */
  public String getShangpinmingcheng() {
    return shangpinmingcheng;
  }

  /** 设置：商品图片 */
  public void setShangpintupian(String shangpintupian) {
    this.shangpintupian = shangpintupian;
  }

  /** 获取：商品图片 */
  public String getShangpintupian() {
    return shangpintupian;
  }

  /** 设置：商品详情 */
  public void setShangpinxiangqing(String shangpinxiangqing) {
    this.shangpinxiangqing = shangpinxiangqing;
  }

  /** 获取：商品详情 */
  public String getShangpinxiangqing() {
    return shangpinxiangqing;
  }

  /** 设置：规格 */
  public void setGuige(String guige) {
    this.guige = guige;
  }

  /** 获取：规格 */
  public String getGuige() {
    return guige;
  }

  /** 设置：保质期 */
  public void setBaozhiqi(String baozhiqi) {
    this.baozhiqi = baozhiqi;
  }

  /** 获取：保质期 */
  public String getBaozhiqi() {
    return baozhiqi;
  }

  /** 设置：商品分类 */
  public void setShangpinfenlei(String shangpinfenlei) {
    this.shangpinfenlei = shangpinfenlei;
  }

  /** 获取：商品分类 */
  public String getShangpinfenlei() {
    return shangpinfenlei;
  }

  /** 设置：商家 */
  public void setShangjia(String shangjia) {
    this.shangjia = shangjia;
  }

  /** 获取：商家 */
  public String getShangjia() {
    return shangjia;
  }

  /** 设置：价格 */
  public void setPrice(Double price) {
    this.price = price;
  }

  /** 获取：价格 */
  public Double getPrice() {
    return price;
  }

  /** 设置：收藏数 */
  public void setStoreupNumber(Integer storeupNumber) {
    this.storeupNumber = storeupNumber;
  }

  /** 获取：收藏数 */
  public Integer getStoreupNumber() {
    return storeupNumber;
  }

  /** 设置：评论数 */
  public void setDiscussNumber(Integer discussNumber) {
    this.discussNumber = discussNumber;
  }

  /** 获取：评论数 */
  public Integer getDiscussNumber() {
    return discussNumber;
  }

  /** 设置：单限 */
  public void setOnelimittimes(Integer onelimittimes) {
    this.onelimittimes = onelimittimes;
  }

  /** 获取：单限 */
  public Integer getOnelimittimes() {
    return onelimittimes;
  }

  /** 设置：库存 */
  public void setAlllimittimes(Integer alllimittimes) {
    this.alllimittimes = alllimittimes;
  }

  /** 获取：库存 */
  public Integer getAlllimittimes() {
    return alllimittimes;
  }
}
