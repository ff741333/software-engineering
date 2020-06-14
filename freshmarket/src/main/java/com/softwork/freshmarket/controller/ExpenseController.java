package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.Expense;
import com.softwork.freshmarket.service.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
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
public class ExpenseController {
    @Autowired
    private IExpenseService expenseService;

    @RequestMapping("/selectorder")
    public List<Expense> selectorder(@RequestParam(value = "date") String date,
                                     @RequestParam(value = "user") String user){
        QueryWrapper<Expense> wrapper = new QueryWrapper<Expense>();
        wrapper.eq("idUser",user);
        if(!date.isEmpty())
            wrapper.between("date",date+" 00:00:00",date+" 23:59:59");
        return expenseService.list(wrapper);


    }

}

