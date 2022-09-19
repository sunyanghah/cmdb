package com.uino.cmdb.controller;

import com.uino.cmdb.config.page.BaseOutPageDto;
import com.uino.cmdb.config.platform.ApiResult;
import com.uino.cmdb.dto.*;
import com.uino.cmdb.service.CiDataService;
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
@Api(tags = "CI数据")
@RestController
@RequestMapping("/ci/data")
public class CiDataController {

    @Resource
    private CiDataService ciDataService;

    /**
     * 新增
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.platform.ApiResult
     * @date 2021/10/29 13:48
     */
    @ApiOperation("新增")
    @PostMapping
    public ApiResult add(@Validated @RequestBody InCiDataAddDto dto) throws Exception{
        String dataId = ciDataService.add(dto);
        return ApiResult.success(dataId);
    }

    /**
     * 修改
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.platform.ApiResult
     * @date 2021/10/29 13:49
     */
    @ApiOperation("修改")
    @PutMapping
    public ApiResult edit(@Validated @RequestBody InCiDataEditDto dto) throws Exception{
        ciDataService.edit(dto);
        return ApiResult.success();
    }

    /**
     * 删除
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.platform.ApiResult
     * @date 2021/10/29 13:49
     */
    @ApiOperation("删除")
    @DeleteMapping
    public ApiResult delete(@Validated @RequestBody InCiDataDeleteDto dto){
        ciDataService.delete(dto);
        return ApiResult.success();
    }

    /**
     * 根据ID获取详情
     * @author sunYang
     * @param dataId
     * @return com.uino.xxv.common.config.platform.ApiResult<com.uino.xxv.manager.dto.ci.OutCiDataInfoDto>
     * @date 2021/11/1 15:32
     */
    @ApiOperation("根据ID获取详情")
    @GetMapping("/info/{dataId}")
    public ApiResult<OutCiDataInfoDto> info(@PathVariable("dataId")String dataId){
        OutCiDataInfoDto infoDto = ciDataService.info(dataId);
        return ApiResult.success(infoDto);
    }
    /**
     * 分页查询
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.platform.ApiResult<com.uino.xxv.common.config.page.BaseOutPageDto<com.uino.xxv.manager.dto.ci.OutCiDataPageDto>>
     * @date 2021/10/29 13:49
     */
    @ApiOperation("分页查询")
    @PostMapping("/page")
    public ApiResult<BaseOutPageDto<OutCiDataPageDto>> page(@Validated @RequestBody InCiDataPageDto dto){
        BaseOutPageDto<OutCiDataPageDto> pageResult = ciDataService.page(dto);
        return ApiResult.success(pageResult);
    }

    /**
     * 条件模糊查询，最多返回20条
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.platform.ApiResult<java.util.List<com.uino.xxv.manager.dto.ci.OutCiDataSearchDto>>
     * @date 2021/11/1 15:31
     */
    @ApiOperation("/根据条件查询,最多返回20条")
    @PostMapping("/search")
    public ApiResult<List<OutCiDataSearchDto>> search(@Validated @RequestBody InCiDataSearchDto dto){
        List<OutCiDataSearchDto> resultList = ciDataService.search(dto);
        return ApiResult.success(resultList);
    }

}
