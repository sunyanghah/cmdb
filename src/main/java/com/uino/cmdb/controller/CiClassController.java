package com.uino.cmdb.controller;

import com.uino.cmdb.config.platform.ApiResult;
import com.uino.cmdb.dto.*;
import com.uino.cmdb.service.CiClassService;
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
@Api(tags = "CI分类")
@RestController
@RequestMapping("/ci/class")
public class CiClassController {

    @Resource
    private CiClassService ciClassService;

    @ApiOperation("新增")
    @PostMapping
    public ApiResult add(@Validated @RequestBody InCiClassAddDto dto) throws Exception{
        String classId = ciClassService.add(dto);
        return ApiResult.success(classId);
    }

    @ApiOperation("修改")
    @PutMapping
    public ApiResult edit(@Validated @RequestBody InCiClassEditDto dto) throws Exception{
        ciClassService.edit(dto);
        return ApiResult.success();
    }

    @ApiOperation("删除")
    @DeleteMapping
    public ApiResult delete(@Validated @RequestBody InCiClassDeleteDto dto){
        ciClassService.delete(dto);
        return ApiResult.success();
    }

    @ApiOperation("查询全部")
    @PostMapping("/list")
    public ApiResult<List<OutCiClassAllDto>> queryAll(@Validated @RequestBody InCiClassQueryDto dto){
        List<OutCiClassAllDto> resultList = ciClassService.queryAll(dto);
        return ApiResult.success(resultList);
    }

}
