package com.yupi.maker.template.model;

import lombok.Data;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/25
 **/
@Data
public class TemplateMakerOutputConfig {

    // 从未分组文件中移除组内的同名文件
    private boolean removeGroupFilesFromRoot = true;
}