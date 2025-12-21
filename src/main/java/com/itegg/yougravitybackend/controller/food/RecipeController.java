package com.itegg.yougravitybackend.controller.food;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.food.RecipeAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecipeUpdateParam;
import com.itegg.yougravitybackend.service.food.RecipeService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 菜谱 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/food/recipe")
@Slf4j
public class RecipeController {

    @Resource
    private RecipeService recipeService;

    /**
     * 新增菜谱
     */
    @PostMapping("/add")
    public Result<Long> addRecipe(@RequestBody RecipeAddParam param) {
        log.info("=========> /food/recipe/add param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(recipeService.addRecipe(param));
    }

    /**
     * 保存菜谱草稿
     */
    @PostMapping("/save")
    public Result<Long> saveRecipe(@RequestBody RecipeAddParam param) {
        log.info("=========> /food/recipe/save param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(recipeService.saveRecipe(param));
    }

    /**
     * 更新菜谱
     */
    @PostMapping("/update")
    public Result<Boolean> updateRecipe(@RequestBody RecipeUpdateParam param) {
        log.info("=========> /food/recipe/update param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(recipeService.updateRecipe(param));
    }

    /**
     * 删除菜谱
     */
    @PostMapping("/delete")
    public Result<Boolean> deleteRecipe(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/recipe/delete param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(recipeService.removeById(idCondition.getId()));
    }

    /**
     * 公开菜谱
     */
    @PostMapping("/open")
    public Result<Boolean> openRecipe(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/recipe/open param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(recipeService.openRecipe(idCondition.getId()));
    }

    /**
     * 私密菜谱
     */
    @PostMapping("/private")
    public Result<Boolean> privateRecipe(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/recipe/private param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(recipeService.privateRecipe(idCondition.getId()));
    }

    /**
     * 查询我的菜谱
     */
    @PostMapping("/my")
    public Result<String> myRecipe() {

        return ResultUtils.ok("ok");
    }

    /**
     * 查询公开菜谱
     */
    @PostMapping("/all-open")
    public Result<String> allOpenRecipe() {

        return ResultUtils.ok("ok");
    }

}
