package com.ly.cloud.controller.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.common.mybatisplus.plugins.PageInfo;
import com.ly.zhxg.component.nhDynameForm.NHDynameFormUtils;
import com.ly.zhxg.component.nhImportExcel.NHExcelImpFactory;
import com.ly.zhxg.component.nhSelect.NHSelectUtils;
import com.ly.zhxg.component.nhSelector.NHSelectorUtils;
import com.ly.zhxg.component.nhTable.NHTableUtils;
import com.ly.zhxg.component.nhTree.NHTreeUtils;
import com.ly.zhxg.dto.nhDynameForm.DynameAllSaveDTO;
import com.ly.zhxg.dto.nhDynameForm.DynameOneOnManyDTO;
import com.ly.zhxg.dto.nhDynameForm.DynameOneSaveDTO;
import com.ly.zhxg.dto.nhSelector.SelectorParamsDTO;
import com.ly.zhxg.dto.nhTable.TableParamsDTO;
import com.ly.zhxg.dto.nhTree.TreeParamsDTO;
import com.ly.zhxg.dto.redis.RedisResultDTO;
import com.ly.cloud.mapper.common.TableDTOMapper;
import com.ly.zhxg.utils.NHIOUtils;
import com.ly.zhxg.utils.NHInputStreamUtils;
import com.ly.zhxg.utils.NHRedisUtils;
import com.ly.zhxg.utils.configAnalysis.NHConfigUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Class Name: DemoController Description: Demo事例控制层
 * 
 * @author: yizhiqiang
 * @mail: yizhiqiang@ly-sky.com
 * @date: 2017年6月27日 10:58:56
 * @version: 1.0
 *
 */
@RestController
@RequestMapping("/proData")
@Api("Pro项目数据查询相关服务API")
public class ProgrammerController {

	private static Logger logger = LoggerFactory.getLogger(ProgrammerController.class);
	
	@Autowired
	private TableDTOMapper mapper;
	
	/********************************** 参数获取 接口  ***********************************/
	
	/**
	 * 获取所有的配置参数
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "应用信息", notes = "获取所有的配置参数")
    @RequestMapping(value = "/queryAllConfig", method = RequestMethod.GET)
    public JsonResult queryAllConfig() {
    	try {
    		
    		return JsonResult.success(NHConfigUtils.queryAllConfig(mapper));
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }

	/********************************** Table列表 ***********************************/
	/**
	 * Table列表查询数据
	 * @param dto
	 * @return
	 */
	@ApiOperation(value = "应用信息", notes = "根据检索分页查询应用信息-参数是实体对象")
    @RequestMapping(value = "/gridList", method = RequestMethod.POST)
	public JsonResult queryPage(@RequestBody TableParamsDTO dto,@RequestHeader(name = "loginUserId") String loginUserId) {
    	try {
    		PageInfo<Map<String,Object>> pageInfo = NHTableUtils.queryPage(dto,loginUserId, mapper);
    		return JsonResult.success(pageInfo);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
	
	@ApiOperation(value = "应用信息", notes = "缓存此次导出需要使用的需要的数据")
	@RequestMapping(value = "/gridList/excel/params", method = RequestMethod.POST)
	public JsonResult downloadIs(@RequestBody TableParamsDTO dto) {
		try {
			String uuid=NHTableUtils.cacheExportData(dto,mapper);
    		return JsonResult.success(uuid);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}

	}
	
	@ApiOperation(value = "应用信息", notes = "根据检索分页导出应用信息-参数是实体对象")
	@RequestMapping(value = "/gridList/excel/export", method = RequestMethod.GET)
	public void downloadIs(@RequestParam("uuid") String uuid,HttpServletRequest request, HttpServletResponse response)  {
		try {
			NHTableUtils.exportExcel(request,response,uuid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
	}
    
    /**********************************下拉框***********************************/
    /**
     * 级联下拉框查询数据
     * @return
     */
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "level", dataType = "Integer", required = true, value = "查询的数据的层级"),
        @ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
        @ApiImplicitParam(paramType = "query", name = "cascaderValue", dataType = "String", required = false, value = "用来过滤的上级数据标志")
    })
    @RequestMapping(value = "/selectCascaderList", method = RequestMethod.GET)
    public JsonResult getCascaderList(@RequestParam("level") Integer level,@RequestParam("sign") String sign
    		,@RequestParam(value = "cascaderValue",required = false) String cascaderValue) {
    	try {
    		List<Map<String,Object>> list=NHSelectUtils.selectCascaderList(sign, level, cascaderValue, mapper);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
    
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
        @ApiImplicitParam(paramType = "query", name = "value", dataType = "String", required = false, value = "最后一级下拉框的值")
    })
    @RequestMapping(value = "/selectValues", method = RequestMethod.GET)
    public JsonResult getCascaderList(@RequestParam("sign") String sign,@RequestParam(value = "value") String value) {
    	try {
    		List<String> list=NHSelectUtils.selectValues(sign, value, mapper);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
    
    /**
     * 普通下拉框和多选下拉框查询数据
     * @param params
     * @return
     */ 
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "level", dataType = "Integer", required = true, value = "查询的数据的层级"),
        @ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
        @ApiImplicitParam(paramType = "query", name = "cascaderValue", dataType = "String", required = false, value = "用来过滤的上级数据标志")
    })
    @RequestMapping(value = "/selectDataList", method = RequestMethod.GET)
    public JsonResult selectDataList(@RequestParam("sign") String sign) {
    	try {
    		List<Map<String,Object>> list=NHSelectUtils.selectDataList(sign,mapper);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage()); 
    	}
    }
    
    /**********************************树Tree***********************************/
    @RequestMapping(value = "/getTreeList", method = RequestMethod.POST)
    public JsonResult getTreeList(@RequestBody TreeParamsDTO dto) {
    	try {
    		List<Map<String,Object>> data = NHTreeUtils.selectTreeList(dto, mapper);
    		return JsonResult.success(data);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }


	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "sign", dataType = "String", required = true, value = "查询数据的标志"),
			@ApiImplicitParam(paramType = "query", name = "values", dataType = "String", required = true, value = "用来过滤的上级数据标志") })
	@RequestMapping(value = "/getSelectName", method = RequestMethod.GET)
	public JsonResult getSelectName(@RequestParam("sign") String sign, @RequestParam("values") String values) {
		try {
			String names = NHSelectUtils.getSelectName(sign, values, mapper);
			return JsonResult.success(names);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	/********************************** Excel文件上传操作  ***********************************/
	
	@ApiOperation(value = "下载导入控件模板", notes = "下载导入控件模板", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "string", name = "sign", dataType = "String", required = true, value = "标志") 
	})
	@RequestMapping(value = "/getTemplate", method = RequestMethod.GET)
	public void getFileByName(@RequestParam("sign") String sign,@RequestParam("fileName") String fileName
			,HttpServletRequest request, HttpServletResponse response) {
		Resource resource = null;
		try {
			fileName=URLDecoder.decode(fileName,"utf-8")+".xlsx";
			resource = NHExcelImpFactory.getResourceByName(sign);
			NHIOUtils.copy(request, response, fileName, resource);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
	}
	
	/********************************** Excel文件上传操作(只在xtgl服务中存在)  ***********************************/
	@ApiOperation(value = "下载导入的错误信息", notes = "下载导入的错误信息", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "string", name = "uuid", dataType = "String", required = true, value = "文件名") })
	@RequestMapping(value = "/getExcelErrorFile", method = RequestMethod.GET)
	public void getExcelErrorFile(@RequestParam("uuid") String uuid
			,HttpServletRequest request, HttpServletResponse response) {
		InputStream is = null;
		try {
			RedisResultDTO po = NHRedisUtils.getRedisUploadResult(uuid);
			is = NHInputStreamUtils.byteToInputStream(po.getIs());
			String fileName = po.getName();
			NHIOUtils.copy(request, response, fileName, is);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		} finally {
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("流关闭失败！");
				}
			}
		}
	}
	
	@ApiOperation(value = "获取导入的状态信息", notes = "获取导入的状态信息", httpMethod = "GET")
	@RequestMapping(value = "/getProgressStatus" , method = RequestMethod.GET)
	public JsonResult getProgressStatus(@RequestParam("uuid") String uuid) {
		try {
			Object obj = NHRedisUtils.getRedisResult("process"+uuid);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	/********************************** 文件上传到Redis缓存中  ***********************************/
	@ApiOperation(value = "上传文件到Redis缓存中", notes = "上传文件到Redis缓存中", httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "file", dataType = "MultipartFile", required = true, value = "文件") })
	@RequestMapping(value = "/uploadRedis",consumes = "multipart/form-data", method = RequestMethod.POST)
	public JsonResult uploadRedis( @RequestParam("file") MultipartFile file , @RequestParam("redisData") Integer redisData) {
		try {
			String uuid=UUID.randomUUID().toString();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("is", file.getBytes());
			map.put("name", file.getOriginalFilename());
			map.put("size", file.getSize());
			map.put("uuid", uuid);
			NHRedisUtils.addRedis(uuid, redisData, map);
			return JsonResult.success(uuid);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	/********************************** 从Redis中下载图片(仅用于下载图片) ***********************************/
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "从Redis中下载图片(仅用于下载图片)", notes = "从Redis中下载图片(仅用于下载图片)", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "string", name = "uuid", dataType = "String", required = true, value = "redis中缓存图片标志") })
	@RequestMapping(value = "/downloadRedisCacheImage", method = RequestMethod.GET)
	public void downloadRedisCacheImage(@RequestParam("uuid") String uuid, HttpServletResponse response) {
		try {
			Object object = NHRedisUtils.getRedisResult(uuid);
			if (null != object) {
				Map<String, Object> cacheImageMap = (Map<String, Object>) object;
				if (null != cacheImageMap) {
					byte[] cacheImageBytes = (byte[]) cacheImageMap.get("is");
					response.getOutputStream().write(cacheImageBytes);
					//将缓冲信息输出到页面
					response.flushBuffer();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/********************************** 选择器接口配置  ***********************************/
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "dto", dataType = "SelectorParamsDTO", required = false, value = "选择器接口参数")
    })
    @RequestMapping(value = "/selector/tree/list", method = RequestMethod.POST)
    public JsonResult selectSelectorTreeDate(@RequestBody SelectorParamsDTO dto) {
    	try {
    		List<Map<String,Object>> list=NHSelectorUtils.selectTreeDataList(dto,mapper);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage()); 
    	}
    }
    
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "dto", dataType = "SelectorParamsDTO", required = false, value = "选择器接口参数")
    })
    @RequestMapping(value = "/selector/table/list", method = RequestMethod.POST)
    public JsonResult selectSelectorTableDate(@RequestBody SelectorParamsDTO dto,@RequestHeader(value="loginUserId") String loginUserId) {
    	try {
    		PageInfo<Map<String,Object>> list=NHSelectorUtils.selectTableDataList(dto,loginUserId,mapper);
    		return JsonResult.success(list);
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage()); 
    	}
    }
    /********************************** 动态表单保存接口配置  ***********************************/
    /**
	 * 
	 * @Title: query 
	 * @Description: 数据保存接口
	 * @author: moyomngpeng
	 * @date: 2019-03-27 16:57
	 * @param: 
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@ApiOperation(value = "根据字段对象，保存一对一的字段信息" , notes = "根据字段对象，保存一对一的字段信息" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "dto", value = "字段保存对象",required = true, dataType = "DynameOneSaveDTO")    	
    })
    @RequestMapping(value = "/saveOneOnOneInfo", method = RequestMethod.POST)
	public JsonResult saveOneOnOneInfo(@RequestBody DynameOneSaveDTO dto,@RequestHeader(value="loginUserId") String loginUserId) {
		try {
			Integer rs=NHDynameFormUtils.saveOneOnOneInfo(dto,mapper,loginUserId);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: query 
	 * @Description: 数据保存接口
	 * @author: moyomngpeng
	 * @date: 2019-03-27 16:57
	 * @param: 
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@ApiOperation(value = "根据字段对象，保存一对多的字段信息" , notes = "根据字段对象，保存一对多的字段信息" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "dto", value = "字段保存对象",required = true, dataType = "DynameOneOnManyDTO")    	
    })
    @RequestMapping(value = "/saveOneOnManyInfo", method = RequestMethod.POST)
	public JsonResult saveOneOnManyInfo(@RequestBody DynameOneOnManyDTO dto,@RequestHeader(value="loginUserId") String loginUserId) {
		try {
			Integer rs=NHDynameFormUtils.saveOneOnManyInfo(dto,mapper,loginUserId);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: query 
	 * @Description: 数据保存接口
	 * @author: moyomngpeng
	 * @date: 2019-03-27 16:57
	 * @param: 
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@ApiOperation(value = "统一保存一对一和一对多的字段信息" , notes = "统一保存一对一和一对多的字段信息" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "query", name = "dto", value = "字段保存对象",required = true, dataType = "DynameAllSaveDTO")    	
    })
    @RequestMapping(value = "/saveAllDynameInfo", method = RequestMethod.POST)
	public JsonResult saveAllDynameInfo(@RequestBody DynameAllSaveDTO dto,@RequestHeader(value="loginUserId") String loginUserId) {
		try {
			Integer rs=NHDynameFormUtils.saveAllDynameInfo(dto,mapper,loginUserId);
			return JsonResult.success(rs);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return JsonResult.failure(e.getMessage());
		}
	}
}
