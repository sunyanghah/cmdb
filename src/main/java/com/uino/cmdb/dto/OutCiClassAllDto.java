package com.uino.cmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uino.cmdb.config.util.AnnotationTransUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sunYang
 * @date 2021/10/27 11:04
 */
@Data
public class OutCiClassAllDto {

    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
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

//    @JsonSerialize(using = AnnotationTransUtils.class)
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

}
