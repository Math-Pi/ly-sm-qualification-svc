package com.ly.cloud.controller.openapi;

import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZgxZgDTO;
import com.ly.cloud.service.base.ZgdyService;
import com.ly.cloud.vo.base.ZgdyVO;
import com.ly.cloud.vo.base.ZglsmdVO;
import com.ly.cloud.vo.base.ZgxZgVO;
import com.ly.zhxg.utils.NHBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.service.base.ZglsmdService;
import com.ly.cloud.service.openapi.OpenAPIService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: OpenAPIController
 * @Description: 资格鉴定提供的外部接口
 * @author zengweijin
 * @date: 2019-04-26 16:33
 * @Copyright: 2019 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */

@RestController
@RequestMapping("/openapi")
@Api("综合学工-资格鉴定-公共API")
public class OpenAPIController {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenAPIController.class);
	@Autowired
	private OpenAPIService openapiService;
	@Autowired
	private ZglsmdService zglsmdService;
	@Autowired
	private ZgdyService zgdyService;
	
	@ApiOperation(value = "根据资格ID和对象ID判断该对象是否符合相关资格", notes = "根据资格ID和对象ID判断该对象是否符合相关资格", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "zgid", dataType = "String", required = true, value = "资格ID"),
			@ApiImplicitParam(paramType = "query", name = "id", dataType = "String", required = true, value = "对象ID") })
	@RequestMapping(value = "/qualifications/checkOne", method = RequestMethod.GET)
	public JsonResult checkOneQualifications(@RequestParam String zgid, @RequestParam String id) {
		try {
			JsonResult json = zglsmdService.qualificationsCheckOne(zgid, id);
			return json;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}

	}

	@ApiOperation(value = "根据资格器ID获取所有资格名单", notes = "根据资格器ID获取所有资格名单", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "zgid", dataType = "String", required = true, value = "资格器ID"),
			@ApiImplicitParam(paramType = "query", name = "type", dataType = "String", required = true, value = "返回名单类型(all:返回所有名单,match:只返回满足条件名单,mismatch:只返回不满足条件名单)"),
			@ApiImplicitParam(paramType = "query", name = "returnType", dataType = "String", required = true, value = "返回值类型(1:返回临时名单，2:只返回是否成功，不用临时名单)") })
	@RequestMapping(value = "/qualifications/checkAll", method = RequestMethod.GET)
	public JsonResult checkAllQualifications(@RequestParam String zgid, @RequestParam String type,
			@RequestParam String returnType) {
		try {
			// 注释旧版实现
//			JsonResult json = zglsmdService.qualificationsCheckAll(zgid, type, returnType);

			ZgdyVO vo = zgdyService.get(zgid);
			JSONObject r = new JSONObject();
			if (vo == null) {
				r.put("code",-1);
				r.put("message","不存在这个资格id对应的数据");
				return JsonResult.success(r);
			}
			final QualificationsDTO dto = NHBeanUtils.cloneDtoToPo(vo, QualificationsDTO.class);
			List<ZgxZgVO> zgxList=zgdyService.selectAllZgxByZgid(zgid);
			List<ZgxZgDTO> zgxZgDTOS = Lists.transform(zgxList, new Function<ZgxZgVO, ZgxZgDTO>() {
				@Override
				public ZgxZgDTO apply(ZgxZgVO input) {
					ZgxZgDTO dto = new ZgxZgDTO();
					BeanUtils.copyProperties(input,dto);
					return dto;
				}
			});
			dto.setZgxList(zgxZgDTOS);
			String pcid = UUID.randomUUID().toString();
			zglsmdService.qualificationsGetBatchID_V2(dto,pcid,type,false);
			if ("1".equals(returnType)) {
				List<ZglsmdVO> list = zglsmdService.selectLsmdByPcid(pcid);
				r.put("list",list);
				r.put("pcid",pcid);
				r.put("code",1);
				r.put("message","操作成功");
			} else {
				r.put("pcid",pcid);
				r.put("code",1);
				r.put("message","操作成功");
			}
			return JsonResult.success(r);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}

	}
	
	/**
	 * 
	 * @Title: deleteQual 
	 * @Description:  单个删除资格接口与
	 * @author: zengweijin
	 * @date: 2019-04-26 16:57
	 * @param: @param quaId
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	
	@ApiOperation(value = "删除资格" , notes = "根据资格id，单个删除" , httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "path", name = "qualId", value = "资格id", required = true, dataType = "String")
    })
    @RequestMapping(value = "/qual/{qualId}/delete", method = RequestMethod.GET)
    public JsonResult deleteQua(@PathVariable String qualId) {
        try {
            Integer rs = openapiService.deleteQua(qualId);
            return JsonResult.success(rs);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
	
	@ApiOperation(value = "删除资格" , notes = "根据资格id，批量删除资格" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "qualIds", value = "资格id列表", required = true, dataType = "List")
    })
    @RequestMapping(value = "/qual/deleteMulti", method = RequestMethod.POST)
    public JsonResult deleteMultiQua(@RequestBody List<String> quaIds) {
        try {
            Integer rs = openapiService.deleteMultiQua(quaIds);
            return JsonResult.success(rs);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
	
	
}
