package com.yupi.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yupi.web.model.entity.Generator;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description 针对表【generator(代码生成器)】的数据库操作Mapper
 */
public interface GeneratorMapper extends BaseMapper<Generator> {
    @Select("SELECT id, distPath FROM generator WHERE isDelete = 1")
    List<Generator> listDeletedGenerator();
}