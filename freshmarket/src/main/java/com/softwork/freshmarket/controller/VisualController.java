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
    public DayTotal total(@RequestParam(value = "date") String date1){
        DayTotal stores;
        if(date1.isEmpty()) {
            stores = visualInterface.getDaytotal();
        }
        else {
            String[] datearr = date1.split("~");
            stores = visualInterface.total(datearr[0],datearr[1]);
        }
        return stores;
    }
    }


