package com.ly.cloud.mapper.base;

import java.util.List;
import java.util.Map;

import com.ly.cloud.vo.base.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ly.cloud.entity.base.ZgxglGlgzPO;

@Mapper
public interface ZgxglGlgzPOMapper extends BaseMapper<ZgxglGlgzPO> {
	

	public void insertPOBatch(@Param("list")List<ZgxglGlgzPO> list);
	
	public void updatePOBatch(@Param("list")List<ZgxglGlgzPO> list);

	public List<Map<String, Object>> selectByZgid(@Param("zgid")String zgid);

	public String selectFwName(@Param("ssfw")String ssfw);

	public String selectSchoolName(@Param("xxdm")String xxdm);
	
	public List<String> selectPkid(@Param("zgxid")String zgxid);

	public List<ZgxflZgxVO> selectZgxList(@Param("xxdm")String xxdm,@Param("ssfw")String ssfw);

	public List<ZgxglVO> selectZgList(@Param("xxdm")String xxdm, @Param("ssfw")String ssfw);

	public List<ZgxglGlgzVO> selectGlgzList(@Param("xxdm")String xxdm, @Param("ssfw")String ssfw);
	public List<String> selectStList(@Param("xxdm")String xxdm, @Param("ssfw")String ssfw);
	public List<String> selectStListOnly(@Param("xxdm")String xxdm);

	public List<ZgxZgVO> selectZgxzgList(@Param("xxdm")String xxdm, @Param("ssfw")String ssfw);

	public List<ZgdyVO> selectZgdyList(@Param("xxdm")String xxdm, @Param("ssfw")String ssfw);

	public List<JtzglVO> selectJtzList();

	public List<ZgxflZgxVO> selectZgxListOnly(@Param("xxdm")String xxdm);

	public List<ZgxglVO> selectZgListOnly(@Param("xxdm")String xxdm);

	public List<ZgxglGlgzVO> selectGlgzListOnly(@Param("xxdm")String xxdm);

	public List<ZgxZgVO> selectZgxzgListOnly(@Param("xxdm")String xxdm);

	public List<ZgdyVO> selectZgdyListOnly(@Param("xxdm")String xxdm);

	public Integer updateHc(@Param("hcglgzid")String hcglgzid,@Param("glgzid")String glgzid);
	public List<Map<String,Object>> selectByZgxid(@Param("zgxid") String zgxid);

	public void deleteMultiByQual(@Param("zgxids")List<String> pkids);

	public List<Map<String, Object>> selectView(@Param("stList")List<String> stList);
} 