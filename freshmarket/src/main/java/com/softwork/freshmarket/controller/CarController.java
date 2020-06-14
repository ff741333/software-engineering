package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.softwork.freshmarket.entity.*;
import com.softwork.freshmarket.service.ICarService;
import com.softwork.freshmarket.service.IFoodService;
import com.softwork.freshmarket.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@RestController
public class CarController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IFoodService foodService;

    @RequestMapping("/addcar")
    public String addcar(@RequestParam(value = "Id") Integer idfood,
                         @RequestParam(value = "num") Integer num,
                         HttpSession httpSession){
                Car car =new Car();
                car.setDate(LocalDateTime.now());
                car.setIdfood(idfood);
                User user = (User)httpSession.getAttribute("user");
                car.setIdUser(user.getIdUser());
                car.setNumfood(num);
                if(carService.saveOrUpdate(car))
                    return "1";
                else return "0";
    }

    @RequestMapping("/changeshopcar")
    public String changeshopcar(@RequestParam(value = "event") String event,
                                @RequestParam(value = "foodsName") String name,
                                HttpSession httpSession){
        QueryWrapper<Car> wrapper = new QueryWrapper<Car>();
        String sqlid = "select idfood from food where name = \"" + name+"\"";
        User user = (User) httpSession.getAttribute("user");
        Car car = carService.getOne(wrapper.inSql("idfood",sqlid).eq("idUser",user.getIdUser()));
        wrapper.eq("idUser", car.getIdUser()).eq("idfood", car.getIdfood());
        boolean result = false;
        switch (event) {
            case "add":
                car.setNumfood(car.getNumfood() + 1);
                result = carService.update(car, wrapper);
                break;
            case "sub":
                if (car.getNumfood() == 1)
                    result = carService.remove(wrapper);
                else {
                    car.setNumfood(car.getNumfood() - 1);
                    result = carService.update(car, wrapper);
                }
                break;
            case "delete":
                result = carService.remove(wrapper);
                break;
            default:
                break;
        }
        if(result) return "1";
        else return "0";

    }

    @RequestMapping("showshopcar")
    public LayuiTypeJson<CarVo> showshopcar(HttpSession httpSession){
        QueryWrapper<Car> carQueryWrapper=new QueryWrapper<Car>();
        User user = (User) httpSession.getAttribute("user");
       carQueryWrapper.eq("idUser",user.getIdUser());
        List<CarVo> carVos = new LinkedList<>();


        List<Car> cars = carService.list(carQueryWrapper);
        for (Car c:cars
             ) {
            CarVo carVo = new CarVo();
            carVo.setcar(c);
            carVo.setFood(foodService.getById(c.getIdfood()));
            carVos.add(carVo);
        }
        LayuiTypeJson<CarVo> layuiTypeJson = new LayuiTypeJson<>();
        layuiTypeJson.setCount(carVos.size());
        layuiTypeJson.setCode(0);
        layuiTypeJson.setMsg("");
        layuiTypeJson.setData(carVos);
        return layuiTypeJson;
    }

    @RequestMapping("totalcar")
    public HashMap<String,String> totalcar(HttpSession httpSession){
        QueryWrapper<Car> carQueryWrapper =new QueryWrapper<Car>();
        User user = (User) httpSession.getAttribute("user");
        carQueryWrapper.eq("idUser",user.getIdUser());
        List<Car> cars = carService.list(carQueryWrapper);
        Float sumprice = new Float(0);
        Integer sumnum = new Integer(0);
        for (Car c:cars
        ) {
            sumprice+=c.getNumfood()*foodService.getById(c.getIdfood()).getPrice();
            sumnum+=c.getNumfood();
        }
        HashMap<String,String> result = new HashMap<>();
        result.put("sumnum",sumnum.toString());
        result.put("summoney",sumprice.toString());
        return result;

    }



}

