package com.ly.cloud.service.base.impl;

import java.util.List;
import java.util.UUID;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;

import com.ly.cloud.mapper.base.JtzglPOMapper;
import com.ly.cloud.service.base.JtzglService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.JtzglDTO;
import com.ly.cloud.entity.base.JtzglPO;
import com.ly.cloud.vo.base.JtzglVO;


@Service
public class JtzglServiceImpl implements JtzglService {

    private static Logger logger = LoggerFactory.getLogger(JtzglServiceImpl.class);

    @Autowired
    private JtzglPOMapper jtzglPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:36
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.JtzglService#get(java.lang.String)
	 */
	@Override
	public JtzglVO get(String pkid) {
    	try {
			JtzglPO po = jtzglPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, JtzglVO.class);
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
     * @see com.ly.cloud.service.base.JtzglService#insert(com.ly.cloud.dto.base.JtzglDTO)
     */
    @Override
	public Integer insert(JtzglDTO dto){
    	try {
			JtzglPO po=NHBeanUtils.cloneDtoToPo(dto, JtzglPO.class);
			po.setJtzid(UUID.randomUUID().toString());
			return jtzglPOMapper.insert(po);
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
     * @see com.ly.cloud.service.base.JtzglService#update(com.ly.cloud.dto.base.JtzglDTO)
     */
    @Override
    public Integer update(JtzglDTO dto) {
        try {
        	JtzglPO po=NHBeanUtils.cloneDtoToPo(dto, JtzglPO.class);
            return jtzglPOMapper.updateById(po);
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
     * @see com.ly.cloud.service.base.JtzglService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			jtzglPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

    /**
	 * @Title: checkReference
	 * @Description: 查询静态值是否被引用
	 * @author: zengweijin
	 * @date: 2019-02-18 
	 * @param: @param pkid
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	@Override
	public boolean checkReference(String pkid) {
		
		try {
			int rs = jtzglPOMapper.checkReference(pkid);
			return rs>0 ? true:false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return false;
	}

}