package com.itegg.yougravitybackend.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.vo.food.IngredientAddParam;
import com.itegg.yougravitybackend.model.vo.food.IngredientUpdateParam;
import com.itegg.yougravitybackend.model.entity.food.Ingredient;

/**
 * 食材基本信息 Service层
 * @author ITegg
 */
public interface IngredientService extends IService<Ingredient> {

    /**
     * 添加食材
     * @param param 食材信息
     * @return 新增的食材ID
     */
    Long addIngredient(IngredientAddParam param);

    /**
     * 修改食材信息
     * @param param 食材信息
     * @return 修改成功的行数
     */
    boolean updateIngredient(IngredientUpdateParam param);

    /**
     * 食材审核通过
     * @param id 食材ID
     * @return 审核结果
     */
    boolean reviewApprove(Long id);

    /**
     * 食材审核拒绝
     * @param id 食材ID
     * @return 审核结果
     */
    boolean reviewReject(Long id);

}
