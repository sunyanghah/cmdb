package com.uino.cmdb.controller;

import com.uino.cmdb.config.enums.CiFieldTypeE;
import com.uino.cmdb.config.platform.ApiResult;
import com.uino.cmdb.dto.OutDictDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunYang
 * @date 2022/9/16 16:35
 */
@RestController
@RequestMapping("/dict")
public class SysDictController {

    @ApiOperation("获取字段类型")
    @GetMapping("/fieldType")
    public ApiResult<List<OutDictDto>> queryFieldType(){

        List<OutDictDto> outList = Arrays.stream(CiFieldTypeE.values())
                .map(type -> {
                    OutDictDto outDictDto = new OutDictDto();
                    outDictDto.setKey(type.getValue());
                    outDictDto.setValue(type.getName());
                    return outDictDto;
                })
                .collect(Collectors.toList());

        return ApiResult.success(outList);
    }

}
