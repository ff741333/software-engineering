package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.Consumption;
import com.softwork.freshmarket.entity.Food;
import com.softwork.freshmarket.entity.Store;
import com.softwork.freshmarket.entity.User;
import com.softwork.freshmarket.service.IFoodService;
import com.softwork.freshmarket.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@RestController
public class StoreController {
    @Autowired
    private IStoreService storeService;
    @Autowired
    private IFoodService foodService;


    @RequestMapping("/selectstore")
    public List<Store> selectstore(@RequestParam(value = "Id") String Id,
                                   @RequestParam(value = "date") String date){
        QueryWrapper<Store> wrapper = new QueryWrapper<>();
        List<Store> stores;
        if(Id.isEmpty()&&date.isEmpty()) {
            stores = storeService.list();
            for (Store s:stores
                 ) {
                s.setName(foodService.getById(s.getIdfood()).getName());
            }
        }
        else if(Id.isEmpty()&&!date.isEmpty()) {
            String[] datearr = date.split("~");
            wrapper.between("submitdate",datearr[0].trim()+" 00:00:00",datearr[1].trim()+" 23.59.59");
            stores = storeService.list(wrapper);
            for (Store s:stores
            ) {
                s.setName(foodService.getById(s.getIdfood()).getName());
            }
        }
        else if(!Id.isEmpty()&&date.isEmpty()){
            wrapper.eq("idFood",Id);
            stores = storeService.list(wrapper);
            for (Store s:stores
            ) {
                s.setName(foodService.getById(s.getIdfood()).getName());
            }
        }
        else {
            String[] datearr = date.split("~");
            wrapper.between("submitdate",datearr[0].trim()+" 00:00:00",datearr[1].trim()+" 23.59.59");
            wrapper.eq("idFood",Id);
            stores = storeService.list(wrapper);
            for (Store s:stores
            ) {
                s.setName(foodService.getById(s.getIdfood()).getName());
            }
        }
        return stores;
    }

    @RequestMapping("/store")
    public String store(@RequestParam(value = "Id") Integer id,
                        @RequestParam(value = "num") Integer num,
                        @RequestParam(value = "price") Float price,
                        @RequestParam(value = "time") String time,
                        @RequestParam(value = "supplier") String supplier,
                        HttpSession httpSession){
        Food food = foodService.getById(id);
        food.setNum(food.getNum()+num);
        foodService.updateById(food);

        User user = (User) httpSession.getAttribute("user");

        Store store = new Store();
        store.setIdfood(id);
        store.setNum(num);
        store.setStoreprice(price);
        store.setSubmitdate(LocalDateTime.now());
        store.setInstoredate(LocalDate.parse(time));
        store.setSupplier(supplier);
        store.setStorename(user.getIdUser());

        if(storeService.save(store)) return "1";
        else return "0";
    }

}

