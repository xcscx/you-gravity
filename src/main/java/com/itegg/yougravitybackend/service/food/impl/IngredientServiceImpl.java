package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.IngredientMapper;
import com.itegg.yougravitybackend.model.dto.food.IngredientAddRequest;
import com.itegg.yougravitybackend.model.dto.food.IngredientUpdateRequest;
import com.itegg.yougravitybackend.model.entity.food.Ingredient;
import com.itegg.yougravitybackend.model.enums.StatusEnum;
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
        // 参数校验
        if (param == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        Ingredient ingredient = BeanUtil.copyProperties(param, Ingredient.class);
        // 默认为待审核/审核中
        ingredient.setStatus(StatusEnum.REVIEWING.getValue());
        log.info("=========> 食材添加参数: {}", JSONUtil.toJsonStr(param));
        boolean save = save(ingredient);
        if (!save) {
            log.error("=========> 食材添加失败: {}", JSONUtil.toJsonStr(param));
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "食材添加失败");
        }
        return ingredient.getId();
    }

    @Override
    public boolean updateIngredient(IngredientUpdateRequest param) {
        // 参数校验
        if (param == null || param.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        Ingredient ingredient = getById(param.getId());
        if (ingredient == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到食材");
        }
        BeanUtil.copyProperties(param, ingredient);
        // 更新后的食材设为待审核/审核中
        ingredient.setStatus(StatusEnum.REVIEWING.getValue());
        log.info("=========> 食材修改参数: {}", JSONUtil.toJsonStr(param));
        return updateIngredient(ingredient, "食材拒绝审批失败");
    }

    @Override
    public boolean reviewApprove(Long id) {
        Ingredient ingredient = getById(id);
        if (ingredient == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到食材");
        }
        ingredient.setStatus(StatusEnum.APPROVE.getValue());
        return updateIngredient(ingredient, "食材通过审批失败");
    }

    @Override
    public boolean reviewReject(Long id) {
        Ingredient ingredient = getById(id);
        if (ingredient == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到食材");
        }
        ingredient.setStatus(StatusEnum.REFUSE.getValue());
        return updateIngredient(ingredient, "食材拒绝审批失败");
    }



    /**
     * --- private ---
     */

    /**
     * 更改食材状态并保存
     *
     * @param ingredient 食材实体
     * @param errorMsg   失败提示消息
     * @return 是否成功
     */
    private boolean updateIngredient(Ingredient ingredient, String errorMsg) {
        boolean update = updateById(ingredient);
        if (!update) {
            log.error("=========> {}: 食品id={}", ingredient.getId());
            throw new BusinessException(ErrorCode.OPERATION_ERROR, errorMsg);
        }
        return true;
    }

}
