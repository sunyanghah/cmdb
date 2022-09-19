package com.uino.cmdb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uino.cmdb.config.util.AnnotationTransUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sunYang
 * @date 2021/10/27 11:38
 */
@Data
public class OutCiFieldAllDto {

    @ApiModelProperty("主键")
    private String id;

    /**
     * 所属ci_class 的id
     */
    @ApiModelProperty("CI分类id")
    private String classId;

    /**
     * 字段名称
     */
    @ApiModelProperty("字段名称")
    private String fieldName;

    /**
     * 字段类型
     * 1：字符串
     * 2：整数
     * 3：小数
     * 4：日期
     * 5：时间
     * 6：日期时间
     * 7：选项
     * 8：父级
     */
    @ApiModelProperty("字段类型")
    private String fieldType;

    @ApiModelProperty("字段类型,描述")
    private String fieldTypeText;

    /**
     * 是否主键  1是  0否
     */
    @ApiModelProperty("是否主键 1是 0否")
    private String primaryFlag;

    @ApiModelProperty("是否主键,描述")
    private String primaryFlagText;

    /**
     * 是否必填  1是  0否
     */
    @ApiModelProperty("是否必填 1是 0否")
    private String requireFlag;

    @ApiModelProperty("是否必填,描述")
    private String requireFlagText;

    /**
     * 字段长度
     */
    @ApiModelProperty("字段长度")
    private Integer fieldLength;

    /**
     * 小数位长度
     */
    @ApiModelProperty("小数位长度")
    private Integer decimalLength;

    /**
     * 枚举选项值
     */
    @ApiModelProperty("枚举选项值")
    private String enumOption;

    /**
     * 父级classId
     */
    @ApiModelProperty("父级ClassId")
    private String parentClass;

    /**
     * 父级class名称
     */
    @ApiModelProperty("父级class名称")
    private String parentClassName;

    @ApiModelProperty(value = "父级字段编码",hidden = true)
    private String parentField;

    /**
     * 父级类型 0：一对多  1：多对多
     */
//    @ApiModelProperty("父级类型 0：一对多  1：多对多")
//    private String parentType;

    /**
     * 父级类型描述
     */
//    @ApiModelProperty("父级类型描述")
//    private String parentTypeText;

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
