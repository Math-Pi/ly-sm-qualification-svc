package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZglsmdDTO;
import com.ly.cloud.vo.base.ZglsmdVO;

public interface ZglsmdService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZglsmdVO
	 * @throws
	 */
	public ZglsmdVO get(String pkid);
	
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
	public Integer insert(ZglsmdDTO dto);
	
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
	public Integer update(ZglsmdDTO dto); 
	
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

	public void insertPoByIdAndPcid(String id ,String pcid);
	public void insertFhPoByPcid(String pcid);
	public void insertAllPoByPcid(String pcid);
	public void insertNoFhPoByPcid(String pcid);
	public void deleteByCjsj();
	public void deleteByPcid(String pcid);
	public List<ZglsmdVO> selectLsmdByPcid( String pcid);
	
	public JsonResult qualificationsCheckOne(String zgid,String id);
	@Deprecated
	public JsonResult qualificationsCheckAll(String zgid,String type,String returnType);
	public JsonResult qualificationsGetBatchID(QualificationsDTO dto, String pcid);

	public Integer getDataCount(String pcid);

	/**
	 * 批量资格名单判断接口
	 * @param dto 资格参数
	 * @param pcid 资格批次id
	 * @param type 名单类型
	 * @param async 是否异步，true时会往redis存进度
	 */
	void qualificationsGetBatchID_V2(QualificationsDTO dto, String pcid, String type, boolean async);
}
