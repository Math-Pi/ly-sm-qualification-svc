package com.ly.cloud.entity.base;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
@TableName("ZHXG_ZG_JTZGL")
public class JtzglPO {
	@TableId("JTZID")
	private String jtzid;//静态值ID
	@TableField(value="YSZMC",el="yszmc,jdbcType=VARCHAR")
	private String yszmc;//预设值名称
	@TableField(value="ZLXDM",el="zlxdm,jdbcType=VARCHAR")
	private String zlxdm;//值类型代码，代码库ZGJD_ZLXDM，01:文本
	@TableField(value="ZHQFSDM",el="zhqfsdm,jdbcType=VARCHAR")
	private String zhqfsdm;//值获取方式代码，代码库ZGJD_ZHQFSDM,01:sql语句查询
	@TableField(value="CSZ",el="csz,jdbcType=VARCHAR")
	private String csz;//参数值
	@TableField(value="MS",el="ms,jdbcType=VARCHAR")
	private String ms;//描述

	public String getJtzid() {
		return jtzid;
	}
	public void setJtzid(String jtzid) {
		this.jtzid = jtzid == null ? null : jtzid.trim(); 
	}
	public String getYszmc() {
		return yszmc;
	}
	public void setYszmc(String yszmc) {
		this.yszmc = yszmc == null ? null : yszmc.trim(); 
	}
	public String getZlxdm() {
		return zlxdm;
	}
	public void setZlxdm(String zlxdm) {
		this.zlxdm = zlxdm == null ? null : zlxdm.trim(); 
	}
	public String getZhqfsdm() {
		return zhqfsdm;
	}
	public void setZhqfsdm(String zhqfsdm) {
		this.zhqfsdm = zhqfsdm == null ? null : zhqfsdm.trim(); 
	}
	public String getCsz() {
		return csz;
	}
	public void setCsz(String csz) {
		this.csz = csz == null ? null : csz.trim(); 
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms == null ? null : ms.trim(); 
	}
}