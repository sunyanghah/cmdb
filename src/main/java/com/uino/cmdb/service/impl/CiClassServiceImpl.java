package com.uino.cmdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.uino.cmdb.config.enums.ExceptionCodeE;
import com.uino.cmdb.config.exception.BusinessException;
import com.uino.cmdb.config.platform.IdGenerator;
import com.uino.cmdb.config.util.UserUtil;
import com.uino.cmdb.dao.CiClassDao;
import com.uino.cmdb.dao.CiDataDao;
import com.uino.cmdb.dao.CiFieldDao;
import com.uino.cmdb.dao.CiRelDao;
import com.uino.cmdb.dto.*;
import com.uino.cmdb.entity.CiClass;
import com.uino.cmdb.entity.CiData;
import com.uino.cmdb.entity.CiField;
import com.uino.cmdb.entity.CiRel;
import com.uino.cmdb.service.CiClassService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ci class service
 * @author sunYang
 * @date 2021/10/26 18:04
 */
@Service
public class CiClassServiceImpl implements CiClassService {

    @Resource
    private CiClassDao ciClassDao;
    @Resource
    private IdGenerator idGenerator;
    @Resource
    private CiFieldDao ciFieldDao;
    @Resource
    private CiDataDao ciDataDao;
    @Resource
    private CiRelDao ciRelDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(InCiClassDeleteDto dto) {

        LambdaQueryWrapper<CiData> dataDeleteWrapper = Wrappers.lambdaQuery();
        dataDeleteWrapper.eq(CiData::getClassId,dto.getId());
        ciDataDao.delete(dataDeleteWrapper);

        LambdaQueryWrapper<CiRel> relDeleteWrapper = Wrappers.lambdaQuery();
        relDeleteWrapper.eq(CiRel::getSourceClassId,dto.getId());
        relDeleteWrapper.or(rd -> rd.eq(CiRel::getTargetClassId,dto.getId()));
        ciRelDao.delete(relDeleteWrapper);

        LambdaQueryWrapper<CiField> fieldDeleteWrapper = Wrappers.lambdaQuery();
        fieldDeleteWrapper.eq(CiField::getClassId,dto.getId());
        ciFieldDao.delete(fieldDeleteWrapper);

        ciClassDao.deleteById(dto.getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String add(InCiClassAddDto dto) throws Exception{

        checkClassName(dto.getClassName(),null);

        String classId = idGenerator.nextStr();
        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();
        CiClass ciClass = CiClass.builder()
                .id(classId)
                .className(dto.getClassName())
                .description(dto.getDescription())
                .sort(dto.getSort())
                .createBy(currentUserId)
                .updateBy(currentUserId)
                .createTime(now)
                .updateTime(now)
                .build();
        ciClassDao.insert(ciClass);
        return classId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(InCiClassEditDto dto) throws Exception{

        checkClassName(dto.getClassName(),dto.getId());

        String currentUserId = UserUtil.getCurrentUser().getId();
        Date now = Calendar.getInstance().getTime();
        CiClass ciClass = CiClass.builder()
                .id(dto.getId())
                .className(dto.getClassName())
                .description(dto.getDescription())
                .sort(dto.getSort())
                .updateBy(currentUserId)
                .updateTime(now)
                .build();
        ciClassDao.updateById(ciClass);
    }

    @Override
    public List<OutCiClassAllDto> queryAll(InCiClassQueryDto dto) {

        LambdaQueryWrapper<CiClass> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.orderByAsc(CiClass::getSort);

        if (StringUtils.isNotBlank(dto.getClassName())){
            queryWrapper.and(qw -> qw.like(CiClass::getClassName,dto.getClassName()));
        }

        List<CiClass> ciClassList = ciClassDao.selectList(queryWrapper);

        List<OutCiClassAllDto> dtoList = ciClassList.stream()
                .map(ciClass -> {
                    OutCiClassAllDto outCiClassAllDto = new OutCiClassAllDto();
                    BeanUtils.copyProperties(ciClass, outCiClassAllDto);
                    return outCiClassAllDto;
                }).collect(Collectors.toList());

        return dtoList;
    }

    private void checkClassName(String className,String classId) throws Exception{
        LambdaQueryWrapper<CiClass> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CiClass::getClassName,className);
        if (StringUtils.isNotBlank(classId)){
            queryWrapper.ne(CiClass::getId,classId);
        }

        Long num = ciClassDao.selectCount(queryWrapper);

        if (num > 0){
            throw new BusinessException(ExceptionCodeE.CODE_DUPLICATE_ERROR);
        }

    }

}
