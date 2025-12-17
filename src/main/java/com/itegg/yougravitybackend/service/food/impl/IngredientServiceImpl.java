package com.itegg.yougravitybackend.service.food.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.mapper.food.IngredientMapper;
import com.itegg.yougravitybackend.model.dto.food.IngredientAddRequest;
import com.itegg.yougravitybackend.model.dto.food.IngredientUpdateRequest;
import com.itegg.yougravitybackend.model.entity.food.Ingredient;
import com.itegg.yougravitybackend.service.food.IngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 食材基本信息 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class IngredientServiceImpl extends ServiceImpl<IngredientMapper, Ingredient>
        implements IngredientService {

    @Override
    public Long addIngredient(IngredientAddRequest param) {
        return 1L;
    }

    @Override
    public boolean updateIngredient(IngredientUpdateRequest param) {
        return true;
    }

    @Override
    public boolean reviewIngredient(Long id) {
        return true;
    }

}
