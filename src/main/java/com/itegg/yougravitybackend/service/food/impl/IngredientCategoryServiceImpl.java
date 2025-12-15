package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.IngredientCategoryMapper;
import com.itegg.yougravitybackend.model.entity.food.IngredientCategory;
import com.itegg.yougravitybackend.service.food.IngredientCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 食材种类 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class IngredientCategoryServiceImpl extends ServiceImpl<IngredientCategoryMapper, IngredientCategory>
        implements IngredientCategoryService {


}
