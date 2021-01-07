package com.ly.cloud.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.ZgxBqPO;

@Mapper
public interface ZgxBqPOMapper extends BaseMapper<ZgxBqPO> {
	
	public void insertPOBatch(@Param("list") List<ZgxBqPO> list);
	
} 