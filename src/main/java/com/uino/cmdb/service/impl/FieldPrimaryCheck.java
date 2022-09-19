package com.uino.cmdb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.uino.cmdb.config.enums.YesNoE;
import com.uino.cmdb.config.exception.BusinessException;
import com.uino.cmdb.dao.CiDataDao;
import com.uino.cmdb.dto.OutCiFieldAllDto;
import com.uino.cmdb.entity.CiData;
import com.uino.cmdb.service.AbstractFieldCheck;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author sunYang
 * @date 2021/10/28 14:09
 */
public class FieldPrimaryCheck extends AbstractFieldCheck {

    private CiDataDao ciDataDao;

    public FieldPrimaryCheck(){}

    public FieldPrimaryCheck(CiDataDao ciDataDao){
        this.ciDataDao = ciDataDao;
    }

    @Override
    public void check(String classId, Map<String, Object> data, List<OutCiFieldAllDto> ciFieldList, String selfId) throws Exception {

        for (Map.Entry<String,Object> entry : data.entrySet()) {
            OutCiFieldAllDto ciField = ciFieldList.stream().filter(cf -> cf.getId().equals(entry.getKey())).findFirst().get();
            String primaryFlag = ciField.getPrimaryFlag();
            if (YesNoE.YES.getCode().equals(primaryFlag)){


                if (entry.getValue() == null || StringUtils.isBlank(entry.getValue().toString())){
                    throw new BusinessException("字段："+ciField.getFieldName()+"，值："+entry.getValue()+"，主键字段为必填！");
                }

                QueryWrapper<CiData> queryWrapper = Wrappers.query();
                queryWrapper.lambda().and(qw -> qw.eq(CiData::getClassId,classId));
                queryWrapper.and(qw -> qw.eq("data -> '$.\""+entry.getKey()+"\"'",entry.getValue()));

                if (selfId != null){
                    queryWrapper.lambda().and(qw -> qw.ne(CiData::getId,selfId));
                }

                Long dataNum = ciDataDao.selectCount(queryWrapper);
                if (dataNum != null && dataNum > 0){
                    throw new BusinessException("字段："+ciField.getFieldName()+"，值："+entry.getValue()+"，主键字段必须唯一！");
                }
            }
        }

        this.getNext().check(classId,data,ciFieldList,selfId);
    }

}
