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
 * 数据
 * @author sunYang
 * @date 2021/10/26 17:29
 */
@Data
@TableName("ci_data")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiData {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /**
     * 分类ID
     */
    private String classId;

    /**
     * 数据JSON
     */
    private String data;

    /**
     * 描述
     */
    private String description;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String extend1;

    private String extend2;

    private String extend3;

}
