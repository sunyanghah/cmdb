package com.uino.cmdb.config.enums;

import lombok.Getter;

/**
 * 错误码-描述 枚举
 * @author sunYang
 * @date 2021/9/17 17:22
 */
@Getter
public enum ExceptionCodeE {

    /**
     * 错误码与描述，
     */

    AUTHENTICATION_ERROR(401, "认证失败"),
    AUTHORIZATION_ERROR(403, "权限不足"),

    SERVER_ERROR(500, "程序异常"),
    REQ_ERROR(501, "请求参数异常"),

    OPERATE_FAIL(502, "操作失败"),
    DETAIL_ERROR(503, "请求数据有误！"),
    ADMIN_ROLE_UPDATE_ERROR(509, "管理员角色不允许被修改"),
    ADMIN_ROLE_DELETE_ERROR(510,"管理员角色不允许被删除"),
    ADMIN_USER_DELETE_ERROR(511,"管理员用户不允许被删除"),
    COORDINATE_ERROR(512,"坐标格式错误"),
    LABEL_OFFSET_ERROR(513, "偏移量格式错误"),

    // 参数
    PARAM_LESS(10001, "缺少必填参数"),
    PARAM_VALID_ERROR(10002, "参数校验错误"),

    NOTDATA_ERROR(10101, "没有更多数据"),
    OVERDATA_ERROR(10102, "数据过多"),
    CODE_DUPLICATE_ERROR(10103,"数据编码重复"),
    CONFIG_DUPLICATE_ERROR(10104,"配置项重复"),

    OLD_PASSWORD_ERROR(10201,"原密码错误"),
    PHONE_ERROR(10202, "手机号格式不合法"),
    NICKNAME_REPEAT(10203, "该昵称已存在！"),
    USERNAME_REPEAT(10204,"用户名已存在"),
    DATA_SECTION_REPEAT(10205,"数据区间冲突"),
    ALARM_LEVEL_VALUE_REPEAT(10206,"告警级别重复"),
    ALARM_TYPE_VALUE_REPEAT(10207,"告警编码重复"),
    ROAD_STATUS_VALUE_REPEAT(10208,"线路状态编码重复"),
    STATION_CONTROL_VALUE_REPEAT(10209,"站控措施编码重复"),
//    CI_FIELD_UNIQUE_PARENT(),

    ERROR_CODE_DUPLICATE(10301,"编码不允许重复"),
    ERROR_IMPORT(10302,"导入数据失败"),

    ERROR_1210(10401, "验证码错误！"),
    ERROR_1212(10402, "验证码失效，请重新获取！"),

    //file
    ERROR_FILE_UPLOAD(10501,"文件上传失败"),



    LICENSE_ERROR(10601, "授权码错误"),

    LICENSE_EXPIRE(10602, "系统授权已超时"),

    LICENSE_OVERSTEP(10603, "已超出系统授权数量"),

    LICENSE_DEPLOY_ERROR(10604,"系统未获取到服务器唯一信息，无法授权"),

    LICENSE_UNKNOWN_MACHINE(10605,"未授权的服务器加入到了集群中"),

    LICENSE_NOT(10606,"未授权"),

    //线路集群
    ERROR_GROUP_REPEAT(10701,"集群名称重复"),

    NOT_SUPPORT_MODULE_ERROR(10801, "不支持的模块编码"),
    NOT_SUPPORT_BUTTON_ERROR(10802,"不支持的按钮编码"),
    NOT_SUPPORT_BUTTON_STATUS_ERROR(10803,"不支持的按钮状态")
    ;

    /**
     * 错误码
     */
    @Getter
    private final Integer code;
    /**
     * 描述
     */
    @Getter
    private String msg;

    /**
     * @param code 错误码
     * @param msg  描述
     */
    ExceptionCodeE(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }
}
