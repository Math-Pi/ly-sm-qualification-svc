package com.ly.cloud.service.base.impl;

import java.util.List;
import java.util.UUID;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;

import com.ly.cloud.mapper.base.ZgxZgPOMapper;
import com.ly.cloud.service.base.ZgxZgService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.ZgxZgDTO;
import com.ly.cloud.entity.base.ZgxZgPO;
import com.ly.cloud.vo.base.ZgxZgVO;


@Service
public class ZgxZgServiceImpl implements ZgxZgService {

    private static Logger logger = LoggerFactory.getLogger(ZgxZgServiceImpl.class);

    @Autowired
    private ZgxZgPOMapper zgxZgPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgxZgService#get(java.lang.String)
	 */
	@Override
	public ZgxZgVO get(String pkid) {
    	try {
			ZgxZgPO po = zgxZgPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxZgVO.class);
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
     * @see com.ly.cloud.service.base.ZgxZgService#insert(com.ly.cloud.dto.base.ZgxZgDTO)
     */
    @Override
	public Integer insert(ZgxZgDTO dto){
    	try {
			ZgxZgPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxZgPO.class);
			po.setZgxdyid(UUID.randomUUID().toString());
			return zgxZgPOMapper.insert(po);
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
     * @see com.ly.cloud.service.base.ZgxZgService#update(com.ly.cloud.dto.base.ZgxZgDTO)
     */
    @Override
    public Integer update(ZgxZgDTO dto) {
        try {
        	ZgxZgPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxZgPO.class);
            return zgxZgPOMapper.updateById(po);
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
     * @see com.ly.cloud.service.base.ZgxZgService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgxZgPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

}