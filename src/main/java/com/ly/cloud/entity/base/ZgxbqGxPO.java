package com.ly.cloud.entity.base;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-11-13 09:59:18
 **/
@TableName("ZHXG_ZG_ZGXBQ_GX")
public class ZgxbqGxPO {
	@TableId("PKID")
	private String pkid;//主键
	@TableField(value="ZGXID",el="zgxid,jdbcType=VARCHAR")
	private String zgxid;//资格项id
	@TableField(value="BQBZ",el="bqbz,jdbcType=VARCHAR")
	private String bqbz;//标签标志

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