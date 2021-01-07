package com.ly.cloud.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.JtzglPO;

@Mapper
public interface JtzglPOMapper extends BaseMapper<JtzglPO> {
	
	public void insertPOBatch(@Param("list")List<JtzglPO> list);
	
	@Select("select count(1) from ZHXG_ZG_ZGXGL_GLGZ t where t.CSZ=#{pkid}")
	public int checkReference(@Param("pkid") String pkid);
	
} 