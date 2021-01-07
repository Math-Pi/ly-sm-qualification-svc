package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.base.ZgglmdDTO;
import com.ly.cloud.vo.base.ZgglmdVO;

public interface ZgglmdService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: myp
	 * @date: 2019-02-20 10:51:24
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgglmdVO
	 * @throws
	 */
	public ZgglmdVO get(String pkid);
	
	/**
	 * @Title: insert
	 * @Description: 插入单表的信息
	 * @author: myp
	 * @date: 2019-02-20 10:51:24
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer insert(ZgglmdDTO dto);
	
	/**
	 * @Title: update
	 * @Description: 更新一条数据
	 * @author: myp
	 * @date: 2019-02-20 10:51:24
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer update(ZgglmdDTO dto); 
	
	/**
	 * @Title: deleteByMulti
	 * @Description: 批量删除多条数据
	 * @author: myp
	 * @date: 2019-02-20 10:51:24
	 * @param: @param pkids
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int deleteByMulti(List<String> pkids);
	/**
	 * 根据批次ID进行插入学生临时名单
	 */
	public void insertPoBySelectStuInfo(String quid);
	/**
	 * 根据批次ID进行插入老师临时名单
	 */
	public void insertPoBySelectTeInfo(String quid);
	/**
	 * 根据批次ID和对象ID进行插入学生临时名单
	 */
	public void insertPoBySelectStuId(String quid,String id);
	
	/**
	 * 根据批次ID和对象ID进行插入老师临时名单
	 */
	public void insertPoBySelectTeId(String quid,String id);
	
	/**
	 * 把创建时间大于30分钟的数据定时清空
	 */
	public void deleteByCjsj();
	
	/**
	 * 根据对象ID和批次ID查询对象的对象结果数据 
	 */
	public ZgglmdVO getZgglmdByIdAndPcid(String id,String pcid);
	
	/**
	 * 根据批次ID返回所有数据
	 */
	public List<ZgglmdVO> getAllZgglmdByPcid(String pcid);
	
	/**
	 * 根据批次ID返回所有符合资格的数据
	 */
	public List<ZgglmdVO> getAllFhzgmdByPcid(String pcid);
	
	/**
	 * 根据批次ID返回所有不符合资格的数据
	 */
	public List<ZgglmdVO> getAllNofhmdByPcid(String pcid);
	
	public void deleteByPcid(String pcid);
	
	public void updateZgglmd(String updateSql);
}
