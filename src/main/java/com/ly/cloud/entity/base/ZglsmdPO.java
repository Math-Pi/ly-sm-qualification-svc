package com.ly.cloud.entity.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-11-12 02:01:08
 **/
@TableName("ZHXG_ZG_ZGLSMD")
public class ZglsmdPO {
	@TableId("PKID")
	private String pkid;//主键
	@TableField(value="DXID",el="dxid,jdbcType=VARCHAR")
	private String dxid;//对象ID,根据资格目标不同，此处存储的信息也不一样
	@TableField(value="CJSJ",el="cjsj,jdbcType=TIMESTAMP")
	private Date cjsj;//创建时间
	@TableField(value="PCID",el="pcid,jdbcType=VARCHAR")
	private String pcid;//批次ID,表示唯一生成的id数据，使用UUID生成
	@TableField(value="BFHTJ",el="bfhtj,jdbcType=VARCHAR")
	private String bfhtj;//不符合的条件
	@TableField(value="ZT",el="zt,jdbcType=VARCHAR")
	private String zt;//状态
	@TableField(value="FHTJ",el="fhtj,jdbcType=VARCHAR")
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