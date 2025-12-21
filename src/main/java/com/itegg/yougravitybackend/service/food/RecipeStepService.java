package com.itegg.yougravitybackend.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.entity.food.RecipeStep;
import com.itegg.yougravitybackend.model.vo.food.RecStepAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecStepUpdateParam;

import java.util.List;

/**
 * 菜谱步骤 Service层
 * @author ITegg
 */
public interface RecipeStepService extends IService<RecipeStep> {

    /**
     * 添加菜谱步骤
     * @param param 菜谱步骤参数
     * @return 菜谱步骤id
     */
    Long addStep(RecStepAddParam param);

    /**
     * 修改菜谱步骤
     * @param param 菜谱步骤参数
     * @return 是否修改成功
     */
    boolean updateStep(RecStepUpdateParam param);

    /**
     * 批量添加菜谱步骤
     * @param recipeId 菜谱id
     * @param params 菜谱步骤参数列表
     * @return 添加的菜谱步骤数量
     */
    Integer batchAddStep(Long recipeId, List<RecStepAddParam> params);

    /**
     * 批量修改菜谱步骤
     * @param recipeId 菜谱id
     * @param params 菜谱步骤参数列表
     * @return 修改的菜谱步骤数量
     */
    Integer batchUpdateStep(Long recipeId, List<RecStepUpdateParam> params);

}
