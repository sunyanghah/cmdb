package com.uino.cmdb.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.uino.cmdb.dto.InCiFieldSaveDto;
import com.uino.cmdb.dto.OutCiFieldAllDto;
import com.uino.cmdb.entity.CiField;

import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/26 18:03
 */
public interface CiFieldService extends IService<CiField> {


    /**
     * 查询全部
     * @author sunYang
     * @param classId
     * @return java.util.List<com.uino.xxv.manager.dto.ci.OutCiFieldAllDto>
     * @date 2021/10/27 11:46
     */
    List<OutCiFieldAllDto> queryAll(String classId);

    /**
     * 保存
     * @param dto
     */
    void save(InCiFieldSaveDto dto);
}
