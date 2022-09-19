package com.uino.cmdb.service;


import com.uino.cmdb.dto.*;

import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/26 18:03
 */
public interface CiClassService {

    /**
     * 删除
     * @author sunYang
     * @param dto
     * @return void
     * @date 2021/10/27 9:58
     */
    void delete(InCiClassDeleteDto dto);

    /**
     * 新增
     * @author sunYang
     * @param dto
     * @return java.lang.String
     * @throws Exception
     * @date 2021/10/27 9:58
     */
    String add(InCiClassAddDto dto) throws Exception;

    /**
     * 修改
     * @author sunYang
     * @param dto
     * @return void
     * @throws Exception
     * @date 2021/10/27 9:59
     */
    void edit(InCiClassEditDto dto) throws Exception;

    /**
     * 查询全部
     * @author sunYang
     * @param dto
     * @return java.util.List<com.uino.xxv.manager.dto.ci.OutCiClassAllDto>
     * @date 2021/10/27 11:08
     */
    List<OutCiClassAllDto> queryAll(InCiClassQueryDto dto);

}
