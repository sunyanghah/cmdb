package com.uino.cmdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uino.cmdb.config.enums.CiFieldTypeE;
import com.uino.cmdb.config.enums.YesNoE;
import com.uino.cmdb.config.exception.BusinessException;
import com.uino.cmdb.config.platform.IdGenerator;
import com.uino.cmdb.config.util.UserUtil;
import com.uino.cmdb.dao.CiClassDao;
import com.uino.cmdb.dao.CiDataDao;
import com.uino.cmdb.dao.CiFieldDao;
import com.uino.cmdb.dao.CiRelDao;
import com.uino.cmdb.dto.InCiFieldSaveDto;
import com.uino.cmdb.dto.OutCiFieldAllDto;
import com.uino.cmdb.entity.CiClass;
import com.uino.cmdb.entity.CiField;
import com.uino.cmdb.entity.CiRel;
import com.uino.cmdb.service.CiFieldService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sunYang
 * @date 2021/10/26 18:03
 */
@Service
public class CiFieldServiceImpl extends ServiceImpl<CiFieldDao,CiField> implements CiFieldService {

    @Resource
    private CiClassDao ciClassDao;
    @Resource
    private CiFieldDao ciFieldDao;
    @Resource
    private IdGenerator idGenerator;
    @Resource
    private CiDataDao ciDataDao;
    @Resource
    private CiRelDao ciRelDao;


    /**
     * 根据classId查询字段集合
     * @author sunYang
     * @param classId
     * @return java.util.List<com.uino.xxv.manager.dto.ci.OutCiFieldAllDto>
     * @date 2022/3/7 15:14
     */
    @Override
    public List<OutCiFieldAllDto> queryAll(String classId) {
        LambdaQueryWrapper<CiField> queryWrapper = Wrappers.lambdaQuery();
        List<CiField> ciClassList = ciFieldDao.selectList(queryWrapper.eq(CiField::getClassId,classId));

        List<OutCiFieldAllDto> dtoList = ciClassList.stream()
                .map(ciField -> {
                    OutCiFieldAllDto outCiClassAllDto = new OutCiFieldAllDto();
                    BeanUtils.copyProperties(ciField, outCiClassAllDto);
                    outCiClassAllDto.setFieldTypeText(CiFieldTypeE.of(outCiClassAllDto.getFieldType()).getName());
                    outCiClassAllDto.setPrimaryFlagText(YesNoE.getTextByCode(outCiClassAllDto.getPrimaryFlag()));
                    outCiClassAllDto.setRequireFlagText(YesNoE.getTextByCode(outCiClassAllDto.getRequireFlag()));
                    return outCiClassAllDto;
                }).collect(Collectors.toList());

        handleParentClassName(dtoList);

        return dtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(InCiFieldSaveDto dto) {

        List<InCiFieldSaveDto.FieldSaveDto> newFieldList = dto.getFieldList();

        checkField(newFieldList);
        LambdaQueryWrapper<CiField> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CiField::getClassId,dto.getClassId());

        List<CiField> oldFieldList = ciFieldDao.selectList(queryWrapper);

        handleAdded(dto.getClassId(),newFieldList);
        handleUpdated(dto.getClassId(),newFieldList,oldFieldList);
        handleDeleted(dto.getClassId(),newFieldList,oldFieldList);

    }

    private void handleParentField(List<CiField> ciFieldList){

        List<String> parentClassList = ciFieldList.stream()
                .filter(ciField -> CiFieldTypeE.PARENT.getValue().equals(ciField.getFieldType()))
                .map(CiField::getParentClass)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(parentClassList)){
            return;
        }

        LambdaQueryWrapper<CiField> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(CiField::getClassId,parentClassList);
        queryWrapper.eq(CiField::getPrimaryFlag,YesNoE.YES.getCode());
        List<CiField> parentFieldList = ciFieldDao.selectList(queryWrapper);

        for (CiField ciField : ciFieldList) {
            for (CiField parentField : parentFieldList) {
                if (parentField.getClassId().equals(ciField.getParentClass())){
                    ciField.setParentField(parentField.getId());
                }
            }
        }

    }

    private void handleAdded(String classId,List<InCiFieldSaveDto.FieldSaveDto> newFieldList){

        List<InCiFieldSaveDto.FieldSaveDto> addedList = new ArrayList<>();

        for (InCiFieldSaveDto.FieldSaveDto newField : newFieldList) {
            if (StringUtils.isBlank(newField.getId())){
                addedList.add(newField);
            }
        }

        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();
        List<CiField> addedFieldList = addedList.stream()
                .map(dto -> {
                    String fieldId = idGenerator.nextStr();
                    CiField ciField = new CiField();
                    ciField.setClassId(classId);
                    BeanUtils.copyProperties(dto, ciField);
                    ciField.setId(fieldId);
                    ciField.setCreateBy(currentUserId);
                    ciField.setUpdateBy(currentUserId);
                    ciField.setCreateTime(now);
                    ciField.setUpdateTime(now);
                    return ciField;
                }).collect(Collectors.toList());

        handleParentField(addedFieldList);

        this.saveBatch(addedFieldList);

    }

    private void handleUpdated(String classId,List<InCiFieldSaveDto.FieldSaveDto> newFieldList,List<CiField> oldFieldList){


        List<CiField> updatedList = new ArrayList<>();
        List<CiField> updatedAndClearList = new ArrayList<>();

        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();

        for (InCiFieldSaveDto.FieldSaveDto newField : newFieldList) {

            for (CiField oldField : oldFieldList) {
                if (oldField.getId().equals(newField.getId())){

                    CiField ciField = new CiField();
                    BeanUtils.copyProperties(newField,ciField);
                    ciField.setClassId(classId);
                    ciField.setId(oldField.getId());
                    ciField.setUpdateBy(currentUserId);
                    ciField.setUpdateTime(now);

                    updatedList.add(ciField);
                    if (!newField.getFieldType().equals(oldField.getFieldType())){
                        updatedAndClearList.add(ciField);
                    }
                }
            }

        }

        handleParentField(updatedList);

        this.updateBatchById(updatedList);

        // 删除掉改变数据类型的字段，相当于清空某一列的值
        for (CiField ciField : updatedAndClearList) {
            ciDataDao.removeJsonField(classId,ciField.getFieldName());
        }

    }

    private void handleDeleted(String classId,List<InCiFieldSaveDto.FieldSaveDto> newFieldList,List<CiField> oldFieldList){
        List<CiField> deletedList = new ArrayList<>();

        for (CiField oldField : oldFieldList) {
            boolean hasFlag = false;
            for (InCiFieldSaveDto.FieldSaveDto newField : newFieldList) {
                if (oldField.getId().equals(newField.getId())){
                    hasFlag = true;
                }
            }

            if (!hasFlag){
                deletedList.add(oldField);
            }

        }

        for (CiField deleted : deletedList) {
            ciFieldDao.deleteById(deleted);
            ciDataDao.removeJsonField(classId,deleted.getFieldName());
            if (CiFieldTypeE.PARENT.getValue().equals(deleted.getFieldType())){
                LambdaQueryWrapper<CiRel> deleteWrapper = Wrappers.lambdaQuery();
                deleteWrapper.eq(CiRel::getSourceClassId,deleted.getParentClass());
                deleteWrapper.eq(CiRel::getTargetClassId,deleted.getClassId());
                ciRelDao.delete(deleteWrapper);
            }

        }

    }


    private void checkField(List<InCiFieldSaveDto.FieldSaveDto> dtoList){

        List<InCiFieldSaveDto.FieldSaveDto> primaryList = dtoList.stream()
                .filter(dto -> YesNoE.YES.getCode().equals(dto.getPrimaryFlag()))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(primaryList)){
            throw new BusinessException("请设置主键字段");
        }
        if (primaryList.size() > 1){
            throw new BusinessException("只允许有一个主键字段");
        }

        Set<String> nameSet = dtoList.stream()
                .map(InCiFieldSaveDto.FieldSaveDto::getFieldName)
                .collect(Collectors.toSet());

        if (nameSet.size() < dtoList.size()){
            throw new BusinessException("字段名称不允许重复");
        }
    }


    /**
     * 处理父级class的名称
     * @author sunYang
     * @param dtoList
     * @return void
     * @date 2022/3/7 15:14
     */
    private void handleParentClassName(List<OutCiFieldAllDto> dtoList){

        List<OutCiFieldAllDto> parentDtoList = dtoList.stream()
                .filter(dto -> CiFieldTypeE.PARENT.getValue().equals(dto.getFieldType()))
                .collect(Collectors.toList());

        if (parentDtoList.size() == 0){
            return;
        }

        List<String> parentClassList = parentDtoList.stream()
                .map(OutCiFieldAllDto::getParentClass)
                .collect(Collectors.toList());

        LambdaQueryWrapper<CiClass> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.select(CiClass::getId,CiClass::getClassName).in(CiClass::getId,parentClassList);
        List<CiClass> ciClassList = ciClassDao.selectList(queryWrapper);

        for (OutCiFieldAllDto dto : parentDtoList) {

            CiClass ciClass = ciClassList.stream().filter(cc -> dto.getParentClass().equals(cc.getId())).findFirst().orElse(null);
            if (ciClass != null){
                dto.setParentClassName(ciClass.getClassName());
            }
        }

    }

}
