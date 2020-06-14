package com.softwork.freshmarket.mapper;

import com.softwork.freshmarket.entity.Food;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softwork.freshmarket.entity.FoodplusStore;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
public interface FoodMapper extends BaseMapper<Food> {

    @Select("select food.idfood , food.num as Xnum, name as FoodsName,type , imgurl as FoodsImg,max(instoredate) as laststoredate ,price as Xprice from food left join store on food.idfood = store.idfood group by idfood")
    public List<FoodplusStore> selectFoodplusStoreAll();

    @Select("select food.idfood , food.num as Xnum, name as FoodsName,type , imgurl as FoodsImg,max(instoredate) as laststoredate ,price as Xprice  from food left join store on food.idfood = store.idfood where name like CONCAT('%',#{name},'%') group by idfood")
    public List<FoodplusStore> selectFoodplusStorebyFoodName(String name);
}
