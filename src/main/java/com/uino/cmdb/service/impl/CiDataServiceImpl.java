package com.uino.cmdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uino.cmdb.config.enums.CiFieldTypeE;
import com.uino.cmdb.config.page.BaseOutPageDto;
import com.uino.cmdb.config.platform.IdGenerator;
import com.uino.cmdb.config.util.UserUtil;
import com.uino.cmdb.dao.CiDataDao;
import com.uino.cmdb.dto.*;
import com.uino.cmdb.entity.CiData;
import com.uino.cmdb.entity.CiRel;
import com.uino.cmdb.service.CiDataService;
import com.uino.cmdb.service.CiFieldService;
import com.uino.cmdb.service.CiRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunYang
 * @date 2021/10/26 18:04
 */
@Service
public class CiDataServiceImpl implements CiDataService {

    @Resource
    private CiDataDao ciDataDao;
    @Resource
    private IdGenerator idGenerator;
    @Resource
    private CiFieldService ciFieldService;
    @Resource
    private FieldPrimaryCheck fieldPrimaryCheck;
    @Resource
    private CiRelService ciRelService;

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 新增
     * @author sunYang
     * @param dto
     * @return java.lang.String
     * @date 2021/10/29 10:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String add(InCiDataAddDto dto) throws Exception {

        checkField(dto.getClassId(),dto.getData(),null);

        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();
        String dataId = idGenerator.nextStr();

        Map<String, Object> data = dto.getData();
        CiData ciData = CiData.builder()
                .id(dataId)
                .classId(dto.getClassId())
                .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data))
                .createBy(currentUserId)
                .updateBy(currentUserId)
                .createTime(now)
                .updateTime(now)
                .build();
        ciDataDao.insert(ciData);

        handleRel(dto.getClassId(),dto.getData(),dataId);

        return dataId;
    }

    /**
     * 检查字段值
     * @author sunYang
     * @param classId
     * @param data 数据
     * @param selfId 修改时传本条数据ID，新增时传null
     * @return void
     * @date 2021/11/1 14:49
     */
    private void checkField(String classId,Map<String, Object> data,String selfId) throws Exception{

        List<OutCiFieldAllDto> ciFieldList = ciFieldService.queryAll(classId);

        fieldPrimaryCheck.check(classId,data,ciFieldList,selfId);

    }

    private void handleRel(String selfClassId,Map<String,Object> data,String selfId){

        List<OutCiFieldAllDto> ciFieldList = ciFieldService.queryAll(selfClassId);

        List<OutCiFieldAllDto> parentFieldList = ciFieldList.stream().filter(field ->
                field.getFieldType().equals(CiFieldTypeE.PARENT.getValue())).collect(Collectors.toList());

        List<CiRel> ciRelList = new ArrayList<>();

        for (OutCiFieldAllDto fieldDto : parentFieldList) {
            String parentClass = fieldDto.getParentClass();

            List<String> parentIdList = (List<String>) data.get(fieldDto.getId());

            parentIdList.stream()
                    .map(parentId -> {
                        CiData ciData = ciDataDao.queryCiByClassAndField(parentClass, fieldDto.getParentField(), parentId);
                        CiRel ciRel = CiRel.builder()
                                .id(idGenerator.nextStr())
                                .sourceClassId(parentClass)
                                .sourceId(ciData.getId())
                                .targetClassId(selfClassId)
                                .targetId(selfId)
                                .build();
                        return ciRel;
                    }).forEach(ciRelList::add);

        }

        ciRelService.saveBatch(ciRelList);

    }

    /**
     * 删除旧的关系
     * @author sunYang
     * @param
     * @return void
     * @date 2022/2/25 18:10
     */
    private void deleteOldRel(String classId,String dataId){
        LambdaQueryWrapper<CiRel> deleteWrapper = Wrappers.lambdaQuery();
        deleteWrapper.eq(CiRel::getTargetClassId,classId);
        deleteWrapper.and(dw -> dw.eq(CiRel::getTargetId,dataId));
        ciRelService.remove(deleteWrapper);
    }

    /**
     * 修改
     * @author sunYang
     * @param dto
     * @return void
     * @date 2021/10/29 10:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(InCiDataEditDto dto) throws Exception{

        checkField(dto.getClassId(),dto.getData(),dto.getId());

        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();

        Map<String, Object> data = dto.getData();
        CiData ciData = CiData.builder()
                .id(dto.getId())
                .classId(dto.getClassId())
                .data(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data))
                .updateBy(currentUserId)
                .updateTime(now)
                .build();
        ciDataDao.updateById(ciData);

        deleteOldRel(dto.getClassId(),dto.getId());
        handleRel(dto.getClassId(),dto.getData(),dto.getId());

    }

    /**
     * 删除
     * @author sunYang
     * @param dto
     * @return void
     * @date 2021/10/29 10:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(InCiDataDeleteDto dto) {

        dto.getIdList().forEach(ciDataDao::deleteById);

    }

    /**
     * 分页查询
     * @author sunYang
     * @param dto
     * @return com.uino.xxv.common.config.page.BaseOutPageDto<com.uino.xxv.manager.dto.ci.OutCiDataPageDto>
     * @date 2021/10/29 10:32
     */
    @Override
    public BaseOutPageDto<OutCiDataPageDto> page(InCiDataPageDto dto) {

        BaseOutPageDto<OutCiDataPageDto> rt = new BaseOutPageDto<>();

        Page page = PageDTO.of(dto.getPageNumber(), dto.getPageSize());

        IPage<CiData> pageResult = ciDataDao.pageCiData(page, dto);

        // 返回
        List<OutCiDataPageDto> records = new ArrayList<>();
        List<CiData> resultList = pageResult.getRecords();
        if (resultList != null && resultList.size() > 0){
            records = resultList.stream()
                    .map(ciData -> {
                        OutCiDataPageDto outCiDataPageDto = new OutCiDataPageDto();
                        outCiDataPageDto.setId(ciData.getId());
                        outCiDataPageDto.setClassId(ciData.getClassId());
                        try {
                            outCiDataPageDto.setData(objectMapper.readValue(ciData.getData(), Map.class));
                        }catch (Exception e){
                            e.printStackTrace();
                            outCiDataPageDto.setData(null);
                        }
                        outCiDataPageDto.setCreateBy(ciData.getCreateBy());
                        outCiDataPageDto.setCreateTime(ciData.getCreateTime());
                        outCiDataPageDto.setUpdateBy(ciData.getUpdateBy());
                        outCiDataPageDto.setUpdateTime(ciData.getUpdateTime());
                        return outCiDataPageDto;
                    }).collect(Collectors.toList());
        }

        rt.setTotal(pageResult.getTotal());
        rt.setRecords(records);
        rt.setPageNumber(dto.getPageNumber());
        rt.setPageSize(dto.getPageSize());
        return rt;

    }

    /**
     * 获取详情
     * @author sunYang
     * @param dataId
     * @return com.uino.xxv.manager.dto.ci.OutCiDataInfoDto
     * @date 2021/10/29 14:13
     */
    @Override
    public OutCiDataInfoDto info(String dataId) {

        CiData ciData = ciDataDao.selectById(dataId);

        OutCiDataInfoDto outCiDataInfoDto = new OutCiDataInfoDto();
        outCiDataInfoDto.setId(ciData.getId());
        outCiDataInfoDto.setClassId(ciData.getClassId());
        try {
            outCiDataInfoDto.setData(objectMapper.readValue(ciData.getData(), Map.class));
        }catch (Exception e){
            e.printStackTrace();
            outCiDataInfoDto.setData(null);
        }
        outCiDataInfoDto.setCreateBy(ciData.getCreateBy());
        outCiDataInfoDto.setCreateTime(ciData.getCreateTime());
        outCiDataInfoDto.setUpdateBy(ciData.getUpdateBy());
        outCiDataInfoDto.setUpdateTime(ciData.getUpdateTime());
        return outCiDataInfoDto;

    }

    /**
     * 查询
     * @author sunYang
     * @param dto
     * @return java.util.List<com.uino.xxv.manager.dto.ci.OutCiDataSearchDto>
     * @date 2021/11/1 15:26
     */
    @Override
    public List<OutCiDataSearchDto> search(InCiDataSearchDto dto) {

        LambdaQueryWrapper<CiData> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.and(qw -> qw.eq(CiData::getClassId,dto.getClassId()));
        queryWrapper.and(qw -> qw.like(CiData::getData,dto.getSearchStr()));

        Page pageRequest = PageDTO.of(1, 20);

        IPage pageResult = ciDataDao.selectPage(pageRequest, queryWrapper);

        List<CiData> dataList = pageResult.getRecords();

        if (dataList == null || dataList.size() == 0){
            return Arrays.asList();
        }

        List<OutCiDataSearchDto> dtoList = dataList.stream()
                .map(data -> {
                    OutCiDataSearchDto outCiDataSearchDto = new OutCiDataSearchDto();
                    outCiDataSearchDto.setId(data.getId());
                    outCiDataSearchDto.setClassId(data.getClassId());
                    try {
                        outCiDataSearchDto.setData(objectMapper.readValue(data.getData(), Map.class));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return outCiDataSearchDto;
                }).collect(Collectors.toList());

        return dtoList;
    }

    /**
     * 构建查询条件
     * @author sunYang
     * @param queryWrapper
     * @param condition
     * @return com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<com.uino.xxv.server.entity.ci.CiData>
     * @date 2021/10/29 13:45
     */
    private QueryWrapper<CiData> buildCondition(QueryWrapper<CiData> queryWrapper, CiDataConditionDto condition){
        switch (condition.getCondition()){
            case "like": return queryWrapper.like(buildColumn(condition.getField()),condition.getValue());
            case "ge": return queryWrapper.ge(buildColumn(condition.getField()),condition.getValue());
            case "le": return queryWrapper.le(buildColumn(condition.getField()),condition.getValue());
            default: return queryWrapper.eq(buildColumn(condition.getField()),condition.getValue());
        }
    }

    private String buildColumn(String field){
        return "data -> '$.\""+field+"\"'";
    }

}
