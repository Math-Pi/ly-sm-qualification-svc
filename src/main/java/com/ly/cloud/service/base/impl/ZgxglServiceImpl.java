package com.ly.cloud.service.base.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.mapper.base.ZgxZgPOMapper;
import com.ly.cloud.mapper.base.ZgxflZgxPOMapper;
import com.ly.cloud.mapper.base.ZgxglGlgzPOMapper;
import com.ly.cloud.mapper.base.ZgxglPOMapper;
import com.ly.cloud.service.base.ZgxglService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.cloud.dto.base.ZgxglDTO;
import com.ly.cloud.entity.base.ZgxglGlgzPO;
import com.ly.cloud.entity.base.ZgxglPO;
import com.ly.cloud.vo.base.ZgxglVO;


@Service
public class ZgxglServiceImpl implements ZgxglService {

    private static Logger logger = LoggerFactory.getLogger(ZgxglServiceImpl.class);

    @Autowired
    private ZgxglPOMapper zgxglPOMapper;
    
    @Autowired
    private ZgxglGlgzPOMapper zgxglGlgzPOMapper;
    @Autowired
    private ZgxflZgxPOMapper zgxflZgxPOMapper;
    @Autowired
    private ZgxZgPOMapper zgxZgPOMapper;

	/**
	 * @Title: get
	 * @Description: 根据主键，查询一条数据
	 * @author: yizhiqiang
	 * @date: 2019-02-18 09:35:37
	 * @param pkid
	 * @return
	 * @see com.ly.cloud.service.base.ZgxglService#get(java.lang.String)
	 */
	@Override
	public ZgxglVO get(String pkid) {
    	try {
			ZgxglPO po = zgxglPOMapper.selectById(pkid);
			return NHBeanUtils.clonePoToVo(po, ZgxglVO.class);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	@Override
	public Object getInitData(String pkid) {
    	try {
			return zgxglPOMapper.selectInitData(pkid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
	
	@Override
	public Object getHtid(String ssfw) {
    	try {
			return zgxglPOMapper.selectHtid(ssfw);
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
     * @see com.ly.cloud.service.base.ZgxglService#insert(com.ly.cloud.dto.base.ZgxglDTO)
     */
    @Override
	public Integer insert(ZgxglDTO dto){
    	try {
			ZgxglPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglPO.class);
			return zgxglPOMapper.insert(po);
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
     * @see com.ly.cloud.service.base.ZgxglService#update(com.ly.cloud.dto.base.ZgxglDTO)
     */
    @Override
    public Integer update(ZgxglDTO dto) {
        try {
        	ZgxglPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglPO.class);
            return zgxglPOMapper.updateById(po);
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
     * @see com.ly.cloud.service.base.ZgxglService#deleteByMulti(java.util.List)
     */
    @Override
    @Transactional
	public int deleteByMulti(List<String> pkids) {
		try {
			if (pkids.size() > 0) {
				zgxglPOMapper.deleteBatchIds(pkids);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}
    
    @Transactional
    @Override
	public int forceDeleteByMulti(List<String> pkids) {
    	try {
    		if (pkids.size()>0) {
    			// 删除引用zgx表的数据
    			zgxglGlgzPOMapper.deleteMultiByQual(pkids);
    			zgxflZgxPOMapper.deleteMultiByQual(pkids);
    			zgxZgPOMapper.deleteMultiByQual(pkids);
    			// 删除zgx表中的数据
    			zgxglPOMapper.deleteBatchIds(pkids);
    		}
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
		return pkids.size();
	}

	@Override
   	public Object getSsfw() {
       	try {
   			return zgxglPOMapper.getSsfw();
   		} catch (Exception e) {
   			logger.error(e.getMessage());
   			NHExpHandleUtils.throwesException(e);
   		}
       	return null;
   	}
    
    @Override
	public Object getQual(String pkid) {
    	try {
			return zgxglPOMapper.getAllGz(pkid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
    
    @Override
	public Object getZgst(String pkid) {
    	try {
			return zgxglPOMapper.getZgst(pkid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
    
    @Override
	public Object getZgtjzd(String pkid) {
    	try {
			return zgxglPOMapper.getZgtjzd(pkid);
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
    
    
    public List<Map<String,String>> getAllGzB(String zgxid){
		return zgxglPOMapper.getAllGzB(zgxid);	
    }
     
    
    public void insertGLGZ(ZgxglGlgzPO glgzpo) {
    	zgxglPOMapper.insertGLGZ(glgzpo);
    }
    
    public void deleteGLGZ(String glgzid) {
    	zgxglPOMapper.deleteGLGZ(glgzid);
    }
	
	public void updateGLGZ(String hcglgzid ,String glgzid) {
		zgxglPOMapper.updateGLGZ(hcglgzid, glgzid);
	}
	
	//处理list的值
	public List<List<Map<String,String>>> getListListMap(String pkid){
		try {
			List<Map<String, String>> list = (List<Map<String, String>>) zgxglPOMapper.getAllGz(pkid);

			List<Map<String, String>> hc = new ArrayList<Map<String, String>>();
			List<Map<String, String>> wuhc = new ArrayList<Map<String, String>>();

			for(int i=0;i<list.size();i++){
				//取所有的互斥项id存入hc
				if(!"".equals(list.get(i).get("HCGLGZID"))){ 
					hc.add(list.get(i)) ;                
				}else{
					wuhc.add(list.get(i));
				}    	  	 
			}
			List<List<Map<String,String>>> arr=new ArrayList<List<Map<String, String>>>();//拼接容器
			//将带有互斥id的hcid和其他所有的规则id相比,
			for(int i=0;i<hc.size();i++){
				for(int j=0;j<list.size();j++){
					//如果互斥id==规则id则拼接起来
					if(hc.get(i).get("HCGLGZID") != null && hc.get(i).get("HCGLGZID").equals(list.get(j).get("GLGZID"))){
						boolean f=false;
						//判断arr里面是否存在目前匹配的这个对象
						for(int r=0;r<arr.size();r++){
							//***yihuan
							for(int k=0;k<arr.get(r).size();k++){
								if(arr.get(r).get(k).equals(hc.get(i))){
									arr.get(r).add(list.get(j));
									f=true;
								}	  
							}
						}
						if(f==false){
							List<Map<String,String>> news=new ArrayList<Map<String, String>>();//拼接容器
							news.add(hc.get(i));
							news.add(list.get(j));
							arr.add(news);
						}
					}   		 
				} 
			}  
			for(int i=0;i<list.size();i++){
				boolean f=false;
				for(int j=0;j<arr.size();j++){
					for(int k=0;k<arr.get(j).size();k++){
						//判断是否在容器中
						if(arr.get(j).get(k).equals(list.get(i))){
							f=true;
							break;
						}
					}
					if(f==true){
						break;
					} 
				}
				if(f==false){
					List<Map<String,String>> news=new ArrayList<Map<String, String>>();
					news.add(list.get(i));
					arr.add(news);
				}
			}
			return arr;
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return null;
	}
    
	//更新保存
	public String updatePreservation(List<Object> allData) {
		try {
			Map<String,String> map=(Map<String, String>) allData.get(0);
			ZgxglDTO dto=new ZgxglDTO();
			dto.setZgxmc(map.get("zgxmc"));
			dto.setSszgmbdm((String)map.get("sszgmbdm"));
			dto.setZgst((String)map.get("zgst"));
			dto.setZgtjzd((String)map.get("zgtjzd"));
			dto.setZgtjzdlxdm((String)map.get("zgtjzdlxdm"));
			dto.setMs((String)map.get("ms"));
			dto.setHdfwid((String)map.get("hdfwid"));
			dto.setZgxid((String)map.get("zgxid"));
			dto.setXlkbz((String)map.get("xlkbz"));
			dto.setYhtszd((String)map.get("yhtszd"));
			dto.setYhtsxx((String)map.get("yhtsxx"));
			Map<String,List<List<Map<String,String>>>>  can = (Map<String, List<List<Map<String, String>>>>) allData.get(1);
			
			ZgxglPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglPO.class);
			zgxglPOMapper.updateById(po);
			
			String zgxid;
			
			List<ZgxglGlgzPO> updateList = new ArrayList<ZgxglGlgzPO>();
			List<ZgxglGlgzPO> insertList = new ArrayList<ZgxglGlgzPO>();
			List<String> deleteList = new ArrayList<String>();
			for(Entry<String, List<List<Map<String, String>>>> entry:can.entrySet()){  
				zgxid= entry.getKey();
				List<List<Map< String,String>>> cglsj= entry.getValue();
				List<Map<String, String>> sjkList=  zgxglPOMapper.getAllGzB(zgxid);//数据库的数据
				List<Map<String,String>> newList = new ArrayList<Map<String,String>>();//处理完前台的一个数组
				for(int a=0;a<cglsj.size();a++) {
					for(int b=0;b<cglsj.get(a).size();b++) {
						newList.add(cglsj.get(a).get(b));
					}
				}
				for(int i=0;i<newList.size();i++) {
					boolean bool=true;
					for(int j=0;j<sjkList.size();j++) {
						//在数据库存在
						if(newList.get(i).get("GLGZID").equals(sjkList.get(j).get("GLGZID"))) {
							//在判断是否有互斥id
							bool=false;
							if(newList.get(i).get("HCGLGZID")!=null && sjkList.get(j).get("HCGLGZID")==null) {
								//有互斥id
								//update操作
								ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
								glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
								glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
								glgzpo.setZgxid(zgxid);
								glgzpo.setZd(newList.get(i).get("ZD"));
								glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
								glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
								glgzpo.setCsz(newList.get(i).get("CSZ"));
								glgzpo.setHcglgzid(newList.get(i).get("HCGLGZID"));
								glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
								updateList.add(glgzpo);
							}else if(newList.get(i).get("HCGLGZID")==null && sjkList.get(j).get("HCGLGZID")!=null){
								ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
								glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
								glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
								glgzpo.setZgxid(zgxid);
								glgzpo.setZd(newList.get(i).get("ZD"));
								glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
								glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
								glgzpo.setCsz(newList.get(i).get("CSZ"));
								glgzpo.setHcglgzid(null);
								glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
								updateList.add(glgzpo);
							}
						}
					}	
					if(bool==true) {
						//不存在就add
						ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
						glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
						glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
						glgzpo.setZgxid(zgxid);
						glgzpo.setZd(newList.get(i).get("ZD"));
						glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
						glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
						glgzpo.setCsz(newList.get(i).get("CSZ"));
						glgzpo.setHcglgzid(newList.get(i).get("HCGLGZID"));
						glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
						insertList.add(glgzpo);
					}
				}
				for(int k=0;k<sjkList.size();k++) {
					boolean flag=false;
					for(int x=0;x<newList.size();x++) {
						if(sjkList.get(k).get("GLGZID").equals(newList.get(x).get("GLGZID"))) {
							flag=true;
							break;
						}
					}
					if(flag==false) {
						//删除
						deleteList.add(sjkList.get(k).get("GLGZID"));
					}
				}
			} 
			if(updateList.size()!=0){
				zgxglGlgzPOMapper.updatePOBatch(updateList);
			}
			if(insertList.size()!=0){
				zgxglGlgzPOMapper.insertPOBatch(insertList);
			}
			if(deleteList.size()!=0){
				zgxglGlgzPOMapper.deleteBatchIds(deleteList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			NHExpHandleUtils.throwesException(e);
		}
    	return "0";
	}
	
	//新增保存
	public String insertPreservation (List<Object> allData) {
		try {
			Map<String,String> map=(Map<String, String>) allData.get(0);
			ZgxglDTO dto=new ZgxglDTO();
			dto.setZgxmc(map.get("zgxmc"));
			dto.setSszgmbdm((String)map.get("sszgmbdm"));
			dto.setZgst((String)map.get("zgst"));
			dto.setZgtjzd((String)map.get("zgtjzd"));
			dto.setZgtjzdlxdm((String)map.get("zgtjzdlxdm"));
			dto.setMs((String)map.get("ms"));
			dto.setHdfwid((String)map.get("hdfwid"));
			dto.setZgxid((String)map.get("zgxid"));
			dto.setXlkbz((String)map.get("xlkbz"));
			dto.setYhtszd((String)map.get("yhtszd"));
			dto.setYhtsxx((String)map.get("yhtsxx"));
			Map<String,List<List<Map<String,String>>>>  can = (Map<String, List<List<Map<String, String>>>>) allData.get(1);
			
			ZgxglPO po=NHBeanUtils.cloneDtoToPo(dto, ZgxglPO.class);
			zgxglPOMapper.insert(po);
			
			
			String zgxid;
			List<ZgxglGlgzPO> updateList = new ArrayList<ZgxglGlgzPO>();
			List<ZgxglGlgzPO> insertList = new ArrayList<ZgxglGlgzPO>();
			List<String> deleteList = new ArrayList<String>();
			for(Entry<String, List<List<Map<String, String>>>> entry:can.entrySet()){  
				zgxid= entry.getKey();
				List<List<Map< String,String>>> cglsj= entry.getValue();
				List<Map<String, String>> sjkList=  zgxglPOMapper.getAllGzB(zgxid);//数据库的数据
				List<Map<String,String>> newList = new ArrayList<Map<String,String>>();//处理完前台的一个数组
				for(int a=0;a<cglsj.size();a++) {
					for(int b=0;b<cglsj.get(a).size();b++) {
						newList.add(cglsj.get(a).get(b));
					}
				}
				for(int i=0;i<newList.size();i++) {
					boolean bool=true;
					for(int j=0;j<sjkList.size();j++) {
						//在数据库存在
						if(newList.get(i).get("GLGZID").equals(sjkList.get(j).get("GLGZID"))) {
							//在判断是否有互斥id
							bool=false;
							if(newList.get(i).get("HCGLGZID")!=null && sjkList.get(j).get("HCGLGZID")==null) {
								//有互斥id
								//update操作
								ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
								glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
								glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
								glgzpo.setZgxid(zgxid);
								glgzpo.setZd(newList.get(i).get("ZD"));
								glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
								glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
								glgzpo.setCsz(newList.get(i).get("CSZ"));
								glgzpo.setHcglgzid(newList.get(i).get("HCGLGZID"));
								glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
								updateList.add(glgzpo);
							}else if(newList.get(i).get("HCGLGZID")==null && sjkList.get(j).get("HCGLGZID")!=null){
								ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
								glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
								glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
								glgzpo.setZgxid(zgxid);
								glgzpo.setZd(newList.get(i).get("ZD"));
								glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
								glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
								glgzpo.setCsz(newList.get(i).get("CSZ"));
								glgzpo.setHcglgzid(null);
								glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
								updateList.add(glgzpo);
							}
						}
					}	
					if(bool==true) {
						//不存在就add
						ZgxglGlgzPO glgzpo=new ZgxglGlgzPO();
						glgzpo.setGlgzid(newList.get(i).get("GLGZID"));
						glgzpo.setGlgzmc(newList.get(i).get("GLGZMC"));
						glgzpo.setZgxid(zgxid);
						glgzpo.setZd(newList.get(i).get("ZD"));
						glgzpo.setYsfdm(newList.get(i).get("YSFDM"));
						glgzpo.setZhqgzdm(newList.get(i).get("ZHQGZDM"));
						glgzpo.setCsz(newList.get(i).get("CSZ"));
						glgzpo.setHcglgzid(newList.get(i).get("HCGLGZID"));
						glgzpo.setZdlxdm(newList.get(i).get("ZDLXDM"));
						insertList.add(glgzpo);
					}
				}
				for(int k=0;k<sjkList.size();k++) {
					boolean flag=false;
					for(int x=0;x<newList.size();x++) {
						if(sjkList.get(k).get("GLGZID").equals(newList.get(x).get("GLGZID"))) {
							flag=true;
							break;
						}
					}
					if(flag==false) {
						//删除
						deleteList.add(sjkList.get(k).get("GLGZID"));
					}
				}
			} 
			if(updateList.size()!=0){
				zgxglGlgzPOMapper.updatePOBatch(updateList);
			}
			if(insertList.size()!=0){
				zgxglGlgzPOMapper.insertPOBatch(insertList);
			}
			if(deleteList.size()!=0){
				zgxglGlgzPOMapper.deleteBatchIds(deleteList);
			}
   		} catch (Exception e) {
   			logger.error(e.getMessage());
   			NHExpHandleUtils.throwesException(e);
   		}
       	return "0";
	}
	
}