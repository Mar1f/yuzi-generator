package com.yupi.generator;

import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/

/**
 * 核心生成器
 */
public class MainGenerator {

    /**
     * 生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String inputRootPath = "F:\\java\\code\\yuzi-generator\\yuzi-generator-demo-projects\\acm-template-pro";
        String outputRootPath = "F:\\java\\code\\yuzi-generator\\acm-template";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath,"src/com/yuzi/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath,"src/com/yuzi/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath,outputPath,model);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath =  new File(outputRootPath, ".gitignore").getAbsolutePath();;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath =  new File(outputRootPath, "README.md").getAbsolutePath();;
        // 生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("mar1");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerate(mainTemplateConfig);
    }
}