package com.uino.cmdb.service.impl;

import com.uino.cmdb.config.enums.YesNoE;
import com.uino.cmdb.config.exception.BusinessException;
import com.uino.cmdb.dto.OutCiFieldAllDto;
import com.uino.cmdb.service.AbstractFieldCheck;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author sunYang
 * @date 2022/9/15 17:33
 */
public class FieldRequireCheck extends AbstractFieldCheck {

    public FieldRequireCheck(){}

    @Override
    public void check(String classId, Map<String, Object> data, List<OutCiFieldAllDto> ciFieldList, String selfId) throws Exception {
        for (Map.Entry<String,Object> entry : data.entrySet()) {
            OutCiFieldAllDto ciField = ciFieldList.stream().filter(cf -> cf.getId().equals(entry.getKey())).findFirst().get();
            String requireFlag = ciField.getRequireFlag();
            if (YesNoE.YES.getCode().equals(requireFlag)){

                if (entry.getValue() == null || StringUtils.isBlank(entry.getValue().toString())){
                    throw new BusinessException("字段："+entry.getKey()+"，值："+entry.getValue()+"，主键字段为必填！");
                }

            }
        }
    }

}
