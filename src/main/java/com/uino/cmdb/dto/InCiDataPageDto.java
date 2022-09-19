package com.uino.cmdb.dto;

import com.uino.cmdb.config.page.BaseInPageDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/29 10:29
 */
@Data
public class InCiDataPageDto extends BaseInPageDto {

    @NotBlank(message = "classId不能为空")
    @ApiModelProperty("classId")
    private String classId;

    @ApiModelProperty("全数据模糊查询")
    private String searchStr;

    @ApiModelProperty("查询条件，多个之间是and关系")
    private List<CiDataConditionDto> conditions;

}
