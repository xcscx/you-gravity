package com.itegg.yougravitybackend.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.food.IngredientVO;
import com.itegg.yougravitybackend.model.vo.food.RecIngAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecIngUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.RecipeIngredient;

import java.util.List;

/**
 * 菜谱食材用量 Service层
 * @author ITegg
 */
public interface RecipeIngredientService extends IService<RecipeIngredient> {

    /**
     * 添加关联关系
     * @return 新增的ID
     */
    Long insert(RecIngAddParam param);

    /**
     * 修改关联关系
     * @return 是否成功
     */
    boolean update(RecIngUpdateParam param);

    /**
     * 删除关联关系
     * @return 是否成功
     */
    boolean delete(Long id);

    /**
     * 批量添加关联关系
     * @return 新增的数量
     */
    Integer batchAdd(Long recipeId, List<RecIngAddParam> params);

    /**
     * 批量修改关联关系
     * @return 修改的数量
     */
    Integer batchUpdate(Long recipeId, List<RecIngUpdateParam> params);

    /**
     * 依据菜谱id获取食材列表
     * @return 食材列表
     */
    List<IngredientVO> getByRecipeId(Long recipeId);

}
