package com.ly.cloud.service.base.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.ly.cloud.dto.qualification.QualificationMatchInfo;
import com.ly.cloud.entity.base.ZgdyPO;
import com.ly.zhxg.exception.NHWarmingException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.controller.Interface.utils.NHQualificationsFactory;
import com.ly.cloud.controller.Interface.utils.QualConfigItem;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZglsmdDTO;
import com.ly.cloud.entity.base.ZglsmdPO;
import com.ly.cloud.mapper.base.ZgdyPOMapper;
import com.ly.cloud.mapper.base.ZgglmdPOMapper;
import com.ly.cloud.mapper.base.ZglsmdPOMapper;
import com.ly.cloud.mapper.base.ZgxglGlgzPOMapper;
import com.ly.cloud.service.base.ZgglmdService;
import com.ly.cloud.service.base.ZglsmdService;
import com.ly.cloud.vo.base.ZgglmdVO;
import com.ly.cloud.vo.base.ZglsmdVO;
import com.ly.zhxg.utils.NHBeanUtils;
import org.springframework.util.StopWatch;


@Service
public class ZglsmdServiceImpl implements ZglsmdService {

    private static Logger logger = LoggerFactory.getLogger(ZglsmdServiceImpl.class);

    @Autowired
    private ZglsmdPOMapper zglsmdPOMapper;
    @Autowired 
	private ZgglmdService zgglmdService;
	@Autowired
	private ZglsmdService zglsmdService;
	@Autowired
	private ZgglmdPOMapper zgglmdPOMapper;
	@Autowired 
	private ZgdyPOMapper zgdyPOMapper;
	@Autowired 
	private ZgxglGlgzPOMapper zgxglGlgzPOMapper;
	@Autowired
	JdbcTemplate jdbcTemplate;
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZglsmdService#get(java.lang.String)
	 */
	@Override
	public ZglsmdVO get(String pkid) {
    	try {
			ZglsmdPO po = zglsmdPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZglsmdVO.class);
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
     * @see com.ly.cloud.service.base.ZglsmdService#insert(com.ly.cloud.dto.base.ZglsmdDTO)
     */
    @Override
	public Integer insert(ZglsmdDTO dto){
    	try {
			ZglsmdPO po=NHBeanUtils.cloneDtoToPo(dto, ZglsmdPO.class);
			po.setPkid(UUID.randomUUID().toString());
			return zglsmdPOMapper.insert(po);
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
     * @see com.ly.cloud.service.base.ZglsmdService#update(com.ly.cloud.dto.base.ZglsmdDTO)
     */
    @Override
    public Integer update(ZglsmdDTO dto) {
        try {
        	ZglsmdPO po=NHBeanUtils.cloneDtoToPo(dto, ZglsmdPO.class);
            return zglsmdPOMapper.updateById(po);
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
     * @see com.ly.cloud.service.base.ZglsmdService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zglsmdPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

	@Override
	public void insertPoByIdAndPcid(String id, String pcid) {
		zglsmdPOMapper.insertPoByIdAndPcid(id, pcid);
	}

	@Override
	public void insertFhPoByPcid( String pcid) {
		// TODO Auto-generated method stub
		zglsmdPOMapper.insertFhPoByPcid(pcid);
	}

	@Override
	public void insertAllPoByPcid(String pcid) {
		// TODO Auto-generated method stub
		zglsmdPOMapper.insertAllPoByPcid(pcid);
	}

	@Override
	public void insertNoFhPoByPcid(String pcid) {
		// TODO Auto-generated method stub
		zglsmdPOMapper.insertNoFhPoByPcid( pcid);
	}

	@Override
	public void deleteByCjsj() {
		// TODO Auto-generated method stub
		zglsmdPOMapper.deleteByCjsj();
	}

	@Override
	public void deleteByPcid(String pcid) {
		// TODO Auto-generated method stub
		zglsmdPOMapper.deleteByPcid(pcid);
	}

	@Override
	public List<ZglsmdVO> selectLsmdByPcid(String pcid) {
		return zglsmdPOMapper.selectLsmdByPcid(pcid);
	}

	@Override
	@Transactional
	public JsonResult qualificationsCheckOne(String zgid, String id) {
		//批次ID
		String pcid=UUID.randomUUID().toString();
		try {
			//初始化一个资格判断工厂
			NHQualificationsFactory factory=NHQualificationsFactory.newInstance(pcid, "admin",zgdyPOMapper,zgxglGlgzPOMapper);
			//根据资格ID获取资格的项目类型
//			String xmlx=factory.getSszgmb(zgid);
			ZgdyPO zgdyPO = zgdyPOMapper.selectById(zgid);
			if (zgdyPO == null) {
				JSONObject r = new JSONObject();
				r.put("id",id);
				r.put("zt","1"); // 通过
				return JsonResult.success(r);
			}
			String xmlx = zgdyPO.getSszgmbdm();
			//每次调用前先把临时名单表里面时间超过30分钟的数据删除先
			zgglmdService.deleteByCjsj();
			zglsmdService.deleteByCjsj();
			logger.info("资格id："+zgid+",对象id：" + id +",生成的批次id："+pcid+",查询的项目类型："+xmlx);
			//根据项目类型进行相关的数据初始化
			if("stu".equals(xmlx)) {
				zgglmdService.insertPoBySelectStuId(pcid, id);
			}else if("tea".equals(xmlx)) {
				zgglmdService.insertPoBySelectTeId(pcid, id);
			}else if ("cla".equals(xmlx)) {
				zgglmdPOMapper.insertPoBySelectBjId(pcid, id);
			}else if("room".equals(xmlx)) {
				return JsonResult.success("该类型模板还未进行开放，请换个类型模板试试！");
			}else {
				return JsonResult.success("资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
			}
			
			//获取所有资格判断条件
			List<QualConfigItem> qualList=factory.getQualConfig(zgid);
			//循环进行判断
			for (QualConfigItem qualConfigItem : qualList) {
				String sql=qualConfigItem.getUpdateSql(false);
				zgglmdService.updateZgglmd(sql);
			}
			//设置通过的名单的通过条件
			String sql=factory.getUpdatePassConditionSql(qualList,pcid);
			logger.info("设置通过的名单的sql："+sql);
			zgglmdService.updateZgglmd(sql);
			//获取最后的判断结果数据
			ZgglmdVO vo=zgglmdService.getZgglmdByIdAndPcid(id, pcid);
			logger.info("最后的判断结果数据："+JSON.toJSONString(vo));
			//将临时中间表的数据删除
			zgglmdService.deleteByPcid(pcid);
			return JsonResult.success(vo);
		} catch (Exception e) {
			//若发现异常就把插入的批次名单进行删除
			zgglmdService.deleteByPcid(pcid);
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@Deprecated
	@Override
	@Transactional
	public JsonResult qualificationsCheckAll(String zgid, String type,String returnType) {
		//批次ID
		String pcid=UUID.randomUUID().toString();
		try {
			//初始化一个资格判断工厂
			NHQualificationsFactory factory=NHQualificationsFactory.newInstance(pcid, "admin",zgdyPOMapper,zgxglGlgzPOMapper);
			//根据资格ID获取资格的项目类型
//			String xmlx=factory.getSszgmb(zgid);
			ZgdyPO zgdyPO = zgdyPOMapper.selectById(zgid);
			if (zgdyPO == null) {
				JSONObject r = new JSONObject();
				r.put("code",-1);
				r.put("message","不存在这个资格id对应的数据");
				return JsonResult.success(r);
			}
			String xmlx = zgdyPO.getSszgmbdm();
			//每次调用前先把临时名单表里面时间超过30分钟的数据删除先
			zgglmdService.deleteByCjsj();
			zglsmdService.deleteByCjsj();
			logger.info("资格id："+zgid+",返回名单类型：" + type +",生成的批次id："+pcid+",查询的项目类型："+xmlx+",返回值的类型："+returnType);
			//根据项目类型进行相关的数据初始化
			if("stu".equals(xmlx)) {
				zgglmdService.insertPoBySelectStuInfo(pcid);
			}else if("tea".equals(xmlx)) {
				zgglmdService.insertPoBySelectTeInfo(pcid);
			}else if ("cla".equals(xmlx)) {
				zgglmdPOMapper.insertPOBySelectBjInfo(pcid);
			}else if("room".equals(xmlx)) {
				return JsonResult.success("该类型模板还未进行开放，请换个类型模板试试！");
			}else {
				return JsonResult.success("资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
			}
			
			//获取所有资格判断条件
			List<QualConfigItem> qualList=factory.getQualConfig(zgid);
			//循环进行判断
			for (QualConfigItem qualConfigItem : qualList) {
				if("match".equals(type)) {
					String sql=qualConfigItem.getUpdateSql(true);
					zgglmdService.updateZgglmd(sql);
				}else {
					String sql=qualConfigItem.getUpdateSql(false);
					zgglmdService.updateZgglmd(sql);
				}
			}
			//设置通过的名单的通过条件
			String sql=factory.getUpdatePassConditionSql(qualList,pcid);
			zgglmdService.updateZgglmd(sql);
			//获取最后的判断结果数据
			List<ZgglmdVO> list=null;
			//如果返回值类型为1时才把结果临时名单返回出去
			if("1".equals(returnType)) {
				if("all".equals(type)) {
					list=zgglmdService.getAllZgglmdByPcid(pcid);
				}else if("match".equals(type)) {
					list=zgglmdService.getAllFhzgmdByPcid(pcid);
				}else {
					list=zgglmdService.getAllNofhmdByPcid(pcid);
				}
				Map<String,Object> returnMap=new HashMap<>();
				returnMap.put("list", list);
				returnMap.put("pcid", pcid);
				returnMap.put("code",1);
				returnMap.put("message","操作成功");
				logger.info("返回的数据："+returnMap.toString());
				//插入完数据后把中间表的数据清除
				zgglmdService.deleteByPcid(pcid);
				return JsonResult.success(returnMap);
			}else {
				Map<String, Object> returnMap=new HashMap<>();
				if("all".equals(type)) {
					zglsmdService.insertAllPoByPcid( pcid);
				}else if("match".equals(type)) {
					zglsmdService.insertFhPoByPcid(pcid);
				}else {
					zglsmdService.insertNoFhPoByPcid(pcid);
				}
				returnMap.put("pcid", pcid);
				returnMap.put("code",1);
				returnMap.put("message","操作成功");
				logger.info("返回的数据："+returnMap.toString());
				//插入完数据后把中间表的数据清除
				zgglmdService.deleteByPcid(pcid);
				return JsonResult.success(returnMap);
			}
		} catch (Exception e) {
			//若发现异常就把插入的批次名单进行删除
			zgglmdService.deleteByPcid(pcid);
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@Override
	@Transactional
	public JsonResult qualificationsGetBatchID(QualificationsDTO dto,String pcid) {
		String xmlx = dto.getSszgmbdm();
		NHQualificationsFactory factory=NHQualificationsFactory.newInstance(pcid, "admin",zgdyPOMapper,zgxglGlgzPOMapper);	
		try {
			//初始化一个资格判断工厂
			factory.setProgressSuccessStatus(10, "准备进行判断资格的数据");
			//每次调用前先把临时名单表里面时间超过30分钟的数据删除先
			zgglmdService.deleteByCjsj();
			zglsmdService.deleteByCjsj();
			//根据项目类型进行相关的数据初始化
			if("stu".equals(xmlx)) {
				zgglmdService.insertPoBySelectStuInfo(pcid);
			}else if("tea".equals(xmlx)) {
				zgglmdService.insertPoBySelectTeInfo(pcid);
			}else if ("cla".equals(xmlx)) {
				zgglmdPOMapper.insertPOBySelectBjInfo(pcid);
			}else if("room".equals(xmlx)) {
				factory.setProgressFailureStatus(100, "该类型模板还未进行开放，请换个类型模板试试！");
//				return JsonResult.success("该类型模板还未进行开放，请换个类型模板试试！");
			}else {
				factory.setProgressFailureStatus(100, "资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
//				return JsonResult.success("资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
			}
			factory.setProgressSuccessStatus(20, "获取所有资格判断条件");
			//获取所有资格判断条件
			List<QualConfigItem> qualList=factory.getQualConfigByQuaDto(dto);
			logger.info("所有资格判断条件:"+JSON.toJSONString(qualList));
			//循环进行判断
			if(qualList!=null&&qualList.size()>0) {
				int single = 60/qualList.size();
				for (int i=0;i<qualList.size();i++) {
					String sql=qualList.get(i).getUpdateSql(false);
					zgglmdService.updateZgglmd(sql);
					factory.setProgressSuccessStatus(20+((i+1)*single), "循环进行判断中");
				}
			}
			
			factory.setProgressSuccessStatus(80, "获取通过的名单的通过条件");
			
			//设置通过的名单的通过条件
			String sql=factory.getUpdatePassConditionSql(qualList,pcid);
			zgglmdService.updateZgglmd(sql);
			factory.setProgressSuccessStatus(90, "插入临时名单");	
			//把临时名单插入到临时表中
			zglsmdService.insertFhPoByPcid(pcid);
			factory.setProgressSuccessStatus(95, "插入完数据后清除数据");
			//插入完数据后把中间表的数据清除
			zgglmdService.deleteByPcid(pcid);
			logger.info("pcid："+pcid);
			factory.setProgressSuccessStatus(100, "资格名单生成完成");
			return JsonResult.success(pcid);
		} catch (Exception e) {
			//若发现异常就把插入的批次名单进行删除
			zgglmdService.deleteByPcid(pcid);
			logger.error(e.getMessage(),e);
			factory.setProgressFailureStatus(100, "资格鉴定执行失败");
			NHExpHandleUtils.throwesException(e);
//			return JsonResult.failure(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer getDataCount(String pcid) {
		try {
			return zglsmdPOMapper.selectDataCount(pcid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	/**
	 * 批量资格名单判断接口
	 * @param dto 资格参数
	 * @param pcid 资格批次id
	 * @param type 名单类型
	 * @param async 是否异步，true时会往redis存进度
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void qualificationsGetBatchID_V2(QualificationsDTO dto, final String pcid, String type, boolean async) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("资格批次："+pcid);
		String xmlx = dto.getSszgmbdm();
		NHQualificationsFactory factory = NHQualificationsFactory.newInstance(pcid, "admin", zgdyPOMapper, zgxglGlgzPOMapper);
		try {
			// 初始化一个资格判断工厂
			factory.setProgressSuccessStatus(10, "准备进行判断资格的数据",async);
			//每次调用前先把临时名单表里面时间超过30分钟的数据删除先
			zgglmdService.deleteByCjsj();
			zglsmdService.deleteByCjsj();
			// 根据项目类型进行相关的数据初始化
			if ("stu".equals(xmlx)) {
				// 初始化参与资格判断的学生
				zgglmdPOMapper.insertStuInfoBySelect(pcid, "0");
			} else if ("tea".equals(xmlx)) {
				// 初始化参与资格判断的教师
				zgglmdPOMapper.insertTeaInfoBySelect(pcid, "0");
			} else if ("cla".equals(xmlx)) {
				// 初始化参与资格判断的班级
				zgglmdPOMapper.insertClaBySelect(pcid, "0");
			} else if ("room".equals(xmlx)) {
				if (async) {
					factory.setProgressFailureStatus(100, "该类型模板还未进行开放，请换个类型模板试试！");
					return;
				} else {
					throw new NHWarmingException("该类型模板还未进行开放，请换个类型模板试试！");
				}
			} else {
				if (async) {
					factory.setProgressFailureStatus(100, "资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
					return;
				} else {
					throw new NHWarmingException("资格配置模板有问题，无法进行资格判断，请确认模板无误后，再进行此操作");
				}
			}
			factory.setProgressSuccessStatus(20, "获取所有资格判断条件",async);
			// 获取所有资格判断条件
			List<QualConfigItem> quaConfigItems = factory.getQualConfigByQuaDto(dto);
			// 查询每个资格条件满足的对象
			List<List<QualificationMatchInfo>> matchQuaList = new ArrayList<>();
			factory.setProgressSuccessStatus(40, "获取每个资格条件的名单",async);
			for (int i = 0; i < quaConfigItems.size(); i++) {
				// 获取资格条件的查询sql
				String matchSql = quaConfigItems.get(i).getMatchSql();
				logger.info("[查询满足资格的对象]：{}", matchSql);
				List<QualificationMatchInfo> mapList = zgglmdPOMapper.selectBySql(matchSql);
				if (CollectionUtils.isEmpty(mapList)) {
					// 没有人满足资格
					logger.info("没有人满足资格：{}",pcid);
					factory.insertResult(zglsmdService,type,pcid);
					zgglmdService.deleteByPcid(pcid);
					factory.setProgressSuccessStatus(100, "资格名单生成完成",async);
					return;
				}
				matchQuaList.add(mapList);
			}
			// 根据每个资格名单数量排序
			Collections.sort(matchQuaList, new Comparator<List<QualificationMatchInfo>>() {
				@Override
				public int compare(List<QualificationMatchInfo> o1, List<QualificationMatchInfo> o2) {
					return Integer.compare(o1.size(), o2.size());
				}
			});
			factory.setProgressSuccessStatus(60, "对所有资格条件名单求交集",async);
			// 将每个名单的对象id为键存为Map，方便判断是否符合条件
			List<Map<String, QualificationMatchInfo>> quaMapList = factory.convertToMap(matchQuaList);
			if (CollectionUtils.isEmpty(matchQuaList)) {
				logger.info("[{}]批次没有资格条件，所有学生都符合", pcid);
				jdbcTemplate.update("update ZHXG_ZG_ZGGLMD set zt = '1' where PCID = ?", pcid);
				zgglmdService.deleteByPcid(pcid);
			} else {
				// 通过名单
				List<QualificationMatchInfo> resultList = factory.match(matchQuaList,quaMapList);
				// 更通过名单的状态
				jdbcTemplate.batchUpdate("update ZHXG_ZG_ZGGLMD set zt = '1',FHTJ=? where id = ? and PCID = ?", resultList, 500, new ParameterizedPreparedStatementSetter<QualificationMatchInfo>() {
					@Override
					public void setValues(PreparedStatement ps, QualificationMatchInfo info) throws SQLException {
						ps.setString(1, info.getFhtj());
						ps.setString(2, info.getId());
						ps.setString(3, pcid);
					}
				});
			}
			//设置通过的名单的通过条件
			factory.setProgressSuccessStatus(90, "插入临时名单",async);
			//把临时名单插入到临时表中
			if(CollectionUtils.isNotEmpty(matchQuaList)){
				factory.insertResult(zglsmdService,type,pcid);
			}
			factory.setProgressSuccessStatus(95, "插入完数据后清除数据",async);
			//插入完数据后把中间表的数据清除
			zgglmdService.deleteByPcid(pcid);
			logger.info("pcid：" + pcid);
			factory.setProgressSuccessStatus(100, "资格名单生成完成",async);
			stopWatch.stop();
			logger.info(stopWatch.prettyPrint());
		} catch (Exception e) {
			//若发现异常就把插入的批次名单进行删除
			zgglmdService.deleteByPcid(pcid);
			logger.error(e.getMessage(), e);
			factory.setProgressFailureStatus(100, "资格鉴定执行失败",async);
			NHExpHandleUtils.throwesException(e);
		}
	}
}