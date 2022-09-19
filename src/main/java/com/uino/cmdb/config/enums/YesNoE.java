package com.uino.cmdb.config.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * 是  否 枚举
 * @author sunYang
 * @Date 2021/9/15
 */
public enum YesNoE {

  /**
   * 否
   */
  NO("0","否"),

  /**
   * 是
   */
  YES("1","是");


  @Getter
  private String code;
  @Getter
  private String text;

  YesNoE(String code, String text){
    this.code = code;
    this.text = text;
  }

  public static YesNoE of(String code) {
    return Stream.of(YesNoE.values())
      .filter(e -> e.getCode().equals(code))
      .findFirst()
      .orElse(null);
  }

  public static String getTextByCode(String code){
    YesNoE yesNoE = of(code);
    return yesNoE == null ? "":yesNoE.getText();
  }

}
