package com.uino.cmdb.service.impl;

import com.uino.cmdb.dto.OutCiFieldAllDto;
import com.uino.cmdb.service.AbstractFieldCheck;

import java.util.List;
import java.util.Map;

/**
 * @author sunYang
 * @date 2022/9/15 17:52
 */
public class FieldLengthCheck extends AbstractFieldCheck {
    @Override
    public void check(String classId, Map<String, Object> data, List<OutCiFieldAllDto> ciFieldList, String selfId) throws Exception {

    }
}
