package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
public class ZgxflZgxVO implements Serializable {
	private String pkid;//主键
	private String zgxid;//资格项ID
	private String flid;//分类ID

    private static final long serialVersionUID = 1L;

	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid == null ? null : pkid.trim(); 
		
	}
	public String getZgxid() {
		return zgxid;
	}
	public void setZgxid(String zgxid) {
		this.zgxid = zgxid == null ? null : zgxid.trim(); 
		
	}
	public String getFlid() {
		return flid;
	}
	public void setFlid(String flid) {
		this.flid = flid == null ? null : flid.trim(); 
		
	}
}