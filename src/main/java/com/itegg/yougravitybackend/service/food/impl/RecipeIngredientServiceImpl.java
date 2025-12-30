package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeIngredientMapper;
import com.itegg.yougravitybackend.model.entity.food.Ingredient;
import com.itegg.yougravitybackend.model.vo.food.IngredientVO;
import com.itegg.yougravitybackend.model.vo.food.RecIngAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecIngUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.RecipeIngredient;
import com.itegg.yougravitybackend.service.food.IngredientService;
import com.itegg.yougravitybackend.service.food.RecipeIngredientService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 食材基本信息 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeIngredientServiceImpl extends ServiceImpl<RecipeIngredientMapper, RecipeIngredient>
        implements RecipeIngredientService {

    @Resource
    private IngredientService ingredientService;

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

    @Override
    public Integer batchAdd(Long recipeId, List<RecIngAddParam> params) {
        // 参数校验
        if (recipeId == null || params == null || params.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        for (RecIngAddParam param : params) {
            if (param == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
            }
            RecipeIngredient recipeIngredient = BeanUtil.copyProperties(param, RecipeIngredient.class);
            recipeIngredient.setRecipeId(recipeId);
            log.info("=========> 菜谱步骤添加参数: {}", JSONUtil.toJsonStr(param));
            boolean save = save(recipeIngredient);
            if (!save) {
                log.error("=========> 菜谱步骤添加失败: {}", JSONUtil.toJsonStr(param));
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "菜谱步骤添加失败");
            }
        }
        return params.size();
    }

    @Override
    public Integer batchUpdate(Long recipeId, List<RecIngUpdateParam> params) {
        // 校验参数
        if (recipeId == null || params == null || params.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        for (RecIngUpdateParam param : params) {
            RecipeIngredient byId = getById(param.getId());
            if (byId == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到菜谱关联食材");
            }
            BeanUtil.copyProperties(param, byId);
            byId.setRecipeId(recipeId);
            log.info("=========> 食材修改参数: {}", JSONUtil.toJsonStr(param));
            boolean save = save(byId);
            if (!save) {
                log.error("=========> 食材修改失败: {}", JSONUtil.toJsonStr(param));
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "食材修改失败");
            }
        }
        return params.size();
    }

    @Override
    public List<IngredientVO> getByRecipeId(Long recipeId) {
        // 校验参数
        if (recipeId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        // 获取菜谱食材
        List<RecipeIngredient> list = list(new QueryWrapper<RecipeIngredient>().eq("recipe_id", recipeId));
        List<Ingredient> ingredients = ingredientService.listByIds(list.stream().map(RecipeIngredient::getIngredientId).toList());
        return ingredients.stream().map(item -> BeanUtil.copyProperties(item, IngredientVO.class)).toList();
    }


}
