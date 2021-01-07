package com.ly.cloud.service.base;

import java.util.List;
import java.util.Map;

import com.ly.cloud.dto.base.ZgxflZgxDTO;
import com.ly.cloud.vo.base.ZgxflZgxVO;
import com.ly.cloud.vo.classification.ClassificationDetailVO;

public interface ZgxflZgxService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgxflZgxVO
	 * @throws
	 */
	public ZgxflZgxVO get(String pkid);
	
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
	public Integer insert(ZgxflZgxDTO dto);
	
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
	public Integer update(ZgxflZgxDTO dto); 
	
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
	/**
	 * 
	 * @Title: getDetail 
	 * @Description: 查询资格分类的详情 
	 * @author: zengweijin
	 * @date: 2019-02-20 13:59
	 * @param: @param fldm
	 * @param: @return      
	 * @return: List<ZgxglGlgzVO>      
	 * @throws
	 */
	public List<ClassificationDetailVO> getDetail(String fldm);
	/**
	 * 
	 * @Title: queryQuaIdByMulti 
	 * @Description: 查询需要删除的资格项ID
	 * @author: zengweijin
	 * @date: 2019-02-21 11:34
	 * @param: @param fldms
	 * @param: @return      
	 * @return: List<String>      
	 * @throws
	 */
	public List<String> queryQuaIdByMulti(List<String> fldms);
	
	/**
	 * 
	 * @Title: deleteByMultiFlid 
	 * @Description:  根据分类id删除资格项
	 * @author: zengweijin
	 * @date: 2019-02-21 11:56
	 * @param: @param flids
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int deleteByMultiFlid(List<String> flids);
	/**
	 * 
	 * @Title: insertMulti 
	 * @Description: 添加资格项分类资格项 
	 * @author: zengweijin
	 * @date: 2019-02-22 11:55
	 * @param: @param classificationQuas
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	public Integer insertMulti(List<ZgxflZgxDTO> classificationQuas);
	
	/**
	 * 
	 * @Title: getClassification 
	 * @Description: 查询服务分类
	 * @author: zengweijin
	 * @date: 2019-03-04 10:27
	 * @param: @param param
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String getClassification(Map<String, String> param);

}
