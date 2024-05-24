package com.yupi.maker.generator.file;

import cn.hutool.core.io.FileUtil;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/

/**
 * 静态文件生成
 */
public class StaticFileGenerator {

    /**
     * 拷贝文件（Hutool 实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}