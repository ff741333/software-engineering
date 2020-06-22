package com.softwork.freshmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.DayTotal;
import com.softwork.freshmarket.entity.Store;
import com.softwork.freshmarket.service.VisualInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
    public class VisualController {

        @Autowired
        private VisualInterface visualInterface;

        /**
         * 每一天的访问用户量
         * @return
         */
        @RequestMapping("/daytotal")
        @ResponseBody
        public DayTotal getDayTotal(){
            return visualInterface.getDaytotal();

        }
    @RequestMapping("/selecttotal")
    @ResponseBody
    public DayTotal total(@RequestParam(value = "Id") String Id,@RequestParam(value = "date") String date1){
        DayTotal stores;
        String[] datearr = date1.split("~");
        if(Id.isEmpty()&&date1.isEmpty()) {//蔬菜和日期均为空则显示全部销量
            stores = visualInterface.getDaytotal();
        }
        else if(Id.isEmpty()&&!date1.isEmpty()){//蔬菜日期不为空则显示选定日期内的所有商品销量

            stores = visualInterface.total(datearr[0],datearr[1]);
        }else if(!Id.isEmpty()&&!date1.isEmpty()){//蔬菜和日期均不为空则显示选定日期内的选定商品销量
            stores = visualInterface.getotal(Id,datearr[0],datearr[1]);
        }else{//蔬菜不为空日期为空则显示该蔬菜的全部销量

            stores = visualInterface.gtotal(Id);
        }

        return stores;
    }
    }


