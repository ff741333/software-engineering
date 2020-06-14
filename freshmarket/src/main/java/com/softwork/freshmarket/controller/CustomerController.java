package com.softwork.freshmarket.controller;


import com.softwork.freshmarket.entity.Customer;
import com.softwork.freshmarket.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@RestController
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping("/changecustomer")
    public String changecustomer(@RequestParam(value = "idUser") String idUser,
                                 @RequestParam(value = "sex" ) String sex,
                                 @RequestParam(value = "numPhone") String numPhone,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "birthday") String birthday){
        Customer customer = new Customer();
        customer.setIdUser(idUser);
        customer.setSex(sex);
        customer.setNumPhone(numPhone);
        customer.setName(name);
        customer.setBirthday(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if(customerService.saveOrUpdate(customer))
            return "1";
        else return "0";
    }

}

