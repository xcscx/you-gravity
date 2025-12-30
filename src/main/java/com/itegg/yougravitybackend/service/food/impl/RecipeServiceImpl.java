package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeMapper;
import com.itegg.yougravitybackend.model.vo.food.*;
import com.itegg.yougravitybackend.model.entity.food.Recipe;
import com.itegg.yougravitybackend.service.food.RecipeIngredientService;
import com.itegg.yougravitybackend.service.food.RecipeService;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // 保存菜谱基本信息
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        save(bean);
        // 保存菜谱食材信息
        List<RecIngAddParam> recIngList = param.getRecIngList();
        if(ObjectUtil.isNotEmpty(recIngList)) {
            Integer i = recipeIngredientService.batchAdd(bean.getId(), recIngList);
            if(i != recIngList.size()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "保存食材信息失败");
            }
        }
        // 保存菜谱步骤信息
        List<RecStepAddParam> recStepList = param.getRecStepList();
        if(ObjectUtil.isNotEmpty(recStepList)) {
            Integer i = recipeStepService.batchAddStep(bean.getId(), recStepList);
            if(i != recStepList.size()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "保存步骤信息失败");
            }
        }
        return bean.getId();
    }

    @Override
    public Long saveRecipe(RecipeUpdateParam param) {
        log.info("=========> /food/recipe/save result={}", JSONUtil.toJsonStr(param));
        // 修改菜谱基本信息
        Recipe bean = BeanUtil.toBean(param, Recipe.class);
        save(bean);
        // 修改菜谱关联食材信息
        List<RecIngUpdateParam> recIngList = param.getRecIngList();
        if(ObjectUtil.isNotEmpty(recIngList)) {
            Integer i = recipeIngredientService.batchUpdate(bean.getId(), recIngList);
            if(i != recIngList.size()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "保存食材信息失败");
            }
        }
        // 修改菜谱步骤信息
        List<RecStepUpdateParam> recStepList = param.getRecStepList();
        if(ObjectUtil.isNotEmpty(recStepList)) {
            Integer i = recipeStepService.batchUpdateStep(bean.getId(), recStepList);
            if(i != recStepList.size()) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "保存步骤信息失败");
            }
        }
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

    @Override
    public RecipeVO getRecipe(Long id) {
        Recipe recipe = getById(id);
        if(ObjectUtil.isNull(recipe)) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "未找到该菜谱");
        }
        RecipeVO vo = BeanUtil.toBean(recipe, RecipeVO.class);
        List<RecStepVO> stepList = recipeStepService.getByRecipeId(id);
        vo.setStepList(stepList);
        List<IngredientVO> ingreList = recipeIngredientService.getByRecipeId(id);
        vo.setIngredientList(ingreList);
        return vo;
    }

}
