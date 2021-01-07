package com.ly.cloud.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.ZgxbqGxPO;

@Mapper
public interface ZgxbqGxPOMapper extends BaseMapper<ZgxbqGxPO> {
	
	public void insertPOBatch(@Param("list") List<ZgxbqGxPO> list);

    int deleteByTagId(@Param("tagIds") List<String> tagIds);
}