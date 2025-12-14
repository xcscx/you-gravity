package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeMapper;
import com.itegg.yougravitybackend.model.dto.food.RecipeAddRequest;
import com.itegg.yougravitybackend.model.dto.food.RecipeUpdateRequest;
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

    @Override
    public Long addRecipe(RecipeAddRequest param) {
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        save(bean);
        return bean.getId();
    }

    @Override
    public boolean updateRecipe(RecipeUpdateRequest param) {
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        return updateById(bean);
    }

    @Override
    public boolean openRecipe(Long id) {
        Recipe recipe = getById(id);
        if(ObjectUtil.isNull(recipe)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未找到该菜谱");
        }
        recipe.setIsPublic(1);
        return updateById(recipe);
    }

    @Override
    public boolean privateRecipe(Long id) {
        Recipe recipe = getById(id);
        if(ObjectUtil.isNull(recipe)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未找到该菜谱");
        }
        recipe.setIsPublic(0);
        return updateById(recipe);
    }

}
