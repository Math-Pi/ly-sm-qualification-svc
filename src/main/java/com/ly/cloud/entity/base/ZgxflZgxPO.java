package com.ly.cloud.entity.base;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
@TableName("ZHXG_ZG_ZGXFL_ZGX")
public class ZgxflZgxPO {
	@TableId("PKID")
	private String pkid;//主键
	@TableField(value="ZGXID",el="zgxid,jdbcType=VARCHAR")
	private String zgxid;//资格项ID
	@TableField(value="FLID",el="flid,jdbcType=VARCHAR")
	private String flid;//分类ID

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