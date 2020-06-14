package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.*;
import com.softwork.freshmarket.mapper.FoodMapper;
import com.softwork.freshmarket.service.ICarService;
import com.softwork.freshmarket.service.IFoodService;
import com.softwork.freshmarket.service.IStoreService;
import javafx.collections.transformation.FilteredList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@RestController
public class FoodController {
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IStoreService storeService;
    @Autowired
    private ICarService carService;


    @RequestMapping("/addfood")
    public String addfood(@RequestParam(value = "name") String name,
                          @RequestParam(value = "type") String type,
                          @RequestParam(value = "price") Float price){
        Food food =new Food();
        food.setName(name);
        food.setNum(0);
        food.setType(type);
        food.setPrice(price);
        if(foodService.save(food))
            return "1";
        else return "0";
    }

    @RequestMapping("/deletefood")
    public String deletefood(@RequestParam("Id") String id){
        QueryWrapper<Food> foodQueryWrapper = new QueryWrapper<Food>();
        QueryWrapper<Store> storeQueryWrapper = new QueryWrapper<Store>();
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<Car>();
        foodQueryWrapper.eq("idfood",id);
        storeQueryWrapper.eq("idfood",id);
        carQueryWrapper.eq("idfood",id);
        foodService.removeById(id);
        storeService.remove(storeQueryWrapper);
        carService.remove(carQueryWrapper);
        if(foodService.count(foodQueryWrapper)==0&&storeService.count(storeQueryWrapper)==0&&carService.count(carQueryWrapper)==0)
            return "1";
        else  return "0";
    }

    @RequestMapping("/showfood2")
    public LayuiTypeJson<FoodplusStore>showfood(HttpServletRequest httpServletRequest){
        List<FoodplusStore> foodplusStores;
        String FoodName = null;
        FoodName = httpServletRequest.getParameter("key[FoodName]");
        if("".equals(FoodName)||FoodName==null)
            foodplusStores=foodService.selectFoodplusStoreAll();
        else foodplusStores=foodService.selectFoodplusStorebyFoodName(FoodName.trim()   );
    //    foodplusStores.forEach(FoodplusStore::doFoodID);
        LayuiTypeJson<FoodplusStore> layuiTypeJson = new LayuiTypeJson<>();
        layuiTypeJson.setCount(foodplusStores.size());
        layuiTypeJson.setCode(0);
        layuiTypeJson.setMsg("");
        layuiTypeJson.setData(foodplusStores);
        return layuiTypeJson;
    }

    @RequestMapping("/showfood")
    public List<Food> showfood(){
        List<Food> foods = foodService.list();
        return foods;
    }



}

