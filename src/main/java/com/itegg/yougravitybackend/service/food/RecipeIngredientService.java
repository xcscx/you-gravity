package com.itegg.yougravitybackend.service.food;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itegg.yougravitybackend.model.dto.food.RecIngAddRequest;
import com.itegg.yougravitybackend.model.dto.food.RecIngUpdateRequest;
import com.itegg.yougravitybackend.model.entity.food.RecipeIngredient;

/**
 * 菜谱食材用量 Service层
 * @author ITegg
 */
public interface RecipeIngredientService extends IService<RecipeIngredient> {

    /**
     * 添加关联关系
     * @return 新增的ID
     */
    Long insert(RecIngAddRequest param);

    /**
     * 修改关联关系
     * @return 是否成功
     */
    boolean update(RecIngUpdateRequest param);

    /**
     * 删除关联关系
     * @return 是否成功
     */
    boolean delete(Long id);

}
