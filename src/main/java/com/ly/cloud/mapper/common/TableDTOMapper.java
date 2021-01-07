package com.ly.cloud.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;


@Mapper
public interface TableDTOMapper{
	
	@Select("${sql}")
	public List<Map<String,Object>> selectPageBySql(@Param("page")Pagination page,@Param("sql") String sql);
	
	@Select("${sql}")
	public List<Map<String,Object>> selectListBySql(@Param("sql") String sql);
	
	@Select("${sql}")
	public List<String> selectListStrSql(@Param("sql") String sql);

	@Select("select t.mc from ZHXG_XTGL_DMK_CL t " + 
			"		left join ZHXG_XTGL_DMK_FL t1 on t1.pkid=t.flid " + 
			"		where upper(t1.dmbz)=upper(#{dmbz,jdbcType=VARCHAR})")
	public List<String> selectListStrDmk(@Param("dmbz") String dmbz);
	
	@Select("select t.dm as text,t.mc as key from ZHXG_XTGL_DMK_CL t" + 
			"		left join ZHXG_XTGL_DMK_FL t1 on t1.pkid=t.flid" + 
			"		where upper(t1.dmbz)=upper(#{dmbz,jdbcType=VARCHAR})")
	public List<Map<String,Object>> selectTranslateListDmk(@Param("dmbz") String dmbz);
	
	@Select("select t.csbz,t.csz from VC_ZHXG_XTGL_CSSZ t")
	public List<Map<String,Object>> selectSysProperties();
	
	@Select(" select * from ( " + 
			" 		select t.bm,t.blm,t.sjhqfsdm,t.stlm from wfwdtbd.zhxg_dtbd_zd t " + 
			" 		left join wfwdtbd.zhxg_dtbd_zdfl t1 on t1.zdflid=t.zdflid " + 
			" 		where  t.sjhqfsdm not in ('01','02') " + 
			" 		group by t.bm,t.blm,t.sjhqfsdm,t.stlm " + 
			" 		) where bm=#{bm} ")
	public List<Map<String,Object>>queryBmPkidByBm(@Param("bm") String bm);
	 //查询数据是否存在
	 @Select("${sql}")
	 public String queryZdInfoIFYesBySql(@Param("sql") String sql);
	 //进行更新
	 @Update("${sql}")
	 public Integer updateInfo(@Param("sql") String sql);
	 //进行插入操作
	 @Insert("${sql}")
	 public Integer insertInfo(@Param("sql") String sql);
	 //进行一对多字段删除操作
	 @Delete("${sql}")
	 public Integer deleteOneOnManyByPkid(@Param("sql") String sql);
	 //根据表名获取相关的外键约束
	 @Select("select constraint_name from user_constraints where table_name=#{bm} and constraint_type='R'")
	 public List<String> queryConstraintsByBm(@Param("bm") String bm);
	 //根据外键名称禁用外键
	 @Select("${sql}")
	 public void disableConstraint(@Param("sql") String sql);
	 @Select("${sql}")
	 public void enableConstraint(@Param("sql") String sql);
	 //根据学号获取学生ID
	 @Select("select pkid from zhxg_xsxx_xsjbxx  where xh=#{userId}")
	 public String queryStudentPkid(@Param("userId") String userId);
	 //根据工号获取老师pkid
	 @Select("select pkid from ZHXG_XGDW_JSJBXX where gh=#{userId}")
	 public String queryTeacherPkid(@Param("userId") String userId);
		
}