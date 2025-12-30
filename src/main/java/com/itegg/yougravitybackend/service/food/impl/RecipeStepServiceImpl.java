package com.itegg.yougravitybackend.service.food.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itegg.yougravitybackend.exception.BusinessException;
import com.itegg.yougravitybackend.exception.ErrorCode;
import com.itegg.yougravitybackend.mapper.food.RecipeStepMapper;
import com.itegg.yougravitybackend.model.entity.food.RecipeStep;
import com.itegg.yougravitybackend.model.vo.food.RecStepAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecStepUpdateParam;
import com.itegg.yougravitybackend.model.vo.food.RecStepVO;
import com.itegg.yougravitybackend.service.food.RecipeStepService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜谱步骤Service 实现类
 * @author ITegg
 */
@Service
@Slf4j
public class RecipeStepServiceImpl extends ServiceImpl<RecipeStepMapper, RecipeStep>
        implements RecipeStepService {

    @Override
    public Long addStep(RecStepAddParam param) {
        // 参数校验
        if (param == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        RecipeStep recipeStep = BeanUtil.copyProperties(param, RecipeStep.class);
        log.info("=========> 菜谱步骤添加参数: {}", JSONUtil.toJsonStr(param));
        boolean save = save(recipeStep);
        if (!save) {
            log.error("=========> 菜谱步骤添加失败: {}", JSONUtil.toJsonStr(param));
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "菜谱步骤添加失败");
        }
        return recipeStep.getId();
    }

    @Override
    public boolean updateStep(RecStepUpdateParam param) {
        // 校验参数
        if (param == null || param.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        RecipeStep byId = getById(param.getId());
        if (byId == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到菜谱步骤");
        }
        BeanUtil.copyProperties(param, byId);
        log.info("=========> 菜谱步骤修改参数: {}", JSONUtil.toJsonStr(byId));
        boolean update = updateById(byId);
        if (!update) {
            log.error("=========> 菜谱步骤修改失败: {}", JSONUtil.toJsonStr(param));
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "菜谱步骤修改失败");
        }
        return true;
    }

    @Override
    public Integer batchAddStep(Long recipeId, List<RecStepAddParam> params) {
        // 参数校验
        if (recipeId == null || params == null || params.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        for (RecStepAddParam param : params) {
            if (param == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
            }
            RecipeStep recipeStep = BeanUtil.copyProperties(param, RecipeStep.class);
            recipeStep.setRecipeId(recipeId);
            log.info("=========> 菜谱步骤添加参数: {}", JSONUtil.toJsonStr(param));
            boolean save = save(recipeStep);
            if (!save) {
                log.error("=========> 菜谱步骤添加失败: {}", JSONUtil.toJsonStr(param));
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "菜谱步骤添加失败");
            }
        }
        return params.size();
    }

    @Override
    public Integer batchUpdateStep(Long recipeId, List<RecStepUpdateParam> params) {
        // 参数校验
        if (recipeId == null || params == null || params.isEmpty()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        for (RecStepUpdateParam param : params) {
            RecipeStep byId = getById(param.getId());
            if (byId == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "无法找到菜谱步骤");
            }
            BeanUtil.copyProperties(param, byId);
            byId.setRecipeId(recipeId);
            log.info("=========> 菜谱步骤添加参数: {}", JSONUtil.toJsonStr(param));
            boolean save = save(byId);
            if (!save) {
                log.error("=========> 菜谱步骤添加失败: {}", JSONUtil.toJsonStr(param));
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "菜谱步骤添加失败");
            }
        }
        return params.size();
    }

    @Override
    public List<RecStepVO> getByRecipeId(Long recipeId) {
        // 参数校验
        if(recipeId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        List<RecipeStep> list = list(new QueryWrapper<RecipeStep>().eq("recipe_id", recipeId));
        return list.stream().map(item -> BeanUtil.copyProperties(item, RecStepVO.class)).toList();
    }

}
