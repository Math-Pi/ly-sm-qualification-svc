package com.ly.cloud.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.ZglsmdPO;
import com.ly.cloud.vo.base.ZglsmdVO;

@Mapper
public interface ZglsmdPOMapper extends BaseMapper<ZglsmdPO> {
	
	public void insertPOBatch(@Param("list")List<ZglsmdPO> list);
	
	public void insertPoByIdAndPcid(@Param("id") String id,@Param("pcid") String pcid);
	
	public void insertFhPoByPcid(@Param("pcid") String pcid);
	
	public void insertAllPoByPcid(@Param("pcid") String pcid);
	
	public void insertNoFhPoByPcid(@Param("pcid") String pcid);
	
	public void deleteByCjsj();
	
	public void deleteByPcid(@Param("pcid") String pcid);
	
	public List<ZglsmdVO> selectLsmdByPcid(@Param("pcid") String pcid);

	public Integer selectDataCount(@Param("pcid") String pcid);
} 