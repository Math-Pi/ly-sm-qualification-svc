package com.ly.cloud.service.base.impl;

import java.util.*;

import com.ly.zhxg.utils.NHStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZgdyDTO;
import com.ly.cloud.dto.base.ZgxZgDTO;
import com.ly.cloud.entity.base.ZgdyPO;
import com.ly.cloud.entity.base.ZgxZgPO;
import com.ly.cloud.mapper.base.ZgdyPOMapper;
import com.ly.cloud.mapper.base.ZgxZgPOMapper;
import com.ly.cloud.service.base.ZgdyService;
import com.ly.cloud.vo.base.ZgdyVO;
import com.ly.cloud.vo.base.ZgxLxVO;
import com.ly.cloud.vo.base.ZgxZgVO;
import com.ly.zhxg.utils.NHBeanUtils;


@Service
public class ZgdyServiceImpl implements ZgdyService {

    private static Logger logger = LoggerFactory.getLogger(ZgdyServiceImpl.class);

    @Autowired
    private ZgdyPOMapper zgdyPOMapper;
    @Autowired
    private ZgxZgPOMapper zgxZgPOMapper;
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgdyService#get(java.lang.String)
	 */
	@Override
	public ZgdyVO get(String pkid) {
    	try {
			ZgdyPO po = zgdyPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgdyVO.class);
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
     * @date: 2019-02-18 09:35:36
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgdyService#insert(com.ly.cloud.dto.base.ZgdyDTO)
     */
    @Override
	public Integer insert(ZgdyDTO dto){
    	try {
			ZgdyPO po=NHBeanUtils.cloneDtoToPo(dto, ZgdyPO.class);
			po.setZgid(UUID.randomUUID().toString());
			return zgdyPOMapper.insert(po);
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
     * @date: 2019-02-18 09:35:36
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgdyService#update(com.ly.cloud.dto.base.ZgdyDTO)
     */
    @Override
    public Integer update(ZgdyDTO dto) {
        try {
        	ZgdyPO po=NHBeanUtils.cloneDtoToPo(dto, ZgdyPO.class);
            return zgdyPOMapper.updateById(po);
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
     * @date: 2019-02-18 09:35:36
     * @param pkids
     * @return
     * @see com.ly.cloud.service.base.ZgdyService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgdyPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

    /**
     * 保存资格的信息
     */
	@Override
	@Transactional
	public void saveQualifications(QualificationsDTO dto) {
		ZgdyPO zgdyPO=zgdyPOMapper.selectById(dto.getZgid());
		logger.error("删除资格id为"+dto.getZgid()+"的资格项");
		//1、先删除以前的定义的资格信息
		zgxZgPOMapper.deleteByZgid(dto.getZgid());
		//2、重新插入新的资格信息
		if(zgdyPO==null) {//只有第一次新增的时候才需要插入资格ID
			zgdyPO=new ZgdyPO();
			zgdyPO.setZgid(dto.getZgid());
			zgdyPO.setZgmc(dto.getZgmc());
			zgdyPO.setSszgmbdm(dto.getSszgmbdm());
			zgdyPO.setZhxgsj(new Date());
			zgdyPOMapper.insert(zgdyPO);
		} else {
			zgdyPO.setZhxgsj(new Date());
			zgdyPOMapper.updateById(zgdyPO);
		}
		List<ZgxZgPO> zgxZgList=new ArrayList<>();
		List<ZgxZgDTO> zgxZgDtoList = dto.getZgxList();
		for (ZgxZgDTO zgxZgDTO : zgxZgDtoList) {
			zgxZgList.add(NHBeanUtils.cloneDtoToPo(zgxZgDTO, ZgxZgPO.class));
		}
		if(zgxZgList.size()>0) {
			zgxZgPOMapper.insertPOBatch(zgxZgList);
		}
	}
	@Override
	public String getSszgmbdmByZgid(String zgid) {		
		return zgdyPOMapper.getSszgmbdmByZgid(zgid);
	}

	@Override
	public List<ZgxZgVO> selectAllZgxByZgid(String zgid) {
		return zgdyPOMapper.selectAllZgxByZgxid(zgid);
	}

	@Override
	public String selectDqxxInfo() {
		// TODO Auto-generated method stub
		return zgdyPOMapper.selectDqxxInfo();
	}

	@Override
	public List<ZgxLxVO> selectZgxByFlmAndMbdm(String flbz, String mbdm, String fdm, String tags) {
		// TODO Auto-generated method stub
		List<String> tagList;
		if (NHStringUtils.isEmpty(tags)) {
			tagList = new ArrayList<>();
		} else {
			String[] split = tags.split(",");
			tagList = Arrays.asList(split);
		}
		return zgdyPOMapper.selectZgxByFlmAndMbdm(flbz, mbdm, fdm, tagList);
	}

	@Override
	public Integer deleteQualifications(String quaId) {
		try {
			zgxZgPOMapper.deleteByZgid(quaId);
			return zgdyPOMapper.deleteById(quaId);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	
	
}