package com.softwork.freshmarket.service.impl;

import com.softwork.freshmarket.entity.DayTotal;
import com.softwork.freshmarket.entity.totalcol;
import com.softwork.freshmarket.mapper.VisualMapper;
import com.softwork.freshmarket.service.VisualInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisualInterfaceImpl implements VisualInterface {

@Autowired
private VisualMapper visualMapper;

    @Override
    public DayTotal getDaytotal() {
        List<totalcol> list =visualMapper.findAllFromTotalcol();
        List<String> name =new ArrayList<>();
        List<Integer> num =new ArrayList<>();
        for (totalcol totalcol : list) {
            name.add(totalcol.getName());
            num.add(totalcol.getNum());
        }
        DayTotal daytotal = new DayTotal();
        daytotal.setName(name);
        daytotal.setNum(num);
        return daytotal;
    }

    @Override
    public DayTotal total(String date1,String date2) {
        List<totalcol> list =visualMapper.findDateFromTotalcol(date1,date2);
        List<String> name =new ArrayList<>();
        List<Integer> num =new ArrayList<>();
        for (totalcol totalcol : list) {
            name.add(totalcol.getName());
            num.add(totalcol.getNum());
        }
        DayTotal daytotal = new DayTotal();
        daytotal.setName(name);
        daytotal.setNum(num);
        return daytotal;
    }

    @Override
    public DayTotal getotal(String id, String date1, String date2) {
        List<totalcol> list =visualMapper.findFoodFromTotalcol(id,date1,date2);
        List<String> name =new ArrayList<>();
        List<Integer> num =new ArrayList<>();
        for (totalcol totalcol : list) {
            name.add(totalcol.getName());
            num.add(totalcol.getNum());
        }
        DayTotal daytotal = new DayTotal();
        daytotal.setName(name);
        daytotal.setNum(num);
        return daytotal;
    }

    @Override
    public DayTotal gtotal(String id) {
        List<totalcol> list =visualMapper.findFromTotalcol(id);
        List<String> name =new ArrayList<>();
        List<Integer> num =new ArrayList<>();
        for (totalcol totalcol : list) {
            name.add(totalcol.getName());
            num.add(totalcol.getNum());
        }
        DayTotal daytotal = new DayTotal();
        daytotal.setName(name);
        daytotal.setNum(num);
        return daytotal;
    }
}

