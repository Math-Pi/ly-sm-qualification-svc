package com.ly.cloud.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.ly.cloud.dto.tag.QualificationTagDTO;
import com.ly.zhxg.exception.NHErrorException;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;

import com.ly.cloud.mapper.base.ZgxbqGxPOMapper;
import com.ly.cloud.service.base.ZgxbqGxService;

import com.ly.zhxg.utils.NHStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.ZgxbqGxDTO;
import com.ly.cloud.entity.base.ZgxbqGxPO;
import com.ly.cloud.vo.base.ZgxbqGxVO;


@Service
public class ZgxbqGxServiceImpl implements ZgxbqGxService {

    private static Logger logger = LoggerFactory.getLogger(ZgxbqGxServiceImpl.class);

    @Autowired
    private ZgxbqGxPOMapper zgxbqGxPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: Administrator
	 * @date: 2019-11-13 09:59:18
	 * @param pkid
	 * @return
	 * @see ZgxbqGxService#get(String)
	 */
	@Override
	public ZgxbqGxVO get(String pkid) {
    	try {
			ZgxbqGxPO po = zgxbqGxPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxbqGxVO.class);
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
     * @date: 2019-11-13 09:59:18
     * @param dto
     * @return
     * @see ZgxbqGxService#insert(ZgxbqGxDTO)
     */
    @Override
	public Integer insert(ZgxbqGxDTO dto){
    	try {
			ZgxbqGxPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxbqGxPO.class);
			po.setPkid(UUID.randomUUID().toString());
			return zgxbqGxPOMapper.insert(po);
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
     * @date: 2019-11-13 09:59:18
     * @param dto
     * @return
     * @see ZgxbqGxService#update(ZgxbqGxDTO)
     */
    @Override
    public Integer update(ZgxbqGxDTO dto) {
        try {
        	ZgxbqGxPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxbqGxPO.class);
            return zgxbqGxPOMapper.updateById(po);
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
     * @date: 2019-11-13 09:59:18
     * @param pkids
     * @return
     * @see ZgxbqGxService#deleteByMulti(List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgxbqGxPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

	@Override
	public int qualificationTagAdd(QualificationTagDTO dto) {
		List<String> ids = dto.getIds();
		List<String> tags = dto.getTags();
		List<ZgxbqGxPO> qualTagList = new ArrayList<>();
		try{
			if (ids == null || ids.size() <= 0) {
				throw  new NHErrorException("资格项id不能为空");
			}
			if (tags == null || ids.size() <= 0) {
				throw  new NHErrorException("标签不能为空");
			}
		}  catch (NHErrorException e) {
			NHExpHandleUtils.throwesException(e);
			return 0;
		}
		for (String id: ids) {
			for (String tag: tags) {
				ZgxbqGxPO po = new ZgxbqGxPO();
				po.setPkid(UUID.randomUUID().toString());
				po.setBqbz(tag);
				po.setZgxid(id);
				qualTagList.add(po);
			}
		}
		try {
			zgxbqGxPOMapper.insertPOBatch(qualTagList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return ids == null ? 0 :ids.size();
	}

	@Override
	public int qualificationTagDelete(List<String> ids) {
    	try{
    		return zgxbqGxPOMapper.delete(new EntityWrapper<ZgxbqGxPO>().in("zgxid",ids));
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return 0;
	}
}