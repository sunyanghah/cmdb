package com.uino.cmdb.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uino.cmdb.dto.InCiDataPageDto;
import com.uino.cmdb.entity.CiData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sunYang
 * @date 2021/10/26 18:01
 */
@Mapper
public interface CiDataDao extends BaseMapper<CiData> {

    /**
     * 分页查询ciData
     * @author sunYang
     * @param page
     * @param dto
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.uino.xxv.common.entity.ci.CiData>
     * @date 2022/3/7 15:35
     */
    IPage<CiData> pageCiData(Page page, @Param("dto") InCiDataPageDto dto);

    /**
     * 根据class和field查询ciData
     * @author sunYang
     * @param classId
     * @param fieldName
     * @param fieldValue
     * @return com.uino.xxv.common.entity.ci.CiData
     * @date 2022/3/7 15:35
     */
    CiData queryCiByClassAndField(@Param("classId")String classId, @Param("fieldName")String fieldName,
                                  @Param("fieldValue")String fieldValue);

    /**
     * 删除json数据中的字段
     * @param classId
     * @param fieldName
     */
    void removeJsonField(@Param("classId")String classId,@Param("fieldName")String fieldName);

    /**
     * 清空json数据中的字段的值
     * @param classId
     * @param fieldName
     */
    void clearJsonField(@Param("classId")String classId,@Param("fieldName")String fieldName);
}
