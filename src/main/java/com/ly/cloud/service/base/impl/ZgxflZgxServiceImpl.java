package com.ly.cloud.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.dto.base.ZgxflZgxDTO;
import com.ly.cloud.entity.base.ZgxflZgxPO;
import com.ly.cloud.mapper.base.ZgxflZgxPOMapper;
import com.ly.cloud.service.base.ZgxflZgxService;
import com.ly.cloud.vo.base.ZgxflZgxVO;
import com.ly.cloud.vo.classification.ClassificationDetailVO;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.zhxg.utils.NHCollectionUtils;


@Service
public class ZgxflZgxServiceImpl implements ZgxflZgxService {

    private static Logger logger = LoggerFactory.getLogger(ZgxflZgxServiceImpl.class);

    @Autowired
    private ZgxflZgxPOMapper zgxflZgxPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgxflZgxService#get(java.lang.String)
	 */
	@Override
	public ZgxflZgxVO get(String pkid) {
    	try {
			ZgxflZgxPO po = zgxflZgxPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxflZgxVO.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	/**
     * @Title: insert
     * @Description: 插入单表的信息
     * @author: yizhiqiang
     * @date: 2019-02-18 09:35:37
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgxflZgxService#insert(com.ly.cloud.dto.base.ZgxflZgxDTO)
     */
    @Override
	public Integer insert(ZgxflZgxDTO dto){
    	try {
			ZgxflZgxPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxflZgxPO.class);
			po.setPkid(UUID.randomUUID().toString());
			return zgxflZgxPOMapper.insert(po);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return 0;
	}
    
    /**
     * @Title: update
     * @Description: 更新一条数据
     * @author: yizhiqiang
     * @date: 2019-02-18 09:35:37
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgxflZgxService#update(com.ly.cloud.dto.base.ZgxflZgxDTO)
     */
    @Override
    public Integer update(ZgxflZgxDTO dto) {
        try {
        	ZgxflZgxPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxflZgxPO.class);
            return zgxflZgxPOMapper.updateById(po);
        } catch (Exception e) {
        	logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
        }
        return 0;
    } 
    
    /**
     * @Title: deleteByMulti
     * @Description: 批量删除多条数据
     * @author: yizhiqiang
     * @date: 2019-02-18 09:35:37
     * @param pkids
     * @return
     * @see com.ly.cloud.service.base.ZgxflZgxService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgxflZgxPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}
    /**
     * 
     * @Title: getDetail 
     * @Description: 查询资格分类详情  
     * @author: zengweijin
     * @date: 2019-02-20 14:01
     * @param: @param fldm
     * @param: @return      
     * @throws   
     * @see com.ly.cloud.service.base.ZgxflZgxService#detailGet(java.lang.String)
     */
	@Override
	public List<ClassificationDetailVO> getDetail(String fldm) {
		try {
			List<ClassificationDetailVO> rs = zgxflZgxPOMapper.queryDetail(fldm);
			return rs;
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	/**
	 * 
	 * @Title: queryQuaIdByMulti 
	 * @Description: 查询需要删除的资格项ID
	 * @author: zengweijin
	 * @date: 2019-02-21 11:35
	 * @param: @param fldms
	 * @param: @return      
	 * @throws   
	 * @see com.ly.cloud.service.base.ZgxflZgxService#deleteQualificationsByMulti(java.util.List)
	 */
	@Override
	public List<String> queryQuaIdByMulti(List<String> fldms) {
		try {
			List<String> list = zgxflZgxPOMapper.deleteQualificationsByMulti(fldms);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: deleteByMultiFlid 
	 * @Description: 根据flid删除资格项
	 * @author: zengweijin
	 * @date: 2019-02-21 11:57
	 * @param: @param flids
	 * @param: @return      
	 * @throws   
	 * @see com.ly.cloud.service.base.ZgxflZgxService#deleteByMultiFlid(java.util.List)
	 */
	@Override
	public int deleteByMultiFlid(List<String> flids) {
		try {
			zgxflZgxPOMapper.deleteByMultiFlid(flids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return flids.size();
	}
	/**
	 * 
	 * @Title: insertMulti 
	 * @Description: 添加资格项分类资格项 
	 * @author: zengweijin
	 * @date: 2019-02-22 11:55
	 * @param: @param classificationQuas
	 * @param: @return      
	 * @throws   
	 * @see com.ly.cloud.service.base.ZgxflZgxService#insertMulti(java.util.List)
	 */
	@Override
	public Integer insertMulti(List<ZgxflZgxDTO> classificationQuas) {
		try {
			List<ZgxflZgxPO> list = new ArrayList<ZgxflZgxPO>();
			ZgxflZgxPO po;
			for (ZgxflZgxDTO dto : classificationQuas) {
				po = NHBeanUtils.cloneDtoToPo(dto,ZgxflZgxPO.class);
				po.setPkid(UUID.randomUUID().toString());
				list.add(po);
			}
			List<List<ZgxflZgxPO>> groupList = NHCollectionUtils.fixedGrouping(list, 500);  
	           for (List<ZgxflZgxPO> tempList : groupList) {  
	               zgxflZgxPOMapper.insertPOBatch(tempList);  
	           } 

			return list.size();
		} catch (Exception e) {	
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getClassification 
	 * @Description: 查询服务分类
	 * @author: zengweijin
	 * @date: 2019-03-04 10:27
	 * @param: @param param
	 * @param: @return      
	 * @throws   
	 * @see com.ly.cloud.service.base.ZgxflZgxService#classification(java.util.Map)
	 */
	@Override
	public String getClassification(Map<String, String> param) {
		try {
			return zgxflZgxPOMapper.queryClassification(param);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	
}