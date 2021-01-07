package com.ly.cloud.controller.Interface;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.service.base.ZgdyService;
import com.ly.cloud.service.base.ZglsmdService;
import com.ly.cloud.vo.base.ZgdyVO;
import com.ly.cloud.vo.base.ZgxLxVO;
import com.ly.cloud.vo.base.ZgxZgVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

 /**
 * @ClassName:  ZgjdJtzglController
 * @Description: 综合学工-资格鉴定-静态资格管理相关服务API
 * @author: Administrator
 * @date: 2019-02-18 11:37:07
 * @Copyright: 2018 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */
@RestController
@RequestMapping("/interface")
@Api("综合学工-资格鉴定-组件使用接口相关服务API")
public class InterfaceController {

    private static Logger logger = LoggerFactory.getLogger(InterfaceController.class);
    @Autowired
    private ZgdyService zgdyService;
    @Autowired
    private ZglsmdService zglsmdService;
    
    @ApiOperation(value = "根据资格ID获取这个资格的所有信息" , notes = "根据资格ID获取这个资格的所有信息" , httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "zgid", dataType = "String", required = true, value = "资格ID")
    })
    @RequestMapping(value = "/qualifications/{zgid}/get", method = RequestMethod.GET)
    public JsonResult getQualifications(@PathVariable String zgid) {
        try {
              List<ZgxZgVO> zgxList=zgdyService.selectAllZgxByZgid(zgid);     		
            return JsonResult.success(zgxList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    @ApiOperation(value = "查询单条资格" , notes = "根据id查询查询单条资格" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/get", method = RequestMethod.GET)
    public JsonResult get(@PathVariable String pkid) {
        try {
        	ZgdyVO vo = zgdyService.get(pkid);
            return JsonResult.success(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    @ApiOperation(value = "获取某一个分类下的所有的资格项信息" , notes = "获取某一个分类下的所有的资格项信息" , httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", name = "flbz", dataType = "String", required = true, value = "分类标志"),
        @ApiImplicitParam(paramType = "query", name = "mbdm", dataType = "String", required = true, value = "目标代码")
    })
    @RequestMapping(value = "/qualifications/term/{flbz}/get", method = RequestMethod.GET)
    public JsonResult getQualificationsTerm(@PathVariable(value="flbz",required=false) String flbz,@RequestParam String mbdm, @RequestParam(required = false,value = "tags") String tags) {
        try {
            String xxdm=zgdyService.selectDqxxInfo();
            logger.info("学校代码："+xxdm+",分类标志："+flbz+",目标代码："+mbdm);
        	List<ZgxLxVO> zgxLxVO=zgdyService.selectZgxByFlmAndMbdm(flbz, mbdm, xxdm,tags);
            return JsonResult.success(zgxLxVO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-02-18 11:42:32
     */
    @ApiOperation(value = "保存资格信息" , notes = "保存资格信息" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格保存对象", required = true, dataType = "QualificationsDTO")
    })
    @RequestMapping(value = "/qualifications/save", method = RequestMethod.POST)
    public JsonResult saveQualifications(@RequestBody QualificationsDTO dto) {
        try {
        	zgdyService.saveQualifications(dto);
            return JsonResult.success(null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    @ApiOperation(value = "删除资格信息" , notes = "删除资格信息" , httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "path", name = "quaId", value = "资格id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/{quaId}/delete", method = RequestMethod.GET)
    public JsonResult deleteQualifications(@PathVariable String quaId) {
        try {
        	Integer rs = zgdyService.deleteQualifications(quaId);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    @ApiOperation(value = "获得资格名单表格数据量" , notes = "获得资格名单表格数据量" , httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "path", name = "pcid", value = "批次id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/{pcid}/getDataCount", method = RequestMethod.GET)
    public JsonResult getDataCount(@PathVariable(value="pcid",required=false) String pcid) {
        try {
        	Integer rs = zglsmdService.getDataCount(pcid);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
