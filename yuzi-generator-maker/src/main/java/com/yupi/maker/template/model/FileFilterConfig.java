package com.yupi.maker.template.model;

import lombok.Builder;
import lombok.Data;

/**
 * @description；文件过滤配置
 * @author:mar1
 * @data:2024/05/24
 **/
@Data
@Builder
public class FileFilterConfig {

    /**
     * 过滤范围
     */
    private String range;

    /**
     * 过滤规则
     */
    private String rule;

    /**
     * 过滤值
     */
    private String value;

}