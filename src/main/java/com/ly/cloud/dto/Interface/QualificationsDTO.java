package com.ly.cloud.dto.Interface;

import java.util.List;

import com.ly.cloud.dto.base.ZgxZgDTO;

public class QualificationsDTO {
	private String zgid;//资格ID
	private String zgmc;//资格名称
	private String sszgmbdm;//所属资格目标代码；(stu,tea,cla,room)
	private List<ZgxZgDTO> zgxList;//资格项信息
	
	public String getZgid() {
		return zgid;
	}
	public void setZgid(String zgid) {
		this.zgid = zgid;
	}
	public String getZgmc() {
		return zgmc;
	}
	public void setZgmc(String zgmc) {
		this.zgmc = zgmc;
	}
	public String getSszgmbdm() {
		return sszgmbdm;
	}
	public void setSszgmbdm(String sszgmbdm) {
		this.sszgmbdm = sszgmbdm;
	}
	public List<ZgxZgDTO> getZgxList() {
		return zgxList;
	}
	public void setZgxList(List<ZgxZgDTO> zgxList) {
		this.zgxList = zgxList;
	}
}
