package com.uino.cmdb.config.page;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 分页入参抽象类.
 * @author sunYang
 * @date 2021/9/16 16:32
 */
@Data
public class BaseInPageDto {

    /**
     * 第一页从1开始
     */
    @NotNull(message = "页码数不能为空")
    protected Integer pageNumber;

    @NotNull(message = "每页条数不能为空")
    protected Integer pageSize;

    public int getDataIndex(){
        return (pageNumber-1) * pageSize;
    }
}
