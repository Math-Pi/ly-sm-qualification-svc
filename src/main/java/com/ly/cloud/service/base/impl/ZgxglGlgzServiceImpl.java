package com.ly.cloud.service.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import com.ly.cloud.vo.base.*;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.common.utils.NHZipWriter;
import com.ly.cloud.mapper.base.ZgxglGlgzPOMapper;
import com.ly.cloud.service.base.ZgxglGlgzService;

import com.ly.zhxg.utils.NHIOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.ly.cloud.dto.base.ZgxglGlgzDTO;
import com.ly.cloud.entity.base.ZgxglGlgzPO;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;


@Service
public class ZgxglGlgzServiceImpl implements ZgxglGlgzService {

    private static Logger logger = LoggerFactory.getLogger(ZgxglGlgzServiceImpl.class);

    @Autowired
    private ZgxglGlgzPOMapper zgxglGlgzPOMapper;

    
	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgxglGlgzService#get(java.lang.String)
	 */
	@Override
	public ZgxglGlgzVO get(String pkid) {
    	try {
			ZgxglGlgzPO po = zgxglGlgzPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxglGlgzVO.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	//查询多条数据
	@Override
	public Object getPkid(String pkid) {
    	try {
			return zgxglGlgzPOMapper.selectPkid(pkid);
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
     * @see com.ly.cloud.service.base.ZgxglGlgzService#insert(com.ly.cloud.dto.base.ZgxglGlgzDTO)
     */
    @Override
	public Integer insert(ZgxglGlgzDTO dto){
    	try {
			ZgxglGlgzPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglGlgzPO.class);
			po.setGlgzid(UUID.randomUUID().toString());
			return zgxglGlgzPOMapper.insert(po);
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
     * @see com.ly.cloud.service.base.ZgxglGlgzService#update(com.ly.cloud.dto.base.ZgxglGlgzDTO)
     */
    @Override
    public Integer update(ZgxglGlgzDTO dto) {
        try {
        	ZgxglGlgzPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglGlgzPO.class);
            return zgxglGlgzPOMapper.updateById(po);
        } catch (Exception e) {
        	logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
        }
        return 0;
    } 
    
    //加入互斥id
    @Override
    public Integer updateHc(String hcglgzid, String glgzid) {
        try {
            return zgxglGlgzPOMapper.updateHc(hcglgzid,glgzid);
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
     * @see com.ly.cloud.service.base.ZgxglGlgzService#deleteByMulti(java.util.List)
     */
    @Override
	public int deleteByMulti(List<String> pkids) {
		try {
			zgxglGlgzPOMapper.deleteBatchIds(pkids);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}
    
    

	public void createSql(String xxdm, String ssfw, HttpServletRequest request, HttpServletResponse response) {
    	try {
    		Map<String,String> dodMap = new HashMap<>();
    		//将视图名和视图创建语句放入map
    		if(ssfw==null||"".equals(ssfw)) {
				List<String> stList = zgxglGlgzPOMapper.selectStListOnly(xxdm);
				dodMap = handleView(stList);
			}else {
				List<String> stList = zgxglGlgzPOMapper.selectStList(xxdm,ssfw);
				dodMap = handleView(stList);
			}
    		//将数据的语句放入map
			String schoolName = zgxglGlgzPOMapper.selectSchoolName(xxdm);
			String fwName = zgxglGlgzPOMapper.selectFwName(ssfw);
			String name = "";
			if(fwName!=null&&!"".equals(fwName)){
				name=schoolName+"-"+fwName;
			}else{
				name=schoolName;
			}
			String fileName=URLDecoder.decode("wfwzgjd","utf-8")+".sql";
			String str="";
			str=handleStr(str,xxdm,ssfw);
			dodMap.put(fileName, str);
			//将文件打包成zip
			NHZipWriter zipWriter = new NHZipWriter(name+".zip");
			for (Entry<String, String> entry : dodMap.entrySet()) { 
				zipWriter.addZipEntry(parse_inputStream(entry.getValue()), entry.getKey());
	        }
			FileInputStream fileInputStream = new FileInputStream(zipWriter.getTheZipFile());
			NHIOUtils.copy(request, response, name+".zip", fileInputStream);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
	}

	private Map<String,String> handleView(List<String> stList) {
		List<Map<String,Object>> listmap = zgxglGlgzPOMapper.selectView(stList);
		Map<String,String> mapList = new HashMap<>();
		for(Map<String,Object> map:listmap) {
			String str="";
			if(mapList.get((String)map.get("OWNER"))!=null) {
				str+= mapList.get((String)map.get("OWNER"));
			}
			str+="create or replace view "+(String)map.get("NAME")+" as\r"+(String)map.get("TEXT")+"\n\r";
			mapList.put((String)map.get("OWNER")+".sql",str);
		}
		return mapList;
	}

	//资格定义表拼接
	public String handleStr(String str,String xxdm, String ssfw){
		List<JtzglVO> jtzList = zgxglGlgzPOMapper.selectJtzList();
		if(jtzList!=null&&jtzList.size()>0){
			for(JtzglVO vo:jtzList){
				str+="delete from ZHXG_ZG_JTZGL where jtzid='"+vo.getJtzid()+"';\r";
				str+="insert into ZHXG_ZG_JTZGL values ( '"+vo.getJtzid()+"','"+vo.getYszmc()+"','"+
						vo.getZlxdm()+"','"+vo.getZhqfsdm()+"','"+
						vo.getCsz().replaceAll("\'","\"")+"','"+vo.getMs()+"' );\r";
			}
		}
		if(ssfw==null||"".equals(ssfw)) {
			str+=allData(str,xxdm);
		}else {
			str+=conditionData(str,xxdm,ssfw);
		}
		return str;
	}

	private String conditionData(String str, String xxdm ,String ssfw) {
		List<ZgdyVO> zgdyList = zgxglGlgzPOMapper.selectZgdyList(xxdm,ssfw);
		List<ZgxZgVO> zgxzgList = zgxglGlgzPOMapper.selectZgxzgList(xxdm,ssfw);
		List<ZgxglGlgzVO> glgzList = zgxglGlgzPOMapper.selectGlgzList(xxdm,ssfw);
		List<ZgxflZgxVO> zgxList = zgxglGlgzPOMapper.selectZgxList(xxdm,ssfw);
		List<ZgxglVO> zgList = zgxglGlgzPOMapper.selectZgList(xxdm,ssfw);
		str=addInsert(str,zgdyList,zgxzgList,glgzList,zgxList,zgList);
		return str;
	}

	private String allData(String str, String xxdm) {
		List<ZgdyVO> zgdyList = zgxglGlgzPOMapper.selectZgdyListOnly(xxdm);
		List<ZgxZgVO> zgxzgList = zgxglGlgzPOMapper.selectZgxzgListOnly(xxdm);
		List<ZgxglGlgzVO> glgzList = zgxglGlgzPOMapper.selectGlgzListOnly(xxdm);
		List<ZgxflZgxVO> zgxList = zgxglGlgzPOMapper.selectZgxListOnly(xxdm);
		List<ZgxglVO> zgList = zgxglGlgzPOMapper.selectZgListOnly(xxdm);
		str=addInsert(str,zgdyList,zgxzgList,glgzList,zgxList,zgList);
		return str;
	}

	private String addInsert(String str, List<ZgdyVO> zgdyList, List<ZgxZgVO> zgxzgList, List<ZgxglGlgzVO> glgzList,
			List<ZgxflZgxVO> zgxList, List<ZgxglVO> zgList) {
		//删除语句拼接
		for(ZgxflZgxVO vo:zgxList) {
			str+="delete from ZHXG_ZG_ZGXFL_ZGX where pkid = '"+vo.getPkid()+"';\r";
		}
		for(ZgxZgVO vo:zgxzgList) {
			str+="delete from ZHXG_ZG_ZGX_ZG where zgxdyid = '"+vo.getZgxdyid()+"';\r";
		}
		for(ZgxglGlgzVO vo:glgzList) {
			str+="delete from ZHXG_ZG_ZGXGL_GLGZ where glgzid = '"+vo.getGlgzid()+"';\r";
		}
		for(ZgdyVO vo:zgdyList) {
			str+="delete from ZHXG_ZG_ZGDY where zgid = '"+vo.getZgid()+"';\r";
		}
		for(ZgxglVO vo:zgList) {
			str+="delete from ZHXG_ZG_ZGXGL where zgxid = '"+vo.getZgxid()+"';\r";
		}
		//插入语句拼接
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(zgdyList!=null&&zgdyList.size()>0){
			for(ZgdyVO vo:zgdyList){
				String date = null;
				if(vo.getZhxgsj()==null||"".equals(vo.getZhxgsj())) {
					date=null;
				}else {
					date="to_date('"+sdf.format(vo.getZhxgsj())+"','YYYY-MM-DD HH24:MI:SS')";
				}
				str+="insert into ZHXG_ZG_ZGDY values ( "+col(vo.getZgid())+","+col(vo.getZgmc())+","+
						col(vo.getSszgmbdm())+","+date+" );\r";
			}
		}
		if(zgList!=null&&zgList.size()>0){
			for(ZgxglVO vo:zgList){
				str+="insert into ZHXG_ZG_ZGXGL values ( "+col(vo.getZgxid())+","+col(vo.getZgxmc())+","+
						col(vo.getSszgmbdm())+","+col(vo.getHdfwid())+","+col(vo.getZgst())+","+col(vo.getZgtjzd())+
						","+col(vo.getZgtjzdlxdm())+","+col(vo.getMs())+","+col(vo.getXlkbz())+","+col(vo.getYhtsxx())+
						","+col(vo.getYhtszd())+" );\r";
			}
		}
		if(glgzList!=null&&glgzList.size()>0){
			for(ZgxglGlgzVO vo:glgzList){
				str+="insert into ZHXG_ZG_ZGXGL_GLGZ values ( "+col(vo.getGlgzid())+","+col(vo.getGlgzmc())+","+
						col(vo.getZgxid())+","+col(vo.getZd())+","+col(vo.getYsfdm())+","+col(vo.getZhqgzdm())+
						","+col(vo.getCsz()).replaceAll("\'","\"")+
						","+col(vo.getHcglgzid())+","+col(vo.getZdlxdm())+" );\r";
			}
		}
		if(zgxList!=null&&zgxList.size()>0){
			for(ZgxflZgxVO vo:zgxList){
				str+="insert into ZHXG_ZG_ZGXFL_ZGX values ( "+
						col(vo.getPkid())+","+col(vo.getZgxid())+","+col(vo.getFlid())+" );\r";
			}
		}
		if(zgxzgList!=null&&zgxzgList.size()>0){
			for(ZgxZgVO vo:zgxzgList){
				str+="insert into ZHXG_ZG_ZGX_ZG values ( "+col(vo.getZgxdyid())+","+col(vo.getZgid())+","+
						col(vo.getZgxid())+","+col(vo.getZgxdymc())+","+col(vo.getYsfdm())+","+
						col(vo.getCsz()).replaceAll("\'","\"")+
						","+col(vo.getHczgxdyid())+","+vo.getPxh()+" );\r";
			}
		}
		return str;
	}
	
	public String col(String col){
		if(col==null||"".equals(col)){
			col=null;
		}else{
			col="'"+col+"'";
		}
		return col;
	}

	// String转inputStream
	public ByteArrayInputStream parse_inputStream(final String in) throws Exception {
		final ByteArrayInputStream input = new ByteArrayInputStream(in.getBytes());
		return input;
	}
}