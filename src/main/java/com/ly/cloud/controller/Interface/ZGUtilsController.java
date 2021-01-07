package com.ly.cloud.controller.Interface;

import com.alibaba.fastjson.JSON;
import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.Interface.QualificationsDTO;
import com.ly.cloud.dto.base.ZgxZgDTO;
import com.ly.cloud.service.base.ZgdyService;
import com.ly.cloud.service.base.ZglsmdService;
import com.ly.cloud.vo.base.ZgdyVO;
import com.ly.cloud.vo.base.ZglsmdVO;
import com.ly.cloud.vo.base.ZgxZgVO;
import com.ly.zhxg.utils.NHBeanUtils;
import com.ly.zhxg.utils.NHRedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @ClassName: ZGUtilsController
 * @Description: 综合学工-资格鉴定-资格评定相关服务API
 * @author: moyongpeng
 * @date: 2019-02-19 17:37:07
 * @Copyright: 2018 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */
@RestController
@RequestMapping("/zgjd")
@Api("综合学工-资格鉴定-资格评定相关服务API")
public class ZGUtilsController {
	private static Logger logger = LoggerFactory.getLogger(ZGUtilsController.class);
	@Autowired
	private ZglsmdService zglsmdService;
	@Autowired
	private ZgdyService zgdyService;

	@ApiOperation(value = "根据资格对象获取批次ID", notes = "根据资格对象获取批次ID", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "dto", dataType = "QualificationsDTO", required = true, value = "资格对象"),
			@ApiImplicitParam(paramType = "query", name = "type", dataType = "String", defaultValue = "match", value = "返回名单类型(all:返回所有名单,match:只返回满足条件名单,mismatch:只返回不满足条件名单)") })
	@RequestMapping(value = "/qualifications/getBatchID", method = RequestMethod.POST)
	public JsonResult getQualificationsBatchID(@RequestBody final QualificationsDTO dto,@RequestParam(value = "type",defaultValue = "match") final String type) {
		try {
			final String pcid = UUID.randomUUID().toString();
//	注释旧版实现方式，切换为新版的实现方式
//			new Thread(new Runnable(){
//				@Override
//				public void run() {
//					zglsmdService.qualificationsGetBatchID(dto,pcid);
//				}
//			}).start();
			new Thread(new Runnable(){
				@Override
				public void run() {
					zglsmdService.qualificationsGetBatchID_V2(dto,pcid,type,true);
				}
			}).start();
			return JsonResult.success(pcid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "根据批次ID获取临时名单", notes = "根据批次ID获取临时名单", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pcid", dataType = "String", required = true, value = "批次ID") })
	@RequestMapping(value = "/qualifications/getBatchlist", method = RequestMethod.GET)
	public JsonResult getQualificationsBatchlist(@RequestParam String pcid) {
		try {
			List<ZglsmdVO> vo = (List<ZglsmdVO>) NHRedisUtils.getRedisResult(pcid);
			return JsonResult.success(vo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
}
