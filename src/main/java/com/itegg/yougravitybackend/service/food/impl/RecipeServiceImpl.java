package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.RecipeMapper;
import com.itegg.yougravitybackend.model.entity.food.Recipe;
import com.itegg.yougravitybackend.service.food.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 菜谱Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe>
        implements RecipeService {


}
