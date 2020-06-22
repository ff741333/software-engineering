package com.softwork.freshmarket.service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.softwork.freshmarket.entity.DayTotal;

import java.util.List;

public interface VisualInterface {
    //List<DayTotal> getAll();
    public DayTotal getDaytotal();

    DayTotal total(String d1,String d2);
    DayTotal getotal(String id,String d1,String d2);
    DayTotal gtotal(String id);
}
