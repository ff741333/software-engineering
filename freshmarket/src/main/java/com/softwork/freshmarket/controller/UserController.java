package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.User;
import com.softwork.freshmarket.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xf
 * @since 2020-06-13
 */
@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/changepassword")
    public String changepassword(@RequestParam(value = "psw") String newpassword,
            HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        user.setPsw(newpassword);

        if(userService.updateById(user)){
            httpSession.removeAttribute("user");
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return "1";
        }
        else return "0";
    }

    @RequestMapping("/showpassword")
    public String showpassword(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return user.getPsw();
    }

    @RequestMapping("/checkUserServlet")
    public String checkuser(@RequestParam(value = "user") String user){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("idUser",user);
        if(userService.count(wrapper)!=0) return "1";
        else return "0";
    }

    @RequestMapping("/regServlet")
    public String reg(@RequestParam String user,
                      @RequestParam String pwd){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("idUser",user);
        if(userService.count(wrapper)==0){
            User user1 =new User();
            user1.setIdUser(user);
            user1.setPsw(pwd);
            user1.setIdentity("customer");
            if(userService.save(user1)) return "1";
            else return "0";
        }
        else return "2";
    }

    @RequestMapping("/loginServlet")
    public String login(@RequestParam(value = "account") String name,
                        @RequestParam(value = "password") String psw,
                        HttpSession httpSession){
        // 根据用户名和密码创建 Token
        UsernamePasswordToken token = new UsernamePasswordToken(name, psw);
        // 获取 subject 认证主体
        Subject subject = SecurityUtils.getSubject();
        try{

            // 开始认证，这一步会跳到我们自定义的 Realm 中
            subject.login(token);
            User user = userService.getById(name);
            httpSession.setAttribute("user", user);
            if(user.getIdentity().equals("customer")) return "1";
            else if(user.getIdentity().equals("admin")) return "2";
            else return "3";
        }catch(Exception e){
            e.printStackTrace();
            return "0";
        }
    }


   /* @RequestMapping("/quit")
    public void quit(HttpSession httpSession){
        httpSession.removeAttribute("user");
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.logout();
        }catch (Exception e){
            e.printStackTrace();
        }


    }*/
}

