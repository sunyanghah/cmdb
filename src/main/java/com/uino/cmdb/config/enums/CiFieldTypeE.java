package com.uino.cmdb.config.enums;

import java.util.stream.Stream;

/**
 * 字段类型枚举
 *
 * @author sy
 * @date 2020-08-01
 */
public enum CiFieldTypeE {

  /**
   * 字符串
   */
  VARCHAR("1", "字符串"),

  /**
   * 整数
   */
  INT("2", "整数"),

  /**
   * 小数
   */
  DECIMAL("3", "小数"),

  /**
   * 日期
   */
  DATE("4", "日期"),

  /**
   * 时间
   */
  TIME("5", "时间"),

  /**
   * 日期时间
   */
  DATETIME("6", "日期时间"),

  /**
   * 选项
   */
  OPTION("7", "选项"),

  /**
   * 父级
   */
  PARENT("8", "父级");

  @lombok.Getter
  private final String value;
  @lombok.Getter
  private final String name;

  CiFieldTypeE(String value, String name) {
    this.value = value;
    this.name = name;
  }

  public boolean eq(String value) {
    return this.equals(of(value));
  }

  /**
   * 得到枚举值，默认 VARCHAR。
   *
   * @param value value
   * @return enum
   */
  public static CiFieldTypeE of(String value) {
    return of(value, VARCHAR);
  }

  public static CiFieldTypeE of(String value, CiFieldTypeE defaultValue) {
    return Stream.of(CiFieldTypeE.values())
      .filter(e -> e.getValue().equals(value))
      .findFirst()
      .orElse(defaultValue);
  }

  public static boolean eq(String value, CiFieldTypeE fileStatusE) {
    if ((value == null)
      || (fileStatusE == null)) {
      return false;
    }
    return fileStatusE.eq(value);
  }

  public static boolean eq(CiFieldTypeE fileStatusE, String value) {
    return eq(value, fileStatusE);
  }

  public static String getNameByValue(String value){
    CiFieldTypeE ciFieldTypeE = of(value);
    if (ciFieldTypeE == null){
      return "";
    }
    return ciFieldTypeE.getName();
  }

}
