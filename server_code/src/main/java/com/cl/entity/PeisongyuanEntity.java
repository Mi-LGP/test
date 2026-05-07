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
 * 配送员 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-04-06 15:12:26
 */
@TableName("peisongyuan")
public class PeisongyuanEntity<T> implements Serializable {
  private static final long serialVersionUID = 1L;

  public PeisongyuanEntity() {}

  public PeisongyuanEntity(T t) {
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

  /** 配送员 */
  private String peisongyuan;

  /** 姓名 */
  private String xingming;

  /** 联系方式 */
  private String lianxifangshi;

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

  /** 设置：配送员 */
  public void setPeisongyuan(String peisongyuan) {
    this.peisongyuan = peisongyuan;
  }

  /** 获取：配送员 */
  public String getPeisongyuan() {
    return peisongyuan;
  }

  /** 设置：姓名 */
  public void setXingming(String xingming) {
    this.xingming = xingming;
  }

  /** 获取：姓名 */
  public String getXingming() {
    return xingming;
  }

  /** 设置：联系方式 */
  public void setLianxifangshi(String lianxifangshi) {
    this.lianxifangshi = lianxifangshi;
  }

  /** 获取：联系方式 */
  public String getLianxifangshi() {
    return lianxifangshi;
  }
}
