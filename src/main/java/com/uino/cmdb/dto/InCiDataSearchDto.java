package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunYang
 * @date 2021/10/29 10:29
 */
@Data
public class InCiDataSearchDto {

    @ApiModelProperty("数据分类ID")
    @NotBlank(message = "数据分类ID不能为空")
    private String classId;

    @ApiModelProperty("查询字符串")
    private String searchStr;

}
