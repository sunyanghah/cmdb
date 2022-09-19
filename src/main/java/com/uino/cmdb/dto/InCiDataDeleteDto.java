package com.uino.cmdb.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/28 9:54
 */
@Data
public class InCiDataDeleteDto {

    @NotEmpty(message = "id不能为空")
    private List<String> idList;

}
