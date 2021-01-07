package com.ly.cloud.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.base.JtzglDTO;
import com.ly.cloud.service.base.JtzglService;
import com.ly.cloud.vo.base.JtzglVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

 /**
 * @ClassName:  StaticValueManageController
 * @Description: 综合学工-资格鉴定-静态资格管理相关服务API
 * @author: Administrator
 * @date: 2019-02-18 11:37:07
 * @Copyright: 2018 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */

@RestController
@RequestMapping("/staticvalue")
@Api("综合学工-资格鉴定-静态资格管理相关服务API")
public class StaticValueManageController {

    private static Logger logger = LoggerFactory.getLogger(StaticValueManageController.class);
    @Autowired
    private JtzglService jtzglService;
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:37:07
     */
    @ApiOperation(value = "添加静态资格管理" , notes = "通过录入的参数，添加一条静态资格管理" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "静态资格管理", required = true, dataType = "JtzglDTO")
    })
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody JtzglDTO dto) {
        try {
            Integer rs = jtzglService.insert(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
  
  	/**
     * @author: Administrator
     * @date: 2019-02-18 11:37:07
     */
    
    @ApiOperation(value = "更新静态资格管理" , notes = "通过录入的参数，修改一条静态资格管理" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "静态资格管理", required = true, dataType = "JtzglDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody JtzglDTO dto) {
        try {
            int rs = jtzglService.update(dto); 
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:37:07
     */
    @ApiOperation(value = "批量删除静态资格管理" , notes = "通过主键，批量删除多条静态资格管理" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键") 
    })
    @RequestMapping(value = "/deleteByMulti", method = RequestMethod.POST)
    public JsonResult deleteByMulti(@RequestBody List<String> pkids) {
        try {
            int rs = jtzglService.deleteByMulti(pkids);
            return JsonResult.success(rs); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:37:07
     */
    @ApiOperation(value = "查询单条静态资格管理" , notes = "根据id查询查询单条静态资格管理" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/get", method = RequestMethod.GET)
    public JsonResult get(@PathVariable String pkid) {
        try {
        	JtzglVO vo = jtzglService.get(pkid);
            return JsonResult.success(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: zengweijin
     * @date: 2019-02-18 
     */
    @ApiOperation(value = "删除单条静态资格管理" , notes = "通过主键，检查是否被引用，没有则删除单条静态资格管理" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键") 
    })
    @RequestMapping(value = "/deleteAndCheck", method = RequestMethod.POST)
    public JsonResult deleteAndCheck(@RequestBody List<String> pkids) {
    	 try {
    		 //查询静态值是否被引用
         	boolean isrefs = jtzglService.checkReference(pkids.get(0));
         	if (!isrefs) {
         		int rs = jtzglService.deleteByMulti(pkids);
         		return JsonResult.success(rs);
         	} else {
         		HashMap<String,Boolean> map= new HashMap<>();
         		map.put("ref", true);
         		return JsonResult.success(map);
         	}
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
             return JsonResult.failure(e.getMessage());
         }
    }
}
