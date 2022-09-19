package com.uino.cmdb.controller;

import com.uino.cmdb.config.platform.ApiResult;
import com.uino.cmdb.dto.*;
import com.uino.cmdb.service.CiFieldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/26 18:12
 */
@Api(tags = "CI字段")
@RestController
@RequestMapping("/ci/field")
public class CiFieldController {

    @Resource
    private CiFieldService ciFieldService;

    @ApiOperation("保存")
    @PostMapping("/save")
    public ApiResult save(@Validated @RequestBody InCiFieldSaveDto dto){
        ciFieldService.save(dto);
        return ApiResult.success();
    }

    @ApiOperation("根据CiClassId查询全部")
    @GetMapping("/all/{classId}")
    public ApiResult<List<OutCiFieldAllDto>> queryAll(@PathVariable("classId")String classId){
        List<OutCiFieldAllDto> dtoList = ciFieldService.queryAll(classId);
        return ApiResult.success(dtoList);
    }

}
