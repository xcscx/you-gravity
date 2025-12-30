package com.itegg.yougravitybackend.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.food.RecipeAddParam;
import com.itegg.yougravitybackend.model.vo.food.RecipeUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.Recipe;
import com.itegg.yougravitybackend.model.vo.food.RecipeVO;

/**
 * 菜谱 Service层
 * @author ITegg
 */
public interface RecipeService extends IService<Recipe> {

    /**
     * 添加菜谱
     * @param param 菜谱信息
     * @return 菜谱id
     */
    Long addRecipe(RecipeAddParam param);

    /**
     * 保存菜谱
     * @param param 菜谱信息
     * @return 菜谱id
     */
    Long saveRecipe(RecipeUpdateParam param);

    /**
     * 更新菜谱
     * @param param 菜谱信息
     * @return 更新关联数据行数
     */
    boolean updateRecipe(RecipeUpdateParam param);

    /**
     * 将菜谱设置为公开状态
     * @param id 菜谱id
     * @return 是否成功
     */
    boolean openRecipe(Long id);

    /**
     * 将菜谱设置为私密状态
     * @param id 菜谱id
     * @return 是否成功
     */
    boolean privateRecipe(Long id);

    /**
     * 获取菜谱
     * @param id 菜谱id
     * @return 菜谱信息
     */
    RecipeVO getRecipe(Long id);

}
