INSERT INTO `my_db`.`generator` (
    `id`,
    `name`,
    `description`,
    `basePackage`,
    `version`,
    `author`,
    `tags`,
    `picture`,
    `fileConfig`,
    `modelConfig`,
    `distPath`,
    `status`,
    `userId`,
    `createTime`,
    `updateTime`,
    `isDelete`
)
VALUES
    (
        8,
        'ACM 模板项目',
        'ACM 模板项目生成器',
        'com.yupi',
        '1.0',
        '小张',
        '[\"Java\"]',
        'https://pic.yupi.icu/1/_r0_c1851-bf115939332e.jpg',
        '{\"type\": \"dir\", \"files\": [{\"type\": \"file\", \"inputPath\": \"src/com/yupi/acm/MainTemplate.java.ftl\", \"outputPath\": \"src/com/yupi/acm/MainTemplate.java\", \"generateType\": \"dynamic\"}, {\"type\": \"group\", \"files\": [{\"type\": \"file\", \"condition\": \"needGit\", \"inputPath\": \".gitignore\", \"outputPath\": \".gitignore\", \"generateType\": \"static\"}, {\"type\": \"file\", \"inputPath\": \"README.md\", \"outputPath\": \"README.md\", \"generateType\": \"static\"}], \"groupKey\": \"acmTemplate\", \"condition\": \"needGit\", \"groupName\": \"gitignore 和 README.md\"}], \"inputRootPath\": \".source/acm-template-pro\", \"outputRootPath\": \"generated\", \"sourceRootPath\": \"F:/java/code/yuzi-generator/yuzi-generator-demo-projects/acm-template-pro\"}',
        '{\"models\": [{\"abbr\": \"n\", \"type\": \"Boolean\", \"fieldName\": \"needGit\", \"description\": \"是否生成.gitignore文件\", \"defaultValue\": true}, {\"abbr\": \"l\", \"type\": \"Boolean\", \"fieldName\": \"loop\", \"description\": \"是否生成循环\", \"defaultValue\": false}, {\"type\": \"MainTemplate\", \"models\": [{\"abbr\": \"a\", \"type\": \"String\", \"fieldName\": \"author\", \"description\": \"作者注释\", \"defaultValue\": \"yupi\"}, {\"abbr\": \"o\", \"type\": \"String\", \"fieldName\": \"outputText\", \"description\": \"输出信息\", \"defaultValue\": \"sum = \"}], \"groupKey\": \"mainTemplate\", \"condition\": \"loop\", \"groupName\": \"核心模板\", \"description\": \"核心模板参数\"}]}',
        NULL,
        0,
        1,
        '2024-02-04 21:57:59',
        '2024-02-11 18:53:05',
        0
    );