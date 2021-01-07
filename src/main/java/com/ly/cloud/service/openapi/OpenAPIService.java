package com.ly.cloud.service.openapi;

import java.util.List;

public interface OpenAPIService {
	
	/**
	 * 
	 * @Title: deleteQua 
	 * @Description:  单个删除资格接口
	 * @author: zengweijin
	 * @date: 2019-04-26 16:59
	 * @param: @param quaId
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	Integer deleteQua(String quaId);
	
	/**
	 * 
	 * @Title: deleteMultiQua 
	 * @Description:  批量删除资格的接口
	 * @author: zengweijin
	 * @date: 2019-04-26 17:06
	 * @param: @param quaIds
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	Integer deleteMultiQua(List<String> quaIds);

}
