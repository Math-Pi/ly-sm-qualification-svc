package com.ly.cloud.service.base;

import java.util.List;

import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZgdyDTO;
import com.ly.cloud.vo.base.ZgdyVO;
import com.ly.cloud.vo.base.ZgxLxVO;
import com.ly.cloud.vo.base.ZgxZgVO;

public interface ZgdyService {
	
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param: @param pkid
	 * @param: @return
	 * @return: ZgdyVO
	 * @throws
	 */
	public ZgdyVO get(String pkid);
	
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
	public Integer insert(ZgdyDTO dto);
	
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
	public Integer update(ZgdyDTO dto); 
	
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
	 * 保存资格的信息
	 * @param dto
	 */
	public void saveQualifications(QualificationsDTO dto);
	/**
	 *根据资格器ID获取资格所属项目
	 */
	public String getSszgmbdmByZgid(String zgid);
	
	public List<ZgxZgVO> selectAllZgxByZgid(String zgid);
	
	public String selectDqxxInfo();
	
	public List<ZgxLxVO>selectZgxByFlmAndMbdm(String flbz,String mbdm,String fdm,String tags);

	public Integer deleteQualifications(String quaId);
}
