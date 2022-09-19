package com.uino.cmdb.config.page;

import lombok.Data;

import java.util.List;

/**
 * 分页出参抽象类.
 * @author sunYang
 * @date 2021/9/16 16:32
 */
@Data
public final class BaseOutPageDto<T> {

    private Long total;

    private List<T> records;

    private Integer pageTotal;

    private Integer pageNumber;

    private Integer pageSize;

    public Long getPageTotal(){

        long left = total % pageSize;
        if (left == 0){
            return total/pageSize;
        }else{
            return total/pageSize+1;
        }

    }
}
