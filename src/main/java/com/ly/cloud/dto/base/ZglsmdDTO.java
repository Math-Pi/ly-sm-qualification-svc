package com.ly.cloud.dto.base;

import java.util.Date;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-11-12 02:01:08
 **/
public class ZglsmdDTO{
	private String pkid;//主键
	private String dxid;//对象ID,根据资格目标不同，此处存储的信息也不一样
	private Date cjsj;//创建时间
	private String pcid;//批次ID,表示唯一生成的id数据，使用UUID生成
	private String bfhtj;//不符合的条件
	private String zt;//状态
	private String fhtj;//符合条件

	public String getPkid() {
		return pkid;
	}
	public void setPkid(String pkid) {
		this.pkid = pkid == null ? null : pkid.trim(); 
		
	}
	public String getDxid() {
		return dxid;
	}
	public void setDxid(String dxid) {
		this.dxid = dxid == null ? null : dxid.trim(); 
		
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		 
		this.cjsj = cjsj;
	}
	public String getPcid() {
		return pcid;
	}
	public void setPcid(String pcid) {
		this.pcid = pcid == null ? null : pcid.trim(); 
		
	}
	public String getBfhtj() {
		return bfhtj;
	}
	public void setBfhtj(String bfhtj) {
		this.bfhtj = bfhtj == null ? null : bfhtj.trim(); 
		
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt == null ? null : zt.trim(); 
		
	}
	public String getFhtj() {
		return fhtj;
	}
	public void setFhtj(String fhtj) {
		this.fhtj = fhtj == null ? null : fhtj.trim(); 
		
	}
}