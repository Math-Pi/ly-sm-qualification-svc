package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.base.ZgxbqGxDTO;
import com.ly.cloud.dto.tag.QualificationTagDTO;
import com.ly.cloud.vo.base.ZgxbqGxVO;

public interface ZgxbqGxService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: Administrator
	 * @date: 2019-11-13 09:59:18
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgxbqGxVO
	 * @throws
	 */
	public ZgxbqGxVO get(String pkid);
	
	/**
	 * @Title: insert
	 * @Description: 插入单表的信息
	 * @author: Administrator
	 * @date: 2019-11-13 09:59:18
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer insert(ZgxbqGxDTO dto);
	
	/**
	 * @Title: update
	 * @Description: 更新一条数据
	 * @author: Administrator
	 * @date: 2019-11-13 09:59:18
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer update(ZgxbqGxDTO dto); 
	
	/**
	 * @Title: deleteByMulti
	 * @Description: 批量删除多条数据
	 * @author: Administrator
	 * @date: 2019-11-13 09:59:18
	 * @param: @param pkids
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int deleteByMulti(List<String> pkids);

	/**
	 * 添加资格项标签
	 * @param dto
	 * @return
	 */
    int qualificationTagAdd(QualificationTagDTO dto);

	/**
	 * 清除资格项的所有标签
	 * @param ids
	 * @return
	 */
    int qualificationTagDelete(List<String> ids);
}
