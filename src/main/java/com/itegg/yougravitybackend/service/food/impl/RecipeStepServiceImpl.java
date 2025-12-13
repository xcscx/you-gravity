package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.RecipeStepMapper;
import com.itegg.yougravitybackend.model.entity.food.RecipeStep;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 菜谱步骤Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeStepServiceImpl extends ServiceImpl<RecipeStepMapper, RecipeStep>
        implements RecipeStepService {


}
