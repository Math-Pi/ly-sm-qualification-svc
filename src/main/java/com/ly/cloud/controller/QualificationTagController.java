package com.ly.cloud.controller;

import com.ly.cloud.common.json.JsonResult;
import com.ly.cloud.dto.base.ZgxBqDTO;
import com.ly.cloud.dto.tag.QualificationTagDTO;
import com.ly.cloud.service.base.ZgxBqService;
import com.ly.cloud.service.base.ZgxbqGxService;
import com.ly.cloud.vo.base.ZgxBqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@Api("资格项标签相关api")
public class QualificationTagController {
    private static Logger logger = LoggerFactory.getLogger(QualificationTagController.class);

    @Autowired
    private ZgxBqService tagService;
    @Autowired
    private ZgxbqGxService qualTagService;

    @ApiOperation(value = "添加标签" , notes = "添加标签" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "标签参数", required = true, dataType = "ZgxBqDTO")
    })
    @PostMapping("/add")
    public JsonResult add(@RequestBody ZgxBqDTO dto) {
        try {
            int rs = tagService.insert(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询标签" , notes = "根据主键查询标签" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", value = "标签主键", required = true, dataType = "String")
    })
    @GetMapping("/{pkid}/get")
    public JsonResult get(@PathVariable("pkid") String pkid ) {
        try {
            ZgxBqVO zgxBqVO = tagService.get(pkid);
            return JsonResult.success(zgxBqVO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改标签" , notes = "修改标签" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "标签参数", required = true, dataType = "ZgxBqDTO")
    })
    @PostMapping("/update")
    public JsonResult update(@RequestBody ZgxBqDTO dto) {
        try {
            int rs = tagService.update(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除标签" , notes = "删除标签" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "ids", value = "主键id列表", required = true, dataType = "List")
    })
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody List<String> ids) {
        try {
            int rs = tagService.deleteByMulti(ids);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "资格项添加标签" , notes = "资格项添加标签" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "标签标志，资格项列表", required = true, dataType = "QualificationTagDTO")
    })
    @PostMapping("/qualification/add")
    public JsonResult qualificationTagAdd(@RequestBody QualificationTagDTO dto) {
        try {
            int rs = qualTagService.qualificationTagAdd(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }
    @ApiOperation(value = "清除资格项标签" , notes = "清除资格项标签" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "ids", value = "资格项id列表", required = true, dataType = "List")
    })
    @PostMapping("/qualification/delete")
    public JsonResult qualificationTagDelete(@RequestBody List<String> ids) {
        try {
            int rs = qualTagService.qualificationTagDelete(ids);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return JsonResult.failure(e.getMessage());
        }
    }

}
