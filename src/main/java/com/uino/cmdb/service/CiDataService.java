package com.uino.cmdb.service;


import com.uino.cmdb.config.page.BaseOutPageDto;
import com.uino.cmdb.dto.*;

import java.util.List;

/**
 * @author sunYang
 * @date 2021/10/26 18:03
 */
public interface CiDataService {

    /**
     * 新增
     * @author sunYang
     * @param dto
     * @return java.lang.String
     * @throws Exception
     * @date 2021/10/28 9:58
     */
    String add(InCiDataAddDto dto) throws Exception;

    /**
     * 修改
     * @author sunYang
     * @param dto
     * @return void
     * @throws Exception
     * @date 2021/10/28 9:58
     */
    void edit(InCiDataEditDto dto) throws Exception;

    /**
     * 删除
     * @author sunYang
     * @param dto
     * @return void
     * @date 2021/10/28 9:58
     */
    void delete(InCiDataDeleteDto dto);

    /**
     * 分页查询
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.page.BaseOutPageDto<com.uino.xxv.manager.dto.ci.OutCiDataPageDto>
     * @date 2021/10/29 10:32
     */
    BaseOutPageDto<OutCiDataPageDto> page(InCiDataPageDto dto);

    /**
     * 获取详情
     * @author sunYang
     * @param dataId
     * @return com.uino.xxv.manager.dto.ci.OutCiDataInfoDto
     * @date 2021/10/29 14:13
     */
    OutCiDataInfoDto info(String dataId);

    /**
     * 根据条件查询列表
     * @author sunYang
     * @param dto
     * @return java.util.List<com.uino.xxv.manager.dto.ci.OutCiDataSearchDto>
     * @date 2021/11/1 15:14
     */
    List<OutCiDataSearchDto> search(InCiDataSearchDto dto);
}
