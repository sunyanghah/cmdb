package com.uino.cmdb.service;

import com.uino.cmdb.dto.OutCiFieldAllDto;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author sunYang
 * @date 2021/10/28 14:07
 */
@Data
@Service
public abstract class AbstractFieldCheck {

    private AbstractFieldCheck next;

    public abstract void check(String classId, Map<String, Object> data, List<OutCiFieldAllDto> ciFieldList, String selfId) throws Exception;

}
