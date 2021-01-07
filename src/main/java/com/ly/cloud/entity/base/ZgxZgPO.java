package com.ly.cloud.entity.base;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
@TableName("ZHXG_ZG_ZGX_ZG")
public class ZgxZgPO {
	@TableId("ZGXDYID")
	private String zgxdyid;//资格项定义ID
	@TableField(value="ZGID",el="zgid,jdbcType=VARCHAR")
	private String zgid;//资格ID
	@TableField(value="ZGXID",el="zgxid,jdbcType=VARCHAR")
	private String zgxid;//资格项ID
	@TableField(value="ZGXDYMC",el="zgxdymc,jdbcType=VARCHAR")
	private String zgxdymc;//资格项定义名称
	@TableField(value="YSFDM",el="ysfdm,jdbcType=VARCHAR")
	private String ysfdm;//运算符代码，代码库ZGJD_YSFDM,01：大于、02：大于小于、03：等于、04:小于等于、05：小于、07：包含
	@TableField(value="CSZ",el="csz,jdbcType=VARCHAR")
	private String csz;//参数值
	@TableField(value="PXH",el="pxh,jdbcType=DECIMAL")
	private Integer pxh;//排序号
	@TableField(value="HCZGXDYID",el="hczgxdyid,jdbcType=VARCHAR")
	private String hczgxdyid;//互斥资格项定义ID

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