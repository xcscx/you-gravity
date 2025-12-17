package com.itegg.yougravitybackend.controller.food;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.itegg.yougravitybackend.common.IdCondition;
import com.itegg.yougravitybackend.common.Result;
import com.itegg.yougravitybackend.common.ResultUtils;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.model.dto.food.IngredientAddRequest;
import com.itegg.yougravitybackend.model.dto.food.IngredientUpdateRequest;
import com.itegg.yougravitybackend.service.food.IngredientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 食材 Controller层
 * @author ITegg
 */
@RestController
@RequestMapping("/food/ingredient")
@Slf4j
public class IngredientController {

    @Resource
    private IngredientService ingredientService;

    /**
     * 新增食材
     */
    @PostMapping("/add")
    public Result<Long> add(@RequestBody IngredientAddRequest param) {
        log.info("=========> /food/ingredient/add param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(ingredientService.addIngredient(param));
    }

    /**
     * 更新食材
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody IngredientUpdateRequest param) {
        log.info("=========> /food/ingredient/update param={}", JSONUtil.toJsonStr(param));
        return ResultUtils.ok(ingredientService.updateIngredient(param));
    }

    /**
     * 食材审核通过
     */
    @PostMapping("/review-approve")
    public Result<Boolean> reviewApprove(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/ingredient/review-approve param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(ingredientService.reviewApprove(idCondition.getId()));
    }

    /**
     * 食材审核拒绝
     */
    @PostMapping("/review-reject")
    public Result<Boolean> reviewReject(@RequestBody IdCondition idCondition) {
        log.info("=========> /food/ingredient/review-reject param={}", JSONUtil.toJsonStr(idCondition));
        if(ObjectUtil.isNull(idCondition) || idCondition.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return ResultUtils.ok(ingredientService.reviewReject(idCondition.getId()));
    }


    /**
     * 查询食材
     */
    @PostMapping("/search")
    public Result<String> search() {

        return ResultUtils.ok("ok");
    }

}
