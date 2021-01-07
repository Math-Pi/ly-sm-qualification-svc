package com.ly.cloud.entity.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-04-29 05:18:01
 **/
@TableName("ZHXG_ZG_ZGDY")
public class ZgdyPO {
	@TableId("ZGID")
	private String zgid;//资格ID
	@TableField(value="ZGMC",el="zgmc,jdbcType=VARCHAR")
	private String zgmc;//资格名称
	@TableField(value="SSZGMBDM",el="sszgmbdm,jdbcType=VARCHAR")
	private String sszgmbdm;//所属资格目标，代码库ZGJD_SSZGMBDM,stu:学生，tea:教师，cla:班级，room:宿舍，other:其他
	@TableField(value="ZHXGSJ",el="zhxgsj,jdbcType=TIMESTAMP")
	private Date zhxgsj;//最后修改时间

	public String getZgid() {
		return zgid;
	}
	public void setZgid(String zgid) {
		this.zgid = zgid == null ? null : zgid.trim(); 
	}
	public String getZgmc() {
		return zgmc;
	}
	public void setZgmc(String zgmc) {
		this.zgmc = zgmc == null ? null : zgmc.trim(); 
	}
	public String getSszgmbdm() {
		return sszgmbdm;
	}
	public void setSszgmbdm(String sszgmbdm) {
		this.sszgmbdm = sszgmbdm == null ? null : sszgmbdm.trim(); 
	}
	public Date getZhxgsj() {
		return zhxgsj;
	}
	public void setZhxgsj(Date zhxgsj) {
		this.zhxgsj = zhxgsj; 
	}
}