package com.yupi.maker.generator.main;

/**
 * @description；生成代码生成器zip
 * @author:mar1
 * @data:2024/05/26
 **/
public class ZipGenerator extends GenerateTemplate {

    @Override
    protected String buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String buildDist = super.buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);
        return super.buildZip(buildDist);
    }
}