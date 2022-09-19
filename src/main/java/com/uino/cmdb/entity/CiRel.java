package com.uino.cmdb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ci数据关系
 * @author sunYang
 * @date 2021/10/26 17:29
 */
@Data
@TableName("ci_rel")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CiRel {

    @TableId(value = "id",type = IdType.INPUT)
    private String id;

    /**
     * 源数据分类ID
     */
    private String sourceClassId;

    /**
     * 源数据ID
     */
    private String sourceId;

    /**
     * 目标数据分类ID
     */
    private String targetClassId;

    /**
     * 目标数据ID
     */
    private String targetId;

    private String extend1;

    private String extend2;

    private String extend3;

}
