package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeIngredientMapper;
import com.itegg.yougravitybackend.model.vo.food.RecIngAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecIngUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.RecipeIngredient;
import com.itegg.yougravitybackend.service.food.RecipeIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 食材基本信息 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeIngredientServiceImpl extends ServiceImpl<RecipeIngredientMapper, RecipeIngredient>
        implements RecipeIngredientService {

    @Override
    public Long insert(RecIngAddParam param) {
        // 校验参数
        if (param == null || param.getRecipeId() == null || param.getIngredientId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        log.info("=========> 食材添加参数: {}", JSONUtil.toJsonStr(param));
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        boolean save = save(recipeIngredient);
        if (!save) {
            log.error("=========> 食材添加失败: {}", JSONUtil.toJsonStr(param));
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "食材添加失败");
        }
        return recipeIngredient.getId();
    }

    @Override
    public boolean update(RecIngUpdateParam param) {
        // 校验参数
        if (param == null || param.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        RecipeIngredient recipeIngredient = getById(param.getId());
        if (recipeIngredient == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到菜谱关联食材");
        }
        BeanUtil.copyProperties(param, recipeIngredient);
        log.info("=========> 食材修改参数: {}", JSONUtil.toJsonStr(param));
        return updateById(recipeIngredient);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        log.info("=========> 食材删除参数: {}", id);
        return removeById(id);
    }

}
