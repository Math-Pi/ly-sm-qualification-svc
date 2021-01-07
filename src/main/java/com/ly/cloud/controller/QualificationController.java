package com.ly.cloud.controller;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.zhxg.component.nhImportExcel.NHExcelImpFactory;
import com.ly.zhxg.utils.NHIOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.service.base.ZgxglGlgzService;
import com.ly.cloud.service.base.ZgxglService;
import com.ly.cloud.dto.base.ZgxglDTO;
import com.ly.cloud.dto.base.ZgxglGlgzDTO;
import com.ly.cloud.vo.base.ZgxglGlgzVO;
import com.ly.cloud.vo.base.ZgxglVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:  QualificationController
 * @Description: 综合学工-资格鉴定-资格项管理相关服务API
 * @author: Administrator
 * @date: 2019-03-07 09:52:45
 * @Copyright: 2018 LIANYI TECHNOLOGY CO.,LTD. All Rights Reserved. 联奕科技有限公司
 */
@RestController
@RequestMapping("/qualification")
@Api("综合学工-资格鉴定-资格项管理相关服务API")
public class QualificationController {

    private static Logger logger = LoggerFactory.getLogger(QualificationController.class);
    @Autowired
    private ZgxglService zgxglService;
    @Autowired
    private ZgxglGlgzService zgxglGlgzService;
    
    /**
     * @author: Administrator
     * @date: 2019-03-07 09:52:45
     */
    @ApiOperation(value = "添加资格项管理" , notes = "通过录入的参数，添加一条资格项管理" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格项管理", required = true, dataType = "ZgxglDTO")
    })
    @RequestMapping(value = "/insertQual", method = RequestMethod.POST)
    public JsonResult insertQual(@RequestBody ZgxglDTO dto) {
        try {
            Integer rs = zgxglService.insert(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
  
  	/**
     * @author: Administrator
     * @date: 2019-03-07 09:52:45
     */
    @ApiOperation(value = "更新资格项管理" , notes = "通过录入的参数，修改一条资格项管理" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格项管理", required = true, dataType = "ZgxglDTO")
    })
    @RequestMapping(value = "/updateQual", method = RequestMethod.POST)
    public JsonResult updateQual(@RequestBody ZgxglDTO dto) {
        try {
            int rs = zgxglService.update(dto); 
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    /**
     * @author: Administrator
     * @date: 2019-03-07 09:52:45
     */
    @ApiOperation(value = "批量删除资格项管理" , notes = "通过主键，批量删除多条资格项管理" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键") 
    })
    @RequestMapping(value = "/deleteByMultiQual", method = RequestMethod.POST)
    public JsonResult deleteByMultiQual(@RequestBody List<String> pkids) {
        try {
            int rs = zgxglService.deleteByMulti(pkids);
            return JsonResult.success(rs); 
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.success("forceDelete");
        }
    }
    
    
    @ApiOperation(value = "强制批量删除资格项管理" , notes = "通过主键，批量删除多条资格项管理" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键") 
    })
    @RequestMapping(value = "/forceDeleteByMultiQual", method = RequestMethod.POST)
    public JsonResult forceDeleteByMultiQual(@RequestBody List<String> pkids) {
    	try {
    		int rs = zgxglService.forceDeleteByMulti(pkids);
    		return JsonResult.success(rs); 
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
    		return JsonResult.failure(e.getMessage());
    	}
    }
    
    /**
     * @author: Administrator
     * @date: 2019-03-07 09:52:45
     */
    @ApiOperation(value = "查询单条资格项管理" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/getPkid", method = RequestMethod.GET)
    public JsonResult getPkid(@PathVariable String pkid) {
        try {
        	ZgxglVO vo = zgxglService.get(pkid);
            return JsonResult.success(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    
    @ApiOperation(value = "查询后台服务id" , notes = "根据名称查后台服务id" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "ssfw", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{ssfw}/getServiceid", method = RequestMethod.GET)
	public JsonResult getServiceid(@PathVariable(value="ssfw",required=false) String ssfw) {
		try {
			Object obj = zgxglService.getHtid(ssfw);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条资格项管理" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{pkid}/getInitData", method = RequestMethod.GET)
	public JsonResult get1(@PathVariable String pkid) {
		try {
			Object obj = zgxglService.get(pkid);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value = "查询所属的服务" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/getServices", method = RequestMethod.GET)
	public JsonResult getServices() {
		try {
			Object obj = zgxglService.getSsfw();
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条资格项管理的所有过滤规则信息" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{pkid}/getQual", method = RequestMethod.GET)
	public JsonResult getQual(@PathVariable String pkid) {
		try {
			Object obj = zgxglService.getQual(pkid);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据所属服务查询资格视图" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{pkid}/getQualView", method = RequestMethod.GET)
	public JsonResult getQualView(@PathVariable String pkid) {
		try {
			Object obj = zgxglService.getZgst(pkid);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value = "根据资格视图查询字段" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{pkid}/getQualField", method = RequestMethod.GET)
	public JsonResult getQualField(@PathVariable(value="pkid",required=false) String pkid) {
		try {
			Object obj = zgxglService.getZgtjzd(pkid);
			return JsonResult.success(obj);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "查询单条资格项管理的所有过滤规则信息" , notes = "根据id查询查询单条资格项管理" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/{pkid}/getList", method = RequestMethod.GET)
	public JsonResult getList(@PathVariable String pkid) {
		try {
			List<List<Map<String,String>>> arr=zgxglService.getListListMap(pkid);
			return JsonResult.success(arr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "修改保存方法" , notes = "保存" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/updatePreservation", method = RequestMethod.POST)
	public JsonResult updatePreservation(@RequestBody List<Object> allData) {
		try {
			String result=zgxglService.updatePreservation(allData);
			return JsonResult.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value = "新增保存方法" , notes = "保存" , httpMethod = "GET")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
	})
	@RequestMapping(value = "/insertPreservation", method = RequestMethod.POST)
	public JsonResult insertPreservation(@RequestBody List<Object> allData) {
		try {
			String result=zgxglService.insertPreservation(allData);
			return JsonResult.success(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JsonResult.failure(e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "查询多条资格项管理过滤规则" , notes = "根据资格项id查询多条资格项管理过滤规则" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/getRuleid", method = RequestMethod.GET)
    public JsonResult getRuleid(@PathVariable String pkid) {
        try {
        	Object obj = zgxglGlgzService.getPkid(pkid);
            return JsonResult.success(obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
	
	@ApiOperation(value = "加入互斥id" , notes = "通过录入的参数，修改一条资格项管理过滤规则的互斥id" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "资格项管理过滤规则", required = true, dataType = "ZgxglGlgzDTO")
    })
    @RequestMapping(value = "/updateHc", method = RequestMethod.POST)
    public JsonResult updateHc(@RequestBody List<String> list) {
        try {
        	String hcglgzid = list.get(0);
        	String glgzid = list.get(1);
            int rs = zgxglGlgzService.updateHc(hcglgzid,glgzid);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
	
	@ApiOperation(value = "查询单条过滤规则" , notes = "根据id查询查询单条过滤规则" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "String", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/get", method = RequestMethod.GET)
    public JsonResult get(@PathVariable String pkid) {
        try {
        	ZgxglGlgzVO vo = zgxglGlgzService.get(pkid);
            return JsonResult.success(vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
	
	@ApiOperation(value = "更新过滤规则" , notes = "通过录入的参数，修改一条过滤规则" , httpMethod = "POST")
    @ApiImplicitParams({
    	@ApiImplicitParam(paramType = "body", name = "dto", value = "过滤规则", required = true, dataType = "ZgxglGlgzDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ZgxglGlgzDTO dto) {
        try {
            int rs = zgxglGlgzService.update(dto); 
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

	@ApiOperation(value = "下载导入控件模板", notes = "下载导入控件模板", httpMethod = "GET")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "string", name = "sign", dataType = "String", required = true, value = "标志")
	})
	@RequestMapping(value = "/getTemplate", method = RequestMethod.GET)
	public void getFileByName(@RequestParam("xxdm") String xxdm, @RequestParam("ssfw") String ssfw
			, HttpServletRequest request, HttpServletResponse response) {
		try {
			zgxglGlgzService.createSql(xxdm,ssfw,request,response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
	}
	
}
