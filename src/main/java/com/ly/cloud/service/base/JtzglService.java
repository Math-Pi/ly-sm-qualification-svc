package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.base.JtzglDTO;
import com.ly.cloud.vo.base.JtzglVO;

public interface JtzglService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param: @param pkid
	 * @param: @return
	 * @return: JtzglVO
	 * @throws
	 */
	public JtzglVO get(String pkid);
	
	/**
	 * @Title: insert
	 * @Description: 插入单表的信息
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer insert(JtzglDTO dto);
	
	/**
	 * @Title: update
	 * @Description: 更新一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer update(JtzglDTO dto); 
	
	/**
	 * @Title: deleteByMulti
	 * @Description: 批量删除多条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param: @param pkids
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int deleteByMulti(List<String> pkids);
	
	/**
	 * @Title: CheckReference
	 * @Description: 查询静态值是否被引用
	 * @author: zengweijin
	 * @date: 2019-02-18 
	 * @param: @param pkid
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	public boolean checkReference(String pkid);

}
