package com.yupi.maker.meta.enums;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/
/**
 * 模型类型枚举
 */
public enum ModelTypeEnum {

    STRING("字符串", "String"),
    BOOLEAN("布尔", "boolean");

    private final String text;

    private final String value;

    ModelTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}