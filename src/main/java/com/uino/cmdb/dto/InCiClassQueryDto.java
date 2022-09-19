package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sunYang
 * @date 2022/2/25 16:04
 */
@Data
public class InCiClassQueryDto {

    @ApiModelProperty("分类名称")
    private String className;

}
