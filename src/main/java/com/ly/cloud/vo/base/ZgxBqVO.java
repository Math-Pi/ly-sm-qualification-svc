package com.ly.cloud.vo.base;

import java.io.Serializable;


/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-11-12 01:49:15
 **/
public class ZgxBqVO implements Serializable {
	private String pkid;//主键
	private String bqbz;//标签标志
	private String bqmc;//标签名称

    private static final long serialVersionUID = 1L;

	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid == null ? null : pkid.trim(); 
		
	}
	public String getBqbz() {
		return bqbz;
	}
	public void setBqbz(String bqbz) {
		this.bqbz = bqbz == null ? null : bqbz.trim(); 
		
	}
	public String getBqmc() {
		return bqmc;
	}
	public void setBqmc(String bqmc) {
		this.bqmc = bqmc == null ? null : bqmc.trim(); 
		
	}
}