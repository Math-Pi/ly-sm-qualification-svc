package com.ly.cloud.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ly.cloud.entity.base.ZgxZgPO;

@Mapper
public interface ZgxZgPOMapper extends BaseMapper<ZgxZgPO> {
	
	public void insertPOBatch(@Param("list")List<ZgxZgPO> list);

	public void deleteByZgid(@Param("zgid")String zgid);

	public void deleteByZgids(@Param("quaIds") List<String> quaIds);

	public void deleteMultiByQual(@Param("zgxids")List<String> pkids);
	
	
} 