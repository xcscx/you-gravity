package com.itegg.yougravitybackend.controller.food;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.vo.food.RecStepAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecStepUpdateParam;
import com.itegg.yougravitybackend.model.vo.food.RecStepVO;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜谱步骤 Controller层
 * @author ITegg
 */
@Slf4j
@RestController
@RequestMapping("/food/recipe-step")
public class RecipeStepController {

    @Resource
    private RecipeStepService recipeStepService;

    /**
     * 新增菜谱步骤
     */
    @PostMapping("/add")
    public Result<Long> addStep(@RequestBody RecStepAddParam param) {
        log.info("=========> /food/recipe-step/add param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(recipeStepService.addStep(param));
    }

    /**
     * 更新菜谱步骤
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody RecStepUpdateParam param) {
        log.info("=========> /food/recipe-step/update param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(recipeStepService.updateStep(param));
    }

    /**
     * 删除菜谱步骤
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/recipe-step/delete param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(recipeStepService.removeById(idCondition.getId()));
    }

    /**
     * 获取菜谱步骤
     */
    @PostMapping("/get")
    public Result<List<RecStepVO>> getByRecipeId(@RequestParam("recipeId") IdCondition idCondition) {
        log.info("=========> /food/recipe-step/get idCondition={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() == null || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(recipeStepService.getByRecipeId(idCondition.getId()));
    }


}
