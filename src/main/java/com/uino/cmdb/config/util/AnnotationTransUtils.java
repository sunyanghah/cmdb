package com.uino.cmdb.config.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author Zhai Xianhao
 * @date 2021年12月01日 10:51
 * 使用注解实现返回值的转化（用户层面：id转成realName）
 */
@Configuration
public class AnnotationTransUtils extends JsonSerializer<String> {


    @Override
    public void serialize(String arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
//        SysUser sysUser = sysUserDao.selectById(arg0);
//
//        if(arg0 != null && sysUser != null) {
//            arg1.writeString(sysUser.getRealName());
//        }else{
//            arg1.writeString("user not find");
//        }
    }
}
