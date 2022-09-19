package com.uino.cmdb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ci分类(表)
 * @author sunYang
 * @date 2021/10/26 17:29
 */
@Data
@TableName("ci_class")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiClass {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /**
     * 分类名称
     */
    private String className;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Double sort;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

}
