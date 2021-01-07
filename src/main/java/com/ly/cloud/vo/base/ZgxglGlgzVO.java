package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
public class ZgxglGlgzVO implements Serializable {
	private String glgzid;//过滤规则ID
	private String glgzmc;//过滤规则名称
	private String zgxid;//资格项ID，关联表ZHXG_ZG_ZGXGL的ZGXID字段
	private String zd;//字段
	private String ysfdm;//运算符代码，代码库ZGJD_YSFDM,>：大于、>=：大于小于、=：等于、<=:小于等于、<：小于、like：包含,!=:不等于、not like:不包含
	private String zhqgzdm;//值获取规则代码，代码库ZGJD_ZHQGZDM，01：固定值，02：静态值、03：平台参数
	private String csz;//参数值
	private String hcglgzid;//互斥过滤规则ID
	private String zdlxdm;//字段类型代码,代码库ZGJD_ZDLX,01:文本、02：数字、03：日期

    private static final long serialVersionUID = 1L;

	public String getGlgzid() {
		return glgzid;
	}
	public void setGlgzid(String glgzid) {
		this.glgzid = glgzid == null ? null : glgzid.trim(); 
		
	}
	public String getGlgzmc() {
		return glgzmc;
	}
	public void setGlgzmc(String glgzmc) {
		this.glgzmc = glgzmc == null ? null : glgzmc.trim(); 
		
	}
	public String getZgxid() {
		return zgxid;
	}
	public void setZgxid(String zgxid) {
		this.zgxid = zgxid == null ? null : zgxid.trim(); 
		
	}
	public String getZd() {
		return zd;
	}
	public void setZd(String zd) {
		this.zd = zd == null ? null : zd.trim(); 
		
	}
	public String getYsfdm() {
		return ysfdm;
	}
	public void setYsfdm(String ysfdm) {
		this.ysfdm = ysfdm == null ? null : ysfdm.trim(); 
		
	}
	public String getZhqgzdm() {
		return zhqgzdm;
	}
	public void setZhqgzdm(String zhqgzdm) {
		this.zhqgzdm = zhqgzdm == null ? null : zhqgzdm.trim(); 
		
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz == null ? null : csz.trim(); 
		
	}
	public String getHcglgzid() {
		return hcglgzid;
	}
	public void setHcglgzid(String hcglgzid) {
		this.hcglgzid = hcglgzid == null ? null : hcglgzid.trim(); 
		
	}
	public String getZdlxdm() {
		return zdlxdm;
	}
	public void setZdlxdm(String zdlxdm) {
		this.zdlxdm = zdlxdm == null ? null : zdlxdm.trim(); 
		
	}
}