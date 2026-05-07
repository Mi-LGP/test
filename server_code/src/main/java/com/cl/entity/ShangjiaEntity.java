package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商家 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("shangjia")
public class ShangjiaEntity<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  public ShangjiaEntity() {}

  public ShangjiaEntity(T t) {
    try {
      BeanUtils.copyProperties(this, t);
    } catch (IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /** 主键id */
  @TableId private Long id;

  /** 商家 */
  private String shangjia;

  /** 密码 */
  private String mima;

  /** 商家名称 */
  private String shangjiamingcheng;

  /** 性别 */
  private String xingbie;

  /** 头像 */
  private String touxiang;

  /** 年龄 */
  private Integer nianling;

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

  /** 设置：商家 */
  public void setShangjia(String shangjia) {
    this.shangjia = shangjia;
  }

  /** 获取：商家 */
  public String getShangjia() {
    return shangjia;
  }

  /** 设置：密码 */
  public void setMima(String mima) {
    this.mima = mima;
  }

  /** 获取：密码 */
  public String getMima() {
    return mima;
  }

  /** 设置：商家名称 */
  public void setShangjiamingcheng(String shangjiamingcheng) {
    this.shangjiamingcheng = shangjiamingcheng;
  }

  /** 获取：商家名称 */
  public String getShangjiamingcheng() {
    return shangjiamingcheng;
  }

  /** 设置：性别 */
  public void setXingbie(String xingbie) {
    this.xingbie = xingbie;
  }

  /** 获取：性别 */
  public String getXingbie() {
    return xingbie;
  }

  /** 设置：头像 */
  public void setTouxiang(String touxiang) {
    this.touxiang = touxiang;
  }

  /** 获取：头像 */
  public String getTouxiang() {
    return touxiang;
  }

  /** 设置：年龄 */
  public void setNianling(Integer nianling) {
    this.nianling = nianling;
  }

  /** 获取：年龄 */
  public Integer getNianling() {
    return nianling;
  }
}
