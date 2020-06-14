package com.softwork.freshmarket.service;

import com.softwork.freshmarket.entity.Food;
import com.baomidou.mybatisplus.extension.service.IService;
import com.softwork.freshmarket.entity.FoodplusStore;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
public interface IFoodService extends IService<Food> {

    public List<FoodplusStore> selectFoodplusStoreAll();

    public List<FoodplusStore> selectFoodplusStorebyFoodName(String name);


}
