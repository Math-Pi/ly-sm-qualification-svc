package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.base.ZgxglGlgzDTO;
import com.ly.cloud.vo.base.ZgxglGlgzVO;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ZgxglGlgzService {
	
	public ZgxglGlgzVO get(String pkid);


	
	//查询多条数据
	public Object getPkid(String pkid);
	
	/**
	 * @Title: insert
	 * @Description: 插入单表的信息
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer insert(ZgxglGlgzDTO dto);
	
	/**
	 * @Title: update
	 * @Description: 更新一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param dto
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer update(ZgxglGlgzDTO dto); 
	
	//加入互斥id
	public Integer updateHc(String hcglgzid, String glgzid); 
	
	/**
	 * @Title: deleteByMulti
	 * @Description: 批量删除多条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param pkids
	 * @param: @return
	 * @return: int
	 * @throws
	 */
	public int deleteByMulti(List<String> pkids);

	public void createSql(String xxdm,String ssfw,HttpServletRequest request,HttpServletResponse response);
}
