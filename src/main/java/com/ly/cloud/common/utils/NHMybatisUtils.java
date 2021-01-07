package com.ly.cloud.common.utils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ly.zhxg.utils.NHCollectionUtils;


@Component
public class NHMybatisUtils {
	private static Logger logger = LoggerFactory.getLogger(NHMybatisUtils.class);
	
	 @Autowired
	 private SqlSessionFactory sqlSessionFactory;
	 private static NHMybatisUtils utils;
		
    @PostConstruct  
    public void init() {  
    	utils = this;  
    	utils.sqlSessionFactory = this.sqlSessionFactory;  
    } 
	 
	/**
     * 批量提交数据
     * @param mybatisSQLId SQL语句在Mapper XML文件中的ID
     * @param list 要提交的数据列表
     */
    public static <T> void batchCommit(Class<?> classz,String mybatisSQLId, List<T> list) {
       if(list==null) {
    	   return;
       }
	   SqlSession session = null;
       int commitCountEveryTime=500;
       try {
    	   Long startTime = System.currentTimeMillis();
    	   session = utils.sqlSessionFactory.openSession(ExecutorType.BATCH, false);
           List<List<T>> groupList = NHCollectionUtils.fixedGrouping(list, commitCountEveryTime);
           for (List<T> tempList : groupList) {
        	   session.insert(classz.getName()+"."+mybatisSQLId, tempList);
               session.commit();
               session.clearCache();
           }
           Long endTime = System.currentTimeMillis();
           logger.error("批量插入数据耗时：" + (endTime - startTime) + "毫秒");
      } catch (Exception e) {
          logger.error("batchCommit error!", e);
           e.printStackTrace();
           session.rollback();
       } finally {
           if (session != null) {
               session.close();
           }
      }
   }
    
    public static <T> void batchCommit(Class<?> classz, List<T> list) {
    	batchCommit(classz,"insertPOBatch" , list);
    }
}
