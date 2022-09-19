package com.uino.cmdb.config.business;

import com.uino.cmdb.dao.CiDataDao;
import com.uino.cmdb.service.impl.FieldPrimaryCheck;
import com.uino.cmdb.service.impl.FieldRequireCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author sunYang
 * @date 2021/10/28 14:52
 */
@Configuration
public class FieldCheckChain {

    @Resource
    private CiDataDao ciDataDao;

    @Bean
    public FieldPrimaryCheck fieldPrimaryCheck(){
        FieldPrimaryCheck fieldPrimaryCheck = new FieldPrimaryCheck(ciDataDao);
        fieldPrimaryCheck.setNext(fieldRequireCheck());
        return fieldPrimaryCheck;
    }

    @Bean
    public FieldRequireCheck fieldRequireCheck(){
        FieldRequireCheck fieldRequireCheck = new FieldRequireCheck();

        return fieldRequireCheck;
    }

}
