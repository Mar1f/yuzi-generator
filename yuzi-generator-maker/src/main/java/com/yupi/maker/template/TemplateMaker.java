package com.yupi.maker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.enums.FileGenerateTypeEnum;
import com.yupi.maker.meta.enums.FileTypeEnum;
import freemarker.cache.StringTemplateLoader;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/
public class TemplateMaker {
    /**
     * 制作模板
     * @param newMeta
     * @param originProjectPath
     * @param inputFilePathList
     * @param modelInfo
     * @param searchStr
     * @param id
     * @return
     */
    public static long makeTemplate(Meta newMeta,String originProjectPath,List<String> inputFilePathList,Meta.ModelConfig.ModelInfo modelInfo,String searchStr,Long id){
        if (id == null) {
            id = IdUtil.getSnowflakeNextId();
        }

         //复制目录
        String projectPath = System.getProperty("user.dir");
        String tempDirPath = projectPath + File.separator + ".temp";
        String templatePath = tempDirPath + File.separator + id;
        // 是否为首次制作模板
        // 目录不存在，则是首次制作
        if (!FileUtil.exist(templatePath)) {
            FileUtil.mkdir(templatePath);
            FileUtil.copy(originProjectPath, templatePath, true);
        }


        // 一、输入信息
        // 2. 输入文件信息
        String sourceRootPath = templatePath + File.separator + FileUtil.getLastPathEle(Paths.get(originProjectPath)).toString();
        // 转义
        sourceRootPath = sourceRootPath.replaceAll("\\\\","/");
        //遍历输入文件
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>();
        for(String inputFilePath : inputFilePathList){
            String inputFileAbsolutePath = sourceRootPath+ File.separator + inputFilePath;
            if(FileUtil.isDirectory(inputFileAbsolutePath)) {
                List<File> fileList = FileUtil.loopFiles(inputFileAbsolutePath);
                for(File file: fileList){
                    Meta.FileConfig.FileInfo fileInfo = makeFileTemplate(modelInfo, searchStr, sourceRootPath,file);
                    newFileInfoList.add(fileInfo);
                }
            }
            else{
                Meta.FileConfig.FileInfo fileInfo = makeFileTemplate(modelInfo, searchStr, sourceRootPath,new File(inputFileAbsolutePath));
                newFileInfoList.add(fileInfo);
            }
        }


        // 三、生成配置文件
        String metaOutputPath = sourceRootPath + File.separator + "meta.json";

        //已有meta文件，不是第一次制作，则在meta基础上进行修改
        if(FileUtil.exist(metaOutputPath)){
            Meta oldMeta = JSONUtil.toBean(FileUtil.readUtf8String(metaOutputPath),Meta.class);
            // 1. 追加配置参数

            List<Meta.FileConfig.FileInfo> fileInfoList = oldMeta.getFileConfig().getFiles();
            fileInfoList.addAll(newFileInfoList);

            List<Meta.ModelConfig.ModelInfo> modelInfoList = oldMeta.getModelConfig().getModels();
            modelInfoList.add(modelInfo);

            // 配置去重
            oldMeta.getFileConfig().setFiles(distinctFiles(fileInfoList));
            oldMeta.getModelConfig().setModels(distinctModels(modelInfoList));

            //2. 输出元信息
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(oldMeta),metaOutputPath);
        }else{

            // 1. 构建配置参数
            Meta.FileConfig fileConfig = new Meta.FileConfig();
            newMeta.setFileConfig(fileConfig);
            fileConfig.setSourceRootPath(sourceRootPath);
            List<Meta.FileConfig.FileInfo> fileInfoList = new ArrayList<>();
            fileConfig.setFiles(fileInfoList);

            fileInfoList.addAll(newFileInfoList);

            Meta.ModelConfig modelConfig = new Meta.ModelConfig();
            newMeta.setModelConfig(modelConfig);
            List<Meta.ModelConfig.ModelInfo> modelInfoList = new ArrayList<>();
            modelConfig.setModels(modelInfoList);
            modelInfoList.add(modelInfo);

            //2. 输出元信息
            FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(newMeta),metaOutputPath);
        }
        return id;
    }

    private static Meta.FileConfig.FileInfo makeFileTemplate(Meta.ModelConfig.ModelInfo modelInfo, String searchStr, String sourceRootPath,File inputFile) {
        String fileInputAbsolutePath = inputFile.getAbsolutePath();
        //要挖坑的文件  一定是相对路径
        // win系统进行转义
        fileInputAbsolutePath = fileInputAbsolutePath.replaceAll("\\\\","/");
        String fileInputPath = fileInputAbsolutePath.replace(sourceRootPath+"/","");
        String fileOutputPath = fileInputPath + ".ftl";

        String fileOutputAbsolutePath = fileInputAbsolutePath + ".ftl";
        // 二、使用字符串替换，生成模板文件
        String fileContent;
        //如果已经有模板文件，表示不是第一次制作，则在原有的模板文件的基础上再挖坑
        if(FileUtil.exist(fileOutputAbsolutePath)){
            fileContent = FileUtil.readUtf8String(fileOutputAbsolutePath);
        }else{
            fileContent = FileUtil.readUtf8String(fileInputAbsolutePath);
        }
        String replacement = String.format("${%s]", modelInfo.getFieldName());
        String newFileContent = StrUtil.replace(fileContent, searchStr,replacement);

        //文件配置信息
        Meta.FileConfig.FileInfo fileInfo = new Meta.FileConfig.FileInfo();
        fileInfo.setInputPath(fileInputPath);
        fileInfo.setOutputPath(fileOutputPath);
        fileInfo.setType(FileTypeEnum.FILE.getValue());


        // 如果和源文件一直，没有挖坑，静态生成
        if(newFileContent.equals(fileContent)){
            //输出路径-= 输入路径
            fileInfo.setOutputPath(fileInputPath);
            fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
        }else{
            //输出模板文件
            fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
            FileUtil.writeUtf8String(newFileContent,fileOutputAbsolutePath);
        }

        return fileInfo;
    }


    public static void main(String[] args) {
        // 输入文件信息

        Meta meta = new Meta();
        meta.setName("acm-template-generator");
        meta.setDescription("ACM 示例模板生成器");

        //指定原始项目 路径、
        String projectPath = System.getProperty("user.dir");
        String originProjectPath = new File(projectPath).getParent() + File.separator + "yuzi-generator-demo-projects/springboot-init";
        String fileInputPath1 = "src/main/java/com/yupi/springbootinit/common";
        String fileInputPath2 = "src/main/java/com/yupi/springbootinit/controller";
        List<String> inputFilePathList = Arrays.asList(fileInputPath1,fileInputPath2);

        // 3. 输入模型参数信息
//        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
//        modelInfo.setFieldName("outputText");
//        modelInfo.setType("String");
//        modelInfo.setDefaultValue("sum = ");

        // 输入模型参数信息（第二次）
        Meta.ModelConfig.ModelInfo modelInfo = new Meta.ModelConfig.ModelInfo();
        modelInfo.setFieldName("className");
        modelInfo.setType("String");
        //替换变量
//        String searchStr = "Sum: ";
        String searchStr = "BaseResponse";
        long id = TemplateMaker.makeTemplate(meta,originProjectPath,inputFilePathList,modelInfo,searchStr,1793985874773155840L);
        System.out.println(id);
    }

    /**
     * 文件去重
     * @param fileInfoList
     * @return
     */
    private static List<Meta.FileConfig.FileInfo> distinctFiles(List<Meta.FileConfig.FileInfo> fileInfoList) {
        List<Meta.FileConfig.FileInfo> newFileInfoList = new ArrayList<>(fileInfoList.stream()
                .collect(
                        Collectors.toMap(Meta.FileConfig.FileInfo::getInputPath, o -> o, (e, r) -> r)
                ).values());
        return newFileInfoList;
    }

    /**
     * 模型去重
     * @param modelInfoList
     * @return
     */
    private static List<Meta.ModelConfig.ModelInfo> distinctModels(List<Meta.ModelConfig.ModelInfo> modelInfoList) {
        List<Meta.ModelConfig.ModelInfo> newModelInfoList = new ArrayList<>(modelInfoList.stream()
                .collect(
                        Collectors.toMap(Meta.ModelConfig.ModelInfo::getFieldName, o -> o, (e, r) -> r)
                ).values());
        return newModelInfoList;
    }
}
