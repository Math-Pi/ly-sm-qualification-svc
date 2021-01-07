package com.ly.cloud.controller.Interface.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZgxZgDTO;
import com.ly.cloud.dto.qualification.QualificationMatchInfo;
import com.ly.cloud.entity.base.ZgdyPO;
import com.ly.cloud.mapper.base.ZgdyPOMapper;
import com.ly.cloud.mapper.base.ZgxglGlgzPOMapper;
import com.ly.cloud.service.base.ZglsmdService;
import com.ly.zhxg.utils.NHCollectionUtils;
import com.ly.zhxg.utils.NHRedisUtils;
import com.ly.zhxg.utils.NHStringUtils;
import org.springframework.beans.BeanUtils;

public class NHQualificationsFactory {
	private String loginUserId;
	private String pcid;
		
	private ZgdyPOMapper zgdyPOMapper;
	private ZgxglGlgzPOMapper zgxglGlgzPOMapper;

	public NHQualificationsFactory(String pcid,String loginUserId,ZgdyPOMapper zgdyPOMapper,ZgxglGlgzPOMapper zgxglGlgzPOMapper) {
		this.pcid=pcid;
		this.loginUserId=loginUserId;
		this.zgdyPOMapper=zgdyPOMapper;
		this.zgxglGlgzPOMapper=zgxglGlgzPOMapper;
	}
	
	public static NHQualificationsFactory newInstance(String pcid,String loginUserId,ZgdyPOMapper zgdyPOMapper,ZgxglGlgzPOMapper zgxglGlgzPOMapper) {
		NHQualificationsFactory factory=new NHQualificationsFactory(pcid, loginUserId,zgdyPOMapper,zgxglGlgzPOMapper);
		return factory;
	}
	
	/**
	 * 获取资格的所有配置信息
	 * @param zgid
	 */
	public List<QualConfigItem> getQualConfig(String zgid) {		
		//1、根据资格ID查询数据
		List<Map<String,Object>> zgxList=zgdyPOMapper.selectZgxByZgid(zgid);
		List<Map<String,Object>> glgzList=zgxglGlgzPOMapper.selectByZgid(zgid);
		
		//2、对查询出来的数据进行装换
		List<List<Map<String,Object>>> list=buildZgx(zgxList, glgzList);
		//3、对资格项数据进行处理，解析出需要的数据
		List<QualConfigItem> resultList=bulidQualConfigItem(list);
		return resultList;
	}
	
	/**
	 * 根据前端传来的List获取资格的所有配置信息
	 * @param zgid
	 */
	public List<QualConfigItem> getQualConfigByQuaDto(QualificationsDTO dto) {	
		List<Map<String,Object>> zgxList=new ArrayList<>();
		List<Map<String,Object>> glgzList=new ArrayList<>();
		//1.获取资格项信息
		List<ZgxZgDTO> zgxpoList=dto.getZgxList();
		for (ZgxZgDTO zgxZgDTO : zgxpoList) {
			//获取该资格项的过滤规则
			List<Map<String,Object>> glList=zgxglGlgzPOMapper.selectByZgxid(zgxZgDTO.getZgxid());
			//合拼所以过滤条件
			glgzList.addAll(glList);
			//获取资格项信息
			Map<String, Object> zgMap=zgdyPOMapper.selectZgxByZgxid(zgxZgDTO.getZgxid());
			zgMap.put("ZGXDYID", zgxZgDTO.getZgxdyid());
			zgMap.put("ZGXDYMC", zgxZgDTO.getZgxdymc());
			zgMap.put("YSFDM", zgxZgDTO.getYsfdm());
			zgMap.put("CSZ", zgxZgDTO.getCsz());
			zgMap.put("HCZGXDYID", zgxZgDTO.getHczgxdyid());
			zgxList.add(zgMap);
		}
		
		//2、对查询出来的数据进行装换
		List<List<Map<String,Object>>> list=buildZgx(zgxList, glgzList);
		//3、对资格项数据进行处理，解析出需要的数据
		List<QualConfigItem> resultList=bulidQualConfigItem(list);
		return resultList;
	}
	/**
	 * 解析资格项，主要是把互斥的资格项合并到一起
	 * @param zgxList
	 * @param glgzList
	 * @return
	 */
	private List<List<Map<String,Object>>> buildZgx(List<Map<String,Object>> zgxList,List<Map<String,Object>> glgzList){
		if(zgxList==null) {
			return new ArrayList<>();
		}
		
		//1、把过滤规则根据资格项进行分组
		Map<String, List<Map<String, Object>>> zgxGlgzMap = NHCollectionUtils.cloneListToMapList(glgzList, "ZGXID");
		//2、把分组之后的资格项放置到资格项信息中,并针对每个资格项过滤规则进行处理，主要是要把那些互斥的过滤规则合并
		for (Map<String,Object> map : zgxList) {
			String zgxid=(String) map.get("ZGXID");
			map.put("GLGZLIST", buildGlgz(zgxGlgzMap.get(zgxid)));
		}
		//3、准备参数
		Map<String,List<Map<String,Object>>> resultMap=new HashMap<>();
		List<Map<String,Object>> tempList=null;
		Map<String,Object> zgxMap=new HashMap<>();
		for (Map<String, Object> map : zgxList) {
			zgxMap.put((String)map.get("ZGXDYID"), map);
		}
		//4、循环过滤规则，并对其做处理，进行合并操作
		for (Map<String, Object> map : zgxList) {
			//如果互斥过滤规则ID为空，表示当前规则只有一个，或者是互斥的数据的源头
			String zgxdyid=null;
			if(NHStringUtils.isEmpty((String)map.get("HCZGXDYID"))) {
				zgxdyid=(String)map.get("ZGXDYID");
			}else {
				zgxdyid=loopZgxid(zgxMap,(String)map.get("HCZGXDYID"));
			}
			tempList=resultMap.get(zgxdyid);
			if(tempList==null) {
				tempList=new ArrayList<>();
			}
			tempList.add(map);
			resultMap.put(zgxdyid, tempList);
		}
		//3、把数据转换成List对象，并进行返回
		List<List<Map<String,Object>>> resultList=new ArrayList<>();
		for (String  glgzid: resultMap.keySet()) {
			resultList.add(resultMap.get(glgzid));
		}
		return resultList;
	}

	/**
	 * 针对每个资格项过滤规则进行处理，主要是要把那些互斥的过滤规则合并
	 * @return
	 */
	private List<List<Map<String,Object>>> buildGlgz(List<Map<String,Object>> glgzList){
		if(glgzList==null || glgzList.size()<=0) {
			return null;
		}
		//1、准备参数
		Map<String,List<Map<String,Object>>> resultMap=new HashMap<>();
		List<Map<String,Object>> tempList=null;
		Map<String,Object> glgzMap=new HashMap<>();
		for (Map<String, Object> map : glgzList) {
			glgzMap.put((String)map.get("GLGZID"), map);
		}
		//2、循环过滤规则，并对其做处理，进行合并操作
		for (Map<String, Object> map : glgzList) {
			//如果互斥过滤规则ID为空，表示当前规则只有一个，或者是互斥的数据的源头
			String glgzid=null;
			if(NHStringUtils.isEmpty((String)map.get("HCGLGZID"))) {
				glgzid=(String)map.get("GLGZID");
			}else {
				glgzid=loopGlgzid(glgzMap,(String)map.get("HCGLGZID"));
			}
			tempList=resultMap.get(glgzid);
			if(tempList==null) {
				tempList=new ArrayList<>();
			}
			tempList.add(map);
			resultMap.put(glgzid, tempList);
		}
		//3、把数据转换成List对象，并进行返回
		List<List<Map<String,Object>>> resultList=new ArrayList<>();
		for (String  glgzid: resultMap.keySet()) {
			resultList.add(resultMap.get(glgzid));
		}
		return resultList;
	}
	
	/**
	 * 查询改互斥的规则是否是第一个规则
	 * @param glgzMap
	 * @param string
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String loopGlgzid(Map<String, Object> glgzMap, String glgzid) {
		Map<String, Object> map=(Map<String, Object>) glgzMap.get(glgzid);
		if(map==null) {
			return glgzid;
		}else if(NHStringUtils.isNotEmpty((String)map.get("HCGLGZID"))) {
			return loopGlgzid(glgzMap,(String)map.get("HCGLGZID"));
		}
		return glgzid;
	}
	
	/**
	 * 查询改互斥的资格项是否是第一个规则
	 * @param zgxMap
	 * @param zgxid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String loopZgxid(Map<String, Object> zgxMap, String zgxdyid) {
		Map<String, Object> map=(Map<String, Object>) zgxMap.get(zgxdyid);
		if(map==null) {
			return zgxdyid;
		}else if(NHStringUtils.isNotEmpty((String)map.get("HCZGXDYID"))) {
			return loopZgxid(zgxMap,(String)map.get("HCZGXDYID"));
		}
		return zgxdyid;
	}
	
	/**
	 * 对资格项数据进行处理，解析出需要的数据
	 * @param list
	 * @return
	 */
	private List<QualConfigItem> bulidQualConfigItem(List<List<Map<String, Object>>> list) {
		List<QualConfigItem> resultList=new ArrayList<>();
		QualConfigItem item=null;
		for (List<Map<String,Object>> list2 : list) {
			item = new QualConfigItem();
			item.setLoginUserId(loginUserId);
			item.setPcid(pcid);
			int i=0;
			for (Map<String, Object> map : list2) {
				item .addItem(map,"ttt"+(i++));
			}
			resultList.add(item);
		}
		return resultList;
	}

	/**
	 * 获取所属资格目标
	 * @param zgid
	 * @return
	 */
	public String getSszgmb(String zgid) {
		ZgdyPO zgdyPO=zgdyPOMapper.selectById(zgid);
		return zgdyPO.getSszgmbdm();
	}

	/**
	 * 获取修改通过名单的资格条件的sql语句
	 * @param qualList
	 * @param pcid
	 * @return
	 */
	public String getUpdatePassConditionSql(List<QualConfigItem> qualList, String pcid) {
		//1、获取所有的满足的条件
		String passCondition=null;
		for (QualConfigItem item:qualList) {
			if(passCondition==null){
				passCondition = item.getZgxName();
			}else{
				passCondition += ";"+item.getZgxName();
			}
		}
		String condition="'"+passCondition+"'";
		//2、拼凑要修改的信息
		for (QualConfigItem item:qualList) {
			List<String> yhtszdList=item.getYhtszdList();
			List<String> yhtsSqlList = item.getYhtsSqlList();
			condition=getPassCondition(yhtszdList,yhtsSqlList,condition,0);
		}
		//3、拼凑修改的sql语句
		//修改信息为空时数据库存的信息是""
		if("'null'".equals(condition)){
			condition="''";
		}
		System.out.println("condition======"+condition);
		String sql="update ZHXG_ZG_ZGGLMD t set t.fhtj="+condition+" where t.zt='1'";
		return sql;
	}

	/**
	 * 拼凑通过的理由的sql语句
	 * @param sql
	 * @param index
	 * @return
	 */
	public String getPassCondition(List<String> yhtszdList,List<String> yhtsSqlList,String sql,Integer index){
		//说明没有用户提示字段
        if(yhtszdList.size()<=0){
            return sql;
        }
        //说明已经全部拼凑完成了
        if(index==yhtszdList.size()){
            return sql;
        }
		String sql1="replace("+sql+",'${"+yhtszdList.get(index)+"}',("+yhtsSqlList.get(index)+"))";
		return getPassCondition(yhtszdList,yhtsSqlList,sql1,index+1);
	}
	
	/**
	 * 设置导入的进度，这个是正常的状态
	 * @param percent 导入进度 1-99
	 * @param processTip 当前状态的提示
	 */
	public void setProgressSuccessStatus(Integer percent,String processTip) {
		Map<String,Object> map=new HashMap<>();
		map.put("percent", percent);
		map.put("processTip", processTip);
		map.put("status", "success");
		NHRedisUtils.addRedis("process"+pcid, 10, map);
	}
	
	/** 
	 * 设置导入的进度，这个是已经操作失败了
	 * @param percent 导入进度 1-99
	 * @param processTip 当前状态的提示
	 */
	public void setProgressFailureStatus(Integer percent,String processTip) {
		Map<String,Object> map=new HashMap<>();
		map.put("percent", percent);
		map.put("processTip", processTip);
		map.put("status", "failure");
		NHRedisUtils.addRedis("process"+pcid, 10, map);
	}

	/**
	 * 将每个资格的名单转为Map
	 * @param matchQuaList 从小到大有序的名单列表（按名单列表大小排序）
	 * @return
	 */
	public List<Map<String, QualificationMatchInfo>> convertToMap(List<List<QualificationMatchInfo>> matchQuaList) {
		List<Map<String, QualificationMatchInfo>> quaMapList = new ArrayList<>();
		for (int i = 0; i < matchQuaList.size(); i++) {
			List<QualificationMatchInfo> maps = matchQuaList.get(i);
			Map<String, QualificationMatchInfo> m = new HashMap<>();
			for (QualificationMatchInfo qualificationMatchInfo : maps) {
				QualificationMatchInfo qualificationMatchInfo1 = m.get(qualificationMatchInfo.getId());
				if (qualificationMatchInfo1 == null) {
					// 当前资格不存在该对象
					qualificationMatchInfo1 = new QualificationMatchInfo();
					BeanUtils.copyProperties(qualificationMatchInfo, qualificationMatchInfo1);
					m.put(qualificationMatchInfo1.getId(), qualificationMatchInfo1);
				} else {
					// 当前资格存在该对象，符合条件拼接在一起
					qualificationMatchInfo1.concat(qualificationMatchInfo.getFhtj(),"或");
					m.put(qualificationMatchInfo1.getId(), qualificationMatchInfo1);
				}
			}
			quaMapList.add(m);
		}
		return quaMapList;
	}

	/**
	 * 匹配出通过的名单
	 * @param matchQuaList 从小到大有序的名单列表（按名单列表大小排序）
	 * @param quaMapList 资格名单的Map
	 * @return
	 */
	public List<QualificationMatchInfo> match(List<List<QualificationMatchInfo>> matchQuaList, List<Map<String, QualificationMatchInfo>> quaMapList) {
		List<QualificationMatchInfo> resultList = new ArrayList<>();
		// 以最小的一个资格资格名单作为判断基础，判断是否符合其他条件
		List<QualificationMatchInfo> matchInfos = matchQuaList.get(0);
		for (QualificationMatchInfo matchInfo : matchInfos) {
			boolean match = true;
			for (Map<String, QualificationMatchInfo> map : quaMapList) {
				QualificationMatchInfo info = map.get(matchInfo.getId());
				if (info == null) { // 存在不符合的条件
					match = false;
					break;
				}
				matchInfo.concat(info.getFhtj(),";");
			}
			if (match) {
				resultList.add(matchInfo);
			}
		}
		return resultList;
	}

	/**
	 * 将过滤名单的数据插入临时名单
	 * @param zglsmdService
	 * @param type 名单类型
	 * @param pcid 资格批次id
	 */
	public void insertResult(ZglsmdService zglsmdService, String type, String pcid) {
		if ("all".equals(type)) {
			zglsmdService.insertAllPoByPcid(pcid);
		} else if("match".equals(type)) {
			zglsmdService.insertFhPoByPcid(pcid);
		} else {
			zglsmdService.insertNoFhPoByPcid(pcid);
		}
	}

	public void setProgressSuccessStatus(int percent, String tip, boolean async) {
		if (async) {
			setProgressSuccessStatus(percent,tip);
		}
	}

	public void setProgressFailureStatus(int percent, String tip, boolean async) {
		if (async) {
			setProgressFailureStatus(percent,tip);
		}
	}
}
