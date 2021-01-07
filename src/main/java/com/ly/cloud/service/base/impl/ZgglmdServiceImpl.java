package com.ly.cloud.service.base.impl;

import java.util.List;
import java.util.UUID;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;

import com.ly.cloud.mapper.base.ZgglmdPOMapper;
import com.ly.cloud.service.base.ZgglmdService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.ZgglmdDTO;
import com.ly.cloud.entity.base.ZgglmdPO;
import com.ly.cloud.vo.base.ZgglmdVO;


@Service
public class ZgglmdServiceImpl implements ZgglmdService {

    private static Logger logger = LoggerFactory.getLogger(ZgglmdServiceImpl.class);

    @Autowired
    private ZgglmdPOMapper zgglmdPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: myp
	 * @date: 2019-02-20 10:51:24
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgglmdService#get(java.lang.String)
	 */
	@Override
	public ZgglmdVO get(String pkid) {
    	try {
			ZgglmdPO po = zgglmdPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgglmdVO.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	/**
     * @Title: insert
     * @Description: 插入单表的信息
     * @author: myp
     * @date: 2019-02-20 10:51:24
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgglmdService#insert(com.ly.cloud.dto.base.ZgglmdDTO)
     */
    @Override
	public Integer insert(ZgglmdDTO dto){
    	try {
			ZgglmdPO po=NHBeanUtils.cloneDtoToPo(dto, ZgglmdPO.class);
			po.setPcid(UUID.randomUUID().toString());
			return zgglmdPOMapper.insert(po);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return 0;
	}
    
    /**
     * @Title: update
     * @Description: 更新一条数据
     * @author: myp
     * @date: 2019-02-20 10:51:24
     * @param dto
     * @return
     * @see com.ly.cloud.service.base.ZgglmdService#update(com.ly.cloud.dto.base.ZgglmdDTO)
     */
    @Override
    public Integer update(ZgglmdDTO dto) {
        try {
        	ZgglmdPO po=NHBeanUtils.cloneDtoToPo(dto, ZgglmdPO.class);
            return zgglmdPOMapper.updateById(po);
        } catch (Exception e) {
        	logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
        }
        return 0;
    } 
    
    /**
     * @Title: deleteByMulti
     * @Description: 批量删除多条数据
     * @author: myp
     * @date: 2019-02-20 10:51:24
     * @param pkids
     * @return
     * @see com.ly.cloud.service.base.ZgglmdService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgglmdPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}
	@Override
	public void insertPoBySelectStuInfo(String quid) {
		zgglmdPOMapper.insertPoBySelectStuInfo(quid);
		
	}

	@Override
	public void insertPoBySelectTeInfo(String quid) {
		zgglmdPOMapper.insertPoBySelectTeInfo(quid);
		
	}

	@Override
	public void deleteByCjsj() {
		zgglmdPOMapper.deleteByCjsj();
	}

	@Override
	public void insertPoBySelectStuId(String quid, String id) {
		zgglmdPOMapper.insertPoBySelectStuId(quid, id);
	}

	@Override
	public void insertPoBySelectTeId(String quid, String id) {
		zgglmdPOMapper.insertPoBySelectTeId(quid, id);
	}

	@Override
	public ZgglmdVO getZgglmdByIdAndPcid(String id, String pcid) {
		return zgglmdPOMapper.getZgglmdByIdAndPcid(id, pcid);
	}

	@Override
	public List<ZgglmdVO> getAllZgglmdByPcid(String pcid) {
		return zgglmdPOMapper.getAllZgglmdByPcid(pcid);
	}

	@Override
	public List<ZgglmdVO> getAllFhzgmdByPcid(String pcid) {
		return zgglmdPOMapper.getAllFhzgmdByPcid(pcid);
	}

	@Override
	public List<ZgglmdVO> getAllNofhmdByPcid(String pcid) {
		return zgglmdPOMapper.getAllNofhmdByPcid(pcid);
	}

	@Override
	public void deleteByPcid(String pcid) {
		zgglmdPOMapper.deleteByPcid(pcid);
	}

	@Override
	public void updateZgglmd(String updateSql) {
		zgglmdPOMapper.updateZgglmd(updateSql);
	}
}