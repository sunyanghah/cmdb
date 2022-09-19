package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author sunYang
 * @date 2021/10/29 10:29
 */
@Data
public class OutCiDataSearchDto {

    @ApiModelProperty("数据主键ID")
    private String id;

    @ApiModelProperty("数据分类ID")
    private String classId;

    @ApiModelProperty("数据")
    private Map<String,Object> data;

}
