package com.yupi.web.service;

import com.yupi.web.model.entity.Generator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class GeneratorServiceTest {

    @Resource
    private GeneratorService generatorService;

    @Test
    public void testInsert(){
        Generator generator = generatorService.getById(7L);
        for(int i=0;i<100000;i++){
            generator.setId(null);
            generatorService.save(generator);
        }

    }

}















