package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author sunYang
 * @date 2021/10/27 9:47
 */
@Data
public class InCiClassDeleteDto {

    @ApiModelProperty("主键ID")
    @NotBlank(message = "主键ID不能为空")
    private String id;

}
