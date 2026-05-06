package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 拼团活动
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
@TableName("tuangou_activity")
public class TuangouActivityEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public TuangouActivityEntity() {
		
	}
	
	public TuangouActivityEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键 id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 商品 ID
	 */
					
	private Long goodsid;
	
	/**
	 * 商品名称
	 */
					
	private String goodsname;
	
	/**
	 * 商品图片
	 */
					
	private String picture;
	
	/**
	 * 拼团编号
	 */
					
	private String groupono;
	
	/**
	 * 团购价
	 */
					
	private Double groupprice;
	
	/**
	 * 要求拼团人数
	 */
					
	private Integer grouppeople;
	
	/**
	 * 当前拼团人数
	 */
					
	private Integer curpeople;
	
	/**
	 * 拼团状态
	 */
					
	private String status;
	
	/**
	 * 团长用户 ID
	 */
					
	private Long leaderid;
	
	/**
	 * 团长姓名
	 */
					
	private String leadername;
	
	/**
	 * 拼团结束时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date endtime;


	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
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
	/**
	 * 设置：商品 ID
	 */
	public void setGoodsid(Long goodsid) {
		this.goodsid = goodsid;
	}
	/**
	 * 获取：商品 ID
	 */
	public Long getGoodsid() {
		return goodsid;
	}
	/**
	 * 设置：商品名称
	 */
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	/**
	 * 获取：商品名称
	 */
	public String getGoodsname() {
		return goodsname;
	}
	/**
	 * 设置：商品图片
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * 获取：商品图片
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * 设置：拼团编号
	 */
	public void setGroupono(String groupono) {
		this.groupono = groupono;
	}
	/**
	 * 获取：拼团编号
	 */
	public String getGroupono() {
		return groupono;
	}
	/**
	 * 设置：团购价
	 */
	public void setGroupprice(Double groupprice) {
		this.groupprice = groupprice;
	}
	/**
	 * 获取：团购价
	 */
	public Double getGroupprice() {
		return groupprice;
	}
	/**
	 * 设置：要求拼团人数
	 */
	public void setGrouppeople(Integer grouppeople) {
		this.grouppeople = grouppeople;
	}
	/**
	 * 获取：要求拼团人数
	 */
	public Integer getGrouppeople() {
		return grouppeople;
	}
	/**
	 * 设置：当前拼团人数
	 */
	public void setCurpeople(Integer curpeople) {
		this.curpeople = curpeople;
	}
	/**
	 * 获取：当前拼团人数
	 */
	public Integer getCurpeople() {
		return curpeople;
	}
	/**
	 * 设置：拼团状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：拼团状态
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：团长用户 ID
	 */
	public void setLeaderid(Long leaderid) {
		this.leaderid = leaderid;
	}
	/**
	 * 获取：团长用户 ID
	 */
	public Long getLeaderid() {
		return leaderid;
	}
	/**
	 * 设置：团长姓名
	 */
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	/**
	 * 获取：团长姓名
	 */
	public String getLeadername() {
		return leadername;
	}
	/**
	 * 设置：拼团结束时间
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：拼团结束时间
	 */
	public Date getEndtime() {
		return endtime;
	}

}
