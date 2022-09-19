package com.uino.cmdb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 字段表
 * @author sunYang
 * @date 2021/10/26 17:29
 */
@Data
@TableName("ci_field")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiField {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /**
     * 所属ci_class 的id
     */
    @TableField("class_id")
    private String classId;

    /**
     * 字段名称
     */
    @TableField("field_name")
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
    @TableField("field_type")
    private String fieldType;

    /**
     * 是否主键  1是  0否
     */
    @TableField("primary_flag")
    private String primaryFlag;

    /**
     * 是否必填  1是  0否
     */
    @TableField("require_flag")
    private String requireFlag;

    /**
     * 字段长度
     */
    @TableField("field_length")
    private Integer fieldLength;

    /**
     * 小数位长度
     */
    @TableField("decimal_length")
    private Integer decimalLength;

    /**
     * 枚举选项值
     */
    @TableField("enum_option")
    private String enumOption;

    /**
     * 父级classId
     */
    @TableField("parent_class")
    private String parentClass;

    /**
     * 父级字段编码
     */
    @TableField("parent_field")
    private String parentField;


//    /**
//     * 父级类型
//     */
//    @TableField("parent_type")
//    private String parentType;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

    /**
     * 排序
     */
    @TableField("sort")
    private Double sort;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("extend1")
    private String extend1;

    @TableField("extend2")
    private String extend2;

    @TableField("extend3")
    private String extend3;

}
