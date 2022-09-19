package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sunYang
 * @date 2021/10/29 11:02
 */
@Data
public class CiDataConditionDto {

    @ApiModelProperty("查询字段code")
    private String field;

    @ApiModelProperty("条件 eq , like , ge  ,le")
    private String condition;

    @ApiModelProperty("查询值")
    private Object value;

}
