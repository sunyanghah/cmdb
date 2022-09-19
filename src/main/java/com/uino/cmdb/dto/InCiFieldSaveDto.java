package com.uino.cmdb.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author sunYang
 * @date 2022/9/16 15:06
 */
@Data
public class InCiFieldSaveDto {

    @ApiModelProperty("分类ID")
    @NotBlank(message = "分类ID不能为空")
    private String classId;

    @ApiModelProperty("字段集合")
    @NotEmpty(message = "字段不能为空")
    private List<FieldSaveDto> fieldList;

    @Data
    public static class FieldSaveDto {

        @ApiModelProperty("字段ID，新增一条字段时空着，修改时带着")
        private String id;

        /**
         * 字段名称
         */
        @ApiModelProperty("字段名称")
        @NotBlank(message = "字段名称不能为空")
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
        @NotBlank(message = "字段类型不能为空")
        private String fieldType;

        /**
         * 是否主键  1是  0否
         */
        @ApiModelProperty("是否主键  1是  0否")
        @NotBlank(message = "是否主键不能为空")
        @Pattern(regexp = "[0,1]",message = "是否主键不符合要求")
        private String primaryFlag;

        /**
         * 是否必填  1是  0否
         */
        @ApiModelProperty("是否必填 1是 0否")
        @Pattern(regexp = "[0,1]",message = "是否必填不符合要求")
        private String requireFlag;

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

}
