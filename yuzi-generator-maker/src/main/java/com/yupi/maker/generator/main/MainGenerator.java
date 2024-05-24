package com.yupi.maker.generator.main;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/


/**
 * 生成代码生成器
 */
public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要给我输出 dist 啦！");
    }
}