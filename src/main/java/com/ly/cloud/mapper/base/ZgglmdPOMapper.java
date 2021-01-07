package com.ly.cloud.mapper.base;

import java.util.List;
import java.util.Map;

import com.ly.cloud.dto.qualification.QualificationMatchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.ZgglmdPO;
import com.ly.cloud.vo.base.ZgglmdVO;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ZgglmdPOMapper extends BaseMapper<ZgglmdPO> {
	
	public void insertPOBatch(@Param("list")List<ZgglmdPO> list);
	
	 public void insertPoBySelectStuInfo(@Param("quid") String quid);
	 
	 public void insertPoBySelectTeInfo(@Param("quid") String quid);
	 public void insertPOBySelectBjInfo(@Param("quid") String quid);
	 
	 public void insertPoBySelectStuId(@Param("quid") String quid,@Param("id") String id);
	 
	 public void insertPoBySelectTeId(@Param("quid") String quid,@Param("id") String id);
	 public void insertPoBySelectBjId(@Param("quid") String quid,@Param("id") String id);
	 public void deleteByCjsj();
	 
	 public void deleteByPcid(@Param("pcid") String pcid);
	 
	 public ZgglmdVO getZgglmdByIdAndPcid(@Param("id") String id,@Param("pcid") String pcid);
	 
	 public List<ZgglmdVO> getAllZgglmdByPcid(@Param("pcid") String pcid);
	 
	 public List<ZgglmdVO> getAllFhzgmdByPcid(@Param("pcid") String pcid);
	 
	 public List<ZgglmdVO> getAllNofhmdByPcid(@Param("pcid") String pcid);
	 
	 public void updateZgglmd(@Param("updateSql") String updateSql);
	@Select("${sql}")
	List<QualificationMatchInfo> selectBySql(@Param("sql") String matchSql);

	/**
	 * 初始化该批次资格过滤名单
	 * @param pcid 批次id
	 * @param zt 初始状态 1：通过，0：不通过
	 */
	void insertStuInfoBySelect(@Param("pcid") String pcid,@Param("zt") String zt);
	/**
	 * 初始化该批次资格过滤名单
	 * @param pcid 批次id
	 * @param zt 初始状态 1：通过，0：不通过
	 */
	void insertTeaInfoBySelect(@Param("pcid") String pcid,@Param("zt") String zt);
	/**
	 * 初始化该批次资格过滤名单
	 * @param pcid 批次id
	 * @param zt 初始状态 1：通过，0：不通过
	 */
	void insertClaBySelect(@Param("pcid") String pcid,@Param("zt") String zt);
}