package com.ly.cloud.dto.base;

import java.util.Date;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-04-29 05:18:01
 **/
public class ZgdyDTO{
	private String zgid;//资格ID
	private String zgmc;//资格名称
	private String sszgmbdm;//所属资格目标，代码库ZGJD_SSZGMBDM,stu:学生，tea:教师，cla:班级，room:宿舍，other:其他
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