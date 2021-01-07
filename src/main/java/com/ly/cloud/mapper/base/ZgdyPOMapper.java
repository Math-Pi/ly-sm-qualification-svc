package com.ly.cloud.mapper.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import com.ly.cloud.entity.base.ZgdyPO;
import com.ly.cloud.vo.base.ZgxLxVO;
import com.ly.cloud.vo.base.ZgxZgVO;

@Mapper
public interface ZgdyPOMapper extends BaseMapper<ZgdyPO> {
	
	public void insertPOBatch(@Param("list")List<ZgdyPO> list);
	public String getSszgmbdmByZgid(@Param("zgid") String zgid);
	/**
	 * 获取资格项的信息
	 * @param zgid
	 * @return
	 */
	public List<Map<String, Object>> selectZgxByZgid(@Param("zgid")String zgid);
	
	/**
	 * 根据资格项ID获取资格项的信息
	 * @param zgxid
	 * @return
	 */
	public Map<String, Object> selectZgxByZgxid(@Param("zgxid") String zgxid);
	
	/**
	 * 根据资格ID获取这个资格的所有信息
	 * @param zgxid
	 * @return
	 */
	public List<ZgxZgVO> selectAllZgxByZgxid(@Param("zgid") String zgid);
	
	/**
	 * 获取某一个分类下的所有的资格项信息
	 * @param flbz
	 *@param mbdm
	 *@param fdm
	 * @return
	 */
	public List<ZgxLxVO> selectZgxByFlmAndMbdm(@Param("flbz") String flbz,@Param("mbdm") String mbdm,@Param("fdm") String fdm, @Param("tagList") List<String> tagList);
	
	/**
	 * 获取当前学校代码
	 * @return
	 */
	public String selectDqxxInfo();
} 