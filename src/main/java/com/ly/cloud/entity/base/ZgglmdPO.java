package com.ly.cloud.entity.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 本文件由表实体生成插件生成，插件版本 v1.0<br>
 * 生成日期：2019-02-26 03:41:07
 **/
@TableName("ZHXG_ZG_ZGGLMD")
public class ZgglmdPO {
	@TableId("ID")
	private String id;//对象ID
	@TableId("PCID")
	private String pcid;//批次ID
	@TableField(value="BFHTJ",el="bfhtj,jdbcType=VARCHAR")
	private String bfhtj;//不符合条件
	@TableField(value="ZT",el="zt,jdbcType=VARCHAR")
	private String zt;//状态
	@TableField(value="CJSJ",el="cjsj,jdbcType=TIMESTAMP")
	private Date cjsj;//创建时间

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id == null ? null : id.trim(); 
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
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj; 
	}
}