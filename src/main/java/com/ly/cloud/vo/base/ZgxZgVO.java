package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
public class ZgxZgVO implements Serializable {
	private String zgxdyid;//资格项定义ID
	private String zgid;//资格ID
	private String zgxid;//资格项ID
	private String zgxdymc;//资格项定义名称
	private String ysfdm;//运算符代码，代码库ZGJD_YSFDM,01：大于、02：大于小于、03：等于、04:小于等于、05：小于、07：包含
	private String csz;//参数值
	private Integer pxh;//排序号
	private String hczgxdyid;//互斥资格项定义ID

    private static final long serialVersionUID = 1L;

	public String getZgxdyid() {
		return zgxdyid;
	}
	public void setZgxdyid(String zgxdyid) {
		this.zgxdyid = zgxdyid == null ? null : zgxdyid.trim(); 
		
	}
	public String getZgid() {
		return zgid;
	}
	public void setZgid(String zgid) {
		this.zgid = zgid == null ? null : zgid.trim(); 
		
	}
	public String getZgxid() {
		return zgxid;
	}
	public void setZgxid(String zgxid) {
		this.zgxid = zgxid == null ? null : zgxid.trim(); 
		
	}
	public String getZgxdymc() {
		return zgxdymc;
	}
	public void setZgxdymc(String zgxdymc) {
		this.zgxdymc = zgxdymc == null ? null : zgxdymc.trim(); 
		
	}
	public String getYsfdm() {
		return ysfdm;
	}
	public void setYsfdm(String ysfdm) {
		this.ysfdm = ysfdm == null ? null : ysfdm.trim(); 
		
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz == null ? null : csz.trim(); 
		
	}
	public Integer getPxh() {
		return pxh;
	}
	public void setPxh(Integer pxh) {
		 
		this.pxh = pxh;
	}
	public String getHczgxdyid() {
		return hczgxdyid;
	}
	public void setHczgxdyid(String hczgxdyid) {
		this.hczgxdyid = hczgxdyid == null ? null : hczgxdyid.trim(); 
		
	}
}