package com.ly.cloud.vo.classification;

import java.io.Serializable;

public class ClassificationDetailVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String flid;	// 分类ID
	private String fldm;	// 分类代码
	private String flmc;	// 分类名称
	private String xxmc;	// 学校名称
	private String fdm;		// 父代码（学校代码）
	private String zgxnum;	// 资格项个数
	public String getFlid() {
		return flid;
	}
	public void setFlid(String flid) {
		this.flid = flid;
	}
	public String getFldm() {
		return fldm;
	}
	public void setFldm(String fldm) {
		this.fldm = fldm;
	}
	public String getFlmc() {
		return flmc;
	}
	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}
	public String getXxmc() {
		return xxmc;
	}
	public void setXxmc(String xxmc) {
		this.xxmc = xxmc;
	}
	public String getFdm() {
		return fdm;
	}
	public void setFdm(String fdm) {
		this.fdm = fdm;
	}
	public String getZgxnum() {
		return zgxnum;
	}
	public void setZgxnum(String zgxnum) {
		this.zgxnum = zgxnum;
	}
	
	

}
