package com.ly.cloud.mapper.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ly.cloud.entity.base.ZgxflZgxPO;
import com.ly.cloud.vo.classification.ClassificationDetailVO;

@Mapper
public interface ZgxflZgxPOMapper extends BaseMapper<ZgxflZgxPO> {
	
	public void insertPOBatch(@Param("list")List<ZgxflZgxPO> list);
	
	
	public List<ClassificationDetailVO> queryDetail(@Param("fldm") String fldm);


	public List<String> deleteQualificationsByMulti(@Param("fldms") List<String> fldms);


	public void deleteByMultiFlid(@Param("flids") List<String> flids);


	public Integer insertMulti(@Param("list") List<ZgxflZgxPO> list);


	public String queryClassification(@Param("param")Map<String, String> param);


	public void deleteMultiByQual(@Param("zgxids")List<String> pkids);



	
} 