package com.ly.cloud.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.base.ZgxflZgxDTO;
import com.ly.cloud.service.base.ZgxflZgxService;
import com.ly.cloud.vo.base.ZgxflZgxVO;
import com.ly.cloud.vo.classification.ClassificationDetailVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

 /**
 * @ClassName:  ClassificationManageController
 * @Description: 综合学工-资格鉴定-资格项管理过滤规则相关服务API
 * @author: Administrator
 * @date: 2019-02-18 11:44:04
 * @Copyright: 2018 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */
@RestController
@RequestMapping("/classification")
@Api("综合学工-资格鉴定-资格项分类管理相关服务API")
public class ClassificationManageController {

	private static Logger logger = LoggerFactory.getLogger(ClassificationManageController.class);
    @Autowired
    private ZgxflZgxService zgxflZgxService;
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:42:32
     */
    @ApiOperation(value = "添加资格项分类资格项" , notes = "通过录入的参数，添加一条资格项分类资格项" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格项分类资格项", required = true, dataType = "ZgxflZgxDTO")
    })
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody ZgxflZgxDTO dto) {
        try {
            Integer rs = zgxflZgxService.insert(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
  
  	/**
     * @author: Administrator
     * @date: 2019-02-18 11:42:32
     */
    @ApiOperation(value = "更新资格项分类资格项" , notes = "通过录入的参数，修改一条资格项分类资格项" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格项分类资格项", required = true, dataType = "ZgxflZgxDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ZgxflZgxDTO dto) {
        try {
            int rs = zgxflZgxService.update(dto); 
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:42:32
     */
    @ApiOperation(value = "批量删除资格项分类资格项" , notes = "通过主键，批量删除多条资格项分类资格项" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键") 
    })
    @RequestMapping(value = "/deleteByMulti", method = RequestMethod.POST)
    public JsonResult deleteByMulti(@RequestBody List<String> pkids) {
        try {
            int rs = zgxflZgxService.deleteByMulti(pkids);
            return JsonResult.success(rs); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:42:32
     */
    @ApiOperation(value = "查询单条资格项分类资格项" , notes = "根据id查询查询单条资格项分类资格项" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/get", method = RequestMethod.GET)
    public JsonResult get(@PathVariable String pkid) {
        try {
        	ZgxflZgxVO vo = zgxflZgxService.get(pkid);
            return JsonResult.success(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * 
     * @Title: getDetail 
     * @Description:  查询资格分类的详情
     * @author: zengweijin
     * @date: 2019-02-20 11:49
     * @param: @param fldm
     * @param: @return      
     * @return: JsonResult      
     * @throws
     */
    @ApiOperation(value = "查询资格分类的详情", notes = "根基fldm查询该分类下的所有服务", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "path", name = "fldm", dataType = "String", required = true, value = "分类代码字段")
    })
    @RequestMapping(value = "/{fldm}/getDetail", method = RequestMethod.GET)
    public JsonResult getDetail(@PathVariable(value="fldm",required=false) String fldm) {
    	try {
        	List<ClassificationDetailVO> list = zgxflZgxService.getDetail(fldm);
            return JsonResult.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * 
     * @Title: deleteQualificationsByMulti 
     * @Description: 批量删除分类下的所有资格项 
     * @author: zengweijin
     * @date: 2019-02-21 11:31
     * @param: @param fldms
     * @param: @return      
     * @return: JsonResult      
     * @throws
     */
    @ApiOperation(value = "批量删除分类下的所有资格项" , notes = "通过主键，批量删除多条资格项分类资格项" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "fldms", dataType = "List", required = true, value="服务分类代码") 
    })
    @RequestMapping(value = "/deleteQualificationsByMulti", method = RequestMethod.POST)
    public JsonResult deleteQualificationsByMulti(@RequestBody List<String> fldms) {
        try {
        	if (fldms == null || fldms.size() == 0) {
        		return JsonResult.failure(null); 
        	}
        	// 查询需要删除资格项分类id
            List<String> flids = zgxflZgxService.queryQuaIdByMulti(fldms);
            int rs = zgxflZgxService.deleteByMultiFlid(flids);
            return JsonResult.success(rs); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * 
     * @Title: deleteQualifications 
     * @Description: 批量删除分类下的某个项目的资格项
     * @author: zengweijin
     * @date: 2019-02-21 14:00
     * @param: @param dto
     * @param: @return      
     * @return: JsonResult      
     * @throws
     */
    @ApiOperation(value = "批量删除分类下的某个项目的资格项" , notes = "通过主键，批量删除多条项目资格项" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "flids", dataType = "List", required = true, value="分类id") 
    })
    @RequestMapping(value = "/deleteQualifications", method = RequestMethod.POST)
    public JsonResult deleteQualifications(@RequestBody List<String> flids) {
        try {
            int rs = zgxflZgxService.deleteByMultiFlid(flids);
            return JsonResult.success(rs); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    /**
     * 
     * @Title: insertMulti 
     * @Description: 添加资格项分类资格项  
     * @author: zengweijin
     * @date: 2019-02-22 11:54
     * @param: @param classificationQuas
     * @param: @return      
     * @return: JsonResult      
     * @throws
     */
    @ApiOperation(value = "添加资格项分类资格项" , notes = "通过录入的参数，添加多条资格项分类资格项" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "classificationQuas", value = "资格项分类资格项", required = true, dataType = "ZgxflZgxDTO")
    })
    @RequestMapping(value = "/insertMulti", method = RequestMethod.POST)
    public JsonResult insertMulti(@RequestBody List<ZgxflZgxDTO> classificationQuas) {
        try {
            Integer rs = zgxflZgxService.insertMulti(classificationQuas);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    /**
     * 
     * @Title: getClassification 
     * @Description: 查询服务分类 
     * @author: zengweijin
     * @date: 2019-03-04 10:27
     * @param: @param param
     * @param: @return      
     * @return: JsonResult      
     * @throws
     */
    @ApiOperation(value = "查询服务分类" , notes = "根据学校代码和分类代码查询分类" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "param", value = "查询服务分类", required = true, dataType = "Map")
    })
    @RequestMapping(value = "/getClassification", method = RequestMethod.POST)
	public JsonResult getClassification(@RequestBody Map<String,String> param) {
		try {
			String rs = zgxflZgxService.getClassification(param);
				return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}
    
}
