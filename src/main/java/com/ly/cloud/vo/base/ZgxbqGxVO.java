package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-11-13 09:59:18
 **/
public class ZgxbqGxVO implements Serializable {
	private String pkid;//主键
	private String zgxid;//资格项id
	private String bqbz;//标签标志

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
	public String getBqbz() {
		return bqbz;
	}
	public void setBqbz(String bqbz) {
		this.bqbz = bqbz == null ? null : bqbz.trim(); 
		
	}
}