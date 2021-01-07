package com.ly.cloud.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ly.cloud.dto.tag.QualificationTagDTO;
import com.ly.cloud.entity.base.ZgxbqGxPO;
import com.ly.cloud.mapper.base.ZgxbqGxPOMapper;
import com.ly.zhxg.exception.NHErrorException;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;

import com.ly.cloud.mapper.base.ZgxBqPOMapper;
import com.ly.cloud.service.base.ZgxBqService;

import com.ly.zhxg.utils.NHStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.ZgxBqDTO;
import com.ly.cloud.entity.base.ZgxBqPO;
import com.ly.cloud.vo.base.ZgxBqVO;


@Service
public class ZgxBqServiceImpl implements ZgxBqService {

    private static Logger logger = LoggerFactory.getLogger(ZgxBqServiceImpl.class);

    @Autowired
    private ZgxBqPOMapper zgxBqPOMapper;
    @Autowired
    private ZgxbqGxPOMapper zgxbqGxPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: Administrator
	 * @date: 2019-11-12 01:49:15
	 * @param pkid
	 * @return
	 * @see ZgxBqService#get(String)
	 */
	@Override
	public ZgxBqVO get(String pkid) {
    	try {
			ZgxBqPO po = zgxBqPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxBqVO.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	/**
     * @Title: insert
     * @Description: 插入单表的信息
     * @author: Administrator
     * @date: 2019-11-12 01:49:15
     * @param dto
     * @return
     * @see ZgxBqService#insert(ZgxBqDTO)
     */
    @Override
	public Integer insert(ZgxBqDTO dto){
    	try {
			ZgxBqPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxBqPO.class);
			po.setPkid(UUID.randomUUID().toString());
			return zgxBqPOMapper.insert(po);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return 0;
	}
    
    /**
     * @Title: update
     * @Description: 更新一条数据
     * @author: Administrator
     * @date: 2019-11-12 01:49:15
     * @param dto
     * @return
     * @see ZgxBqService#update(ZgxBqDTO)
     */
    @Override
    public Integer update(ZgxBqDTO dto) {
        try {
        	ZgxBqPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxBqPO.class);
            return zgxBqPOMapper.updateById(po);
        } catch (Exception e) {
        	logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
        }
        return 0;
    } 
    
    /**
     * @Title: deleteByMulti
     * @Description: 批量删除多条数据
     * @author: Administrator
     * @date: 2019-11-12 01:49:15
     * @param pkids
     * @return
     * @see ZgxBqService#deleteByMulti(List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgxbqGxPOMapper.deleteByTagId(pkids);
			return zgxBqPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}
}