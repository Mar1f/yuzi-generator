package com.yupi.model;

import lombok.Data;
/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/


/**
 * 动态模版配置
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "yupi";

    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}