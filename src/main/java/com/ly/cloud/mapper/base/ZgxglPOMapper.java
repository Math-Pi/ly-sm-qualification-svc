package com.ly.cloud.mapper.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ly.cloud.entity.base.ZgxglGlgzPO;
import com.ly.cloud.entity.base.ZgxglPO;

@Mapper
public interface ZgxglPOMapper extends BaseMapper<ZgxglPO> {
	
	public void insertPOBatch(@Param("list")List<ZgxglPO> list);
	
	public List<String> getSsfw();
	
	public List<Map<String,String>> getAllGz(String pkid);
	
	public List<String> getZgst(String pkid);
	
	public List<String> getZgtjzd(String pkid);

	public Map<String,String> selectInitData(String pkid);
	
	//根据服务名称查id
	public String selectHtid(String ssfw);

	//保存操作的sql
	public List<Map<String,String>> getAllGzB(String zgxid);
	
	//新增
	public int insertGLGZ(ZgxglGlgzPO glgzpo);
	
	//删除
	public int deleteGLGZ(String glgzid);
	
	//修改
	public int updateGLGZ(@Param("hcglgzid")String hcglgzid ,@Param("glgzid")String glgzid);
} 