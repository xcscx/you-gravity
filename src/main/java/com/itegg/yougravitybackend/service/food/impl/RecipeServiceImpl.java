package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeMapper;
import com.itegg.yougravitybackend.model.vo.food.RecipeAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecipeUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.Recipe;
import com.itegg.yougravitybackend.service.food.RecipeIngredientService;
import com.itegg.yougravitybackend.service.food.RecipeService;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import jakarta.annotation.Resource;
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

    @Resource
    private RecipeStepService recipeStepService;
    @Resource
    private RecipeIngredientService recipeIngredientService;

    @Override
    public Long addRecipe(RecipeAddParam param) {
        log.info("=========> /food/recipe/add result={}", JSONUtil.toJsonStr(param));
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        save(bean);

        return bean.getId();
    }

    @Override
    public Long saveRecipe(RecipeAddParam param) {
        log.info("=========> /food/recipe/save result={}", JSONUtil.toJsonStr(param));
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        save(bean);
        return bean.getId();
    }

    @Override
    public boolean updateRecipe(RecipeUpdateParam param) {
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
