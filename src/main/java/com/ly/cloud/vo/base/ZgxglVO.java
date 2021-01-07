package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-03-21 02:32:40
 **/
public class ZgxglVO implements Serializable {
	private String zgxid;//资格项ID
	private String zgxmc;//资格项名称
	private String sszgmbdm;//所属资格目标，代码库ZGJD_SSZGMBDM,stu:学生，tea:教师，cla:班级，room:宿舍，other:其他
	private String hdfwid;//后端服务ID,关联表
	private String zgst;//资格视图
	private String zgtjzd;//资格条件字段
	private String zgtjzdlxdm;//资格条件字段类型代码，代码库ZGJD_ZGTJZDLXDM,01:文本，02：数字，03：下拉框，11：Boolean类型(是否)，12：Boolean类型(满足不满足)
	private String ms;//描述
	private String xlkbz;//下拉框标志
	private String yhtszd;//用户提示字段
	private String yhtsxx;//用户提示信息

    private static final long serialVersionUID = 1L;

	public String getZgxid() {
		return zgxid;
	}
	public void setZgxid(String zgxid) {
		this.zgxid = zgxid == null ? null : zgxid.trim(); 
		
	}
	public String getZgxmc() {
		return zgxmc;
	}
	public void setZgxmc(String zgxmc) {
		this.zgxmc = zgxmc == null ? null : zgxmc.trim(); 
		
	}
	public String getSszgmbdm() {
		return sszgmbdm;
	}
	public void setSszgmbdm(String sszgmbdm) {
		this.sszgmbdm = sszgmbdm == null ? null : sszgmbdm.trim(); 
		
	}
	public String getHdfwid() {
		return hdfwid;
	}
	public void setHdfwid(String hdfwid) {
		this.hdfwid = hdfwid == null ? null : hdfwid.trim(); 
		
	}
	public String getZgst() {
		return zgst;
	}
	public void setZgst(String zgst) {
		this.zgst = zgst == null ? null : zgst.trim(); 
		
	}
	public String getZgtjzd() {
		return zgtjzd;
	}
	public void setZgtjzd(String zgtjzd) {
		this.zgtjzd = zgtjzd == null ? null : zgtjzd.trim(); 
		
	}
	public String getZgtjzdlxdm() {
		return zgtjzdlxdm;
	}
	public void setZgtjzdlxdm(String zgtjzdlxdm) {
		this.zgtjzdlxdm = zgtjzdlxdm == null ? null : zgtjzdlxdm.trim(); 
		
	}
	public String getMs() {
		return ms;
	}
	public void setMs(String ms) {
		this.ms = ms == null ? null : ms.trim(); 
		
	}
	public String getXlkbz() {
		return xlkbz;
	}
	public void setXlkbz(String xlkbz) {
		this.xlkbz = xlkbz == null ? null : xlkbz.trim(); 
		
	}

	public String getYhtszd() {
		return yhtszd;
	}

	public void setYhtszd(String yhtszd) {
		this.yhtszd = yhtszd;
	}

	public String getYhtsxx() {
		return yhtsxx;
	}

	public void setYhtsxx(String yhtsxx) {
		this.yhtsxx = yhtsxx;
	}
}