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
 * 订单配送
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
@TableName("dingdanpeisong")
public class DingdanpeisongEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public DingdanpeisongEntity() {
		
	}
	
	public DingdanpeisongEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 商品名称
	 */
					
	private String shangpinmingcheng;
	
	/**
	 * 用户
	 */
					
	private String yonghu;
	
	/**
	 * 姓名
	 */
					
	private String xingming;
	
	/**
	 * 配送状态
	 */
					
	private String peisongzhuangtai;
	
	/**
	 * 更新时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date gengxinshijian;
	
	/**
	 * 配送员
	 */
					
	private String peisongyuan;
	
	/**
	 * 商家
	 */
					
	private String shangjia;
	

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
	 * 设置：商品名称
	 */
	public void setShangpinmingcheng(String shangpinmingcheng) {
		this.shangpinmingcheng = shangpinmingcheng;
	}
	/**
	 * 获取：商品名称
	 */
	public String getShangpinmingcheng() {
		return shangpinmingcheng;
	}
	/**
	 * 设置：用户
	 */
	public void setYonghu(String yonghu) {
		this.yonghu = yonghu;
	}
	/**
	 * 获取：用户
	 */
	public String getYonghu() {
		return yonghu;
	}
	/**
	 * 设置：姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：姓名
	 */
	public String getXingming() {
		return xingming;
	}
	/**
	 * 设置：配送状态
	 */
	public void setPeisongzhuangtai(String peisongzhuangtai) {
		this.peisongzhuangtai = peisongzhuangtai;
	}
	/**
	 * 获取：配送状态
	 */
	public String getPeisongzhuangtai() {
		return peisongzhuangtai;
	}
	/**
	 * 设置：更新时间
	 */
	public void setGengxinshijian(Date gengxinshijian) {
		this.gengxinshijian = gengxinshijian;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getGengxinshijian() {
		return gengxinshijian;
	}
	/**
	 * 设置：配送员
	 */
	public void setPeisongyuan(String peisongyuan) {
		this.peisongyuan = peisongyuan;
	}
	/**
	 * 获取：配送员
	 */
	public String getPeisongyuan() {
		return peisongyuan;
	}
	/**
	 * 设置：商家
	 */
	public void setShangjia(String shangjia) {
		this.shangjia = shangjia;
	}
	/**
	 * 获取：商家
	 */
	public String getShangjia() {
		return shangjia;
	}

}
