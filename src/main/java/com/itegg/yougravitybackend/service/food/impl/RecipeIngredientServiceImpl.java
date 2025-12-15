package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.RecipeIngredientMapper;
import com.itegg.yougravitybackend.model.entity.food.RecipeIngredient;
import com.itegg.yougravitybackend.service.food.RecipeIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 食材基本信息 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeIngredientServiceImpl extends ServiceImpl<RecipeIngredientMapper, RecipeIngredient>
        implements RecipeIngredientService {


}
