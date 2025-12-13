package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.FoodRecipeStepMapper;
import com.itegg.yougravitybackend.model.entity.food.FoodRecipeStep;
import com.itegg.yougravitybackend.service.food.FoodRecipeStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 菜谱步骤Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class FoodRecipeStepServiceImpl extends ServiceImpl<FoodRecipeStepMapper, FoodRecipeStep>
        implements FoodRecipeStepService {


}
