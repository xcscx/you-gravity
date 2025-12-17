package com.itegg.yougravitybackend.controller.food;

import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜谱步骤 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/food/recipe-step")
public class RecipeStepController {

    @Resource
    private RecipeStepService recipeStepService;

    /**
     * 健康检查
     */
    @GetMapping("/health")
    public Result<String> health() {
        return ResultUtils.ok("ok");
    }

}
