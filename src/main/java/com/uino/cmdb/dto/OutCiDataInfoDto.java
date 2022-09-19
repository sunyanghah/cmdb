package com.uino.cmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uino.cmdb.config.util.AnnotationTransUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author sunYang
 * @date 2021/10/29 10:29
 */
@Data
public class OutCiDataInfoDto {

    @ApiModelProperty("数据主键ID")
    private String id;

    @ApiModelProperty("数据分类ID")
    private String classId;

    @ApiModelProperty("数据")
    private Map<String,Object> data;

    @ApiModelProperty("创建人")
//    @JsonSerialize(using = AnnotationTransUtils.class)
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("最后修改人")
    private String updateBy;

    @ApiModelProperty("最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}
