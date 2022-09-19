package com.uino.cmdb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uino.cmdb.dao.CiRelDao;
import com.uino.cmdb.entity.CiRel;
import com.uino.cmdb.service.CiRelService;
import org.springframework.stereotype.Service;

/**
 * @author sunYang
 * @date 2021/10/26 18:05
 */
@Service
public class CiRelServiceImpl extends ServiceImpl<CiRelDao, CiRel> implements CiRelService {
}
