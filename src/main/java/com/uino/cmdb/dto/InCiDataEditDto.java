package com.uino.cmdb.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author sunYang
 * @date 2021/10/28 10:04
 */
@Data
public class InCiDataEditDto {

    @NotBlank(message = "CI分类ID不能为空")
    private String classId;

    private String id;

    private Map<String,Object> data;

}
