package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.Food;
import com.softwork.freshmarket.entity.FoodplusStore;
import com.softwork.freshmarket.mapper.FoodMapper;
import com.softwork.freshmarket.service.IFoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {

    @Override
    public List<FoodplusStore> selectFoodplusStoreAll() {
        return this.baseMapper.selectFoodplusStoreAll();
    }

    @Override
    public List<FoodplusStore> selectFoodplusStorebyFoodName(String name) {
        return this.baseMapper.selectFoodplusStorebyFoodName(name);
    }
}
