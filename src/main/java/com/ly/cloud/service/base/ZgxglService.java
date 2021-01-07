package com.ly.cloud.service.base;

import java.util.List;
import java.util.Map;

import com.ly.cloud.dto.base.ZgxglDTO;
import com.ly.cloud.entity.base.ZgxglGlgzPO;
import com.ly.cloud.vo.base.ZgxglVO;

public interface ZgxglService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgxglVO
	 * @throws
	 */
	public ZgxglVO get(String pkid);
	
	//修改保存
	public Object getInitData(String pkid);
	
	//查后台id
	public Object getHtid(String ssfw);
	
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
	public Integer insert(ZgxglDTO dto);
	
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
	public Integer update(ZgxglDTO dto); 
	
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
	
	//查所有服务
	public Object getSsfw();
	
	//根据pkid查询所有规则
	public Object getQual(String pkid);
	
	//根据所属服务查询资格视图
	public Object getZgst(String pkid);
	
	//根据资格视图查询字段
	public Object getZgtjzd(String pkid);
	
	public List<Map<String,String>> getAllGzB(String zgxid);
    
	public void insertGLGZ(ZgxglGlgzPO glgzpo);
	
	public void deleteGLGZ(String glgzid);
	
	public void updateGLGZ(String hcglgzid ,String glgzid);
	
	public List<List<Map<String,String>>> getListListMap(String pkid);
	
	public String updatePreservation(List<Object> allData);
	
	public String insertPreservation (List<Object> allData);

	public int forceDeleteByMulti(List<String> pkids);

}
