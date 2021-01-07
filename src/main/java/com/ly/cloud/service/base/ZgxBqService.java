package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.base.ZgxBqDTO;
import com.ly.cloud.dto.tag.QualificationTagDTO;
import com.ly.cloud.vo.base.ZgxBqVO;

public interface ZgxBqService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: Administrator
	 * @date: 2019-11-12 01:49:15
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgxBqVO
	 * @throws
	 */
	public ZgxBqVO get(String pkid);
	
	/**
	 * @Title: insert
	 * @Description: 插入单表的信息
	 * @author: Administrator
	 * @date: 2019-11-12 01:49:15
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer insert(ZgxBqDTO dto);
	
	/**
	 * @Title: update
	 * @Description: 更新一条数据
	 * @author: Administrator
	 * @date: 2019-11-12 01:49:15
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer update(ZgxBqDTO dto); 
	
	/**
	 * @Title: deleteByMulti
	 * @Description: 批量删除多条数据
	 * @author: Administrator
	 * @date: 2019-11-12 01:49:15
	 * @param: @param pkids
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int deleteByMulti(List<String> pkids);
}
