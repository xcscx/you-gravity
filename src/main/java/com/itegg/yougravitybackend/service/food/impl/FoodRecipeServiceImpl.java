package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.FoodRecipeMapper;
import com.itegg.yougravitybackend.model.entity.food.FoodRecipe;
import com.itegg.yougravitybackend.service.food.FoodRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 菜谱Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class FoodRecipeServiceImpl extends ServiceImpl<FoodRecipeMapper, FoodRecipe>
        implements FoodRecipeService {


}
