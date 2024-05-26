package com.yupi.maker;

import com.yupi.maker.generator.main.GenerateTemplate;
import com.yupi.maker.generator.main.MainGenerator;
import com.yupi.maker.generator.main.ZipGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/
public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
//        MainGenerator mainGenerator = new MainGenerator();
        GenerateTemplate generateTemplate = new ZipGenerator();
        generateTemplate.doGenerate();

    }
}