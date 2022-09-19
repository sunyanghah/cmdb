package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunYang
 * @date 2021/10/27 9:47
 */
@Data
public class InCiClassAddDto {

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String className;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Double sort;

}
