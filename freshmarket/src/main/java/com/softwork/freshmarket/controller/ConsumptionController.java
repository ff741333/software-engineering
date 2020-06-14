package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.Car;
import com.softwork.freshmarket.entity.Consumption;
import com.softwork.freshmarket.entity.Expense;
import com.softwork.freshmarket.entity.User;
import com.softwork.freshmarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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
public class ConsumptionController {
    @Autowired
    private IConsumptionService consumptionService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private IExpenseService expenseService;
    @Autowired
    private ICarService carService;

    @RequestMapping("/selectmainorder")
    public List<Consumption> getshopcar(@RequestParam(value = "idexpense") String idexpense ){
        if(!idexpense.isEmpty()){
            QueryWrapper<Consumption> wrapper= new QueryWrapper<Consumption>();
            wrapper.eq("idexpense",idexpense);
            List<Consumption> consumptions= consumptionService.list(wrapper);
            for (Consumption c:consumptions
                 ) {
                c.setNameFood(foodService.getById(c.getIdFood()).getName());
            }
            return consumptions;
        }
        else return null;

    }

    @RequestMapping("/sendorder")
    public String sendorder(HttpSession httpSession){
        User user =(User) httpSession.getAttribute("user");
        Expense expense = new Expense();
        expense.setIdUser(user.getIdUser());
        expense.setStatus("配送中");
        expense.setIdAdmin("1");
        LocalDateTime now = LocalDateTime.now();
        expense.setDate(now);
        expenseService.save(expense);
        QueryWrapper<Car> carQueryWrapper =new QueryWrapper<Car>();
        carQueryWrapper.eq("idUser",user.getIdUser());
        List<Car> cars = carService.list(carQueryWrapper);
        Float sumprice = new Float(0);
        Consumption consumption = new Consumption();
        consumption.setIdexpense(expense.getIdexpense());
        for (Car c:cars
             ) {
            sumprice+=c.getNumfood()*foodService.getById(c.getIdfood()).getPrice();
            consumption.setIdFood(c.getIdfood());
            consumption.setNumFood(c.getNumfood());
            consumption.setIdAdmin(1);
            consumption.setIdUser(user.getIdUser());
            consumption.setDate(now);
            consumptionService.save(consumption);
        }
        expense.setCost(sumprice);
        expenseService.updateById(expense);
        if(carService.remove(carQueryWrapper)) return "1";
        return "0";

    }
}

