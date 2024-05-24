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
        private String path;

        private List<FileFilterConfig> filterConfigsList;
    }

    @Data
    public static class  FileGroupConfig{

        private String condition;

        private String groupKey;

        private String groupName;
    }
}
