package com.yupi.maker.template.model;

import com.yupi.maker.meta.Meta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description；
 * @author:mar1
 * @data:2024/05/24
 **/
@Data
public class TemplateMakerFileConfig {

    private List<FileInfoConfig>files;

    private FileGroupConfig fileGroupConfig;

    @NoArgsConstructor
    @Data
    public static class  FileInfoConfig{
        /**
         * 文件（目录）路径
         */
        private String path;

        /**
         * 控制单个文件是否生成
         */
        private String condition;

        /**
         * 文件过滤配置
         */
        private List<FileFilterConfig> filterConfigList;
    }

    @Data
    public static class FileGroupConfig{

        private String condition;

        private String groupKey;

        private String groupName;
    }
}
