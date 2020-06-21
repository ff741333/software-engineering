package com.softwork.freshmarket.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.softwork.freshmarket.entity.Food;
import com.softwork.freshmarket.entity.FoodplusStore;
import com.softwork.freshmarket.entity.LayuiTypeJson;
import com.softwork.freshmarket.entity.User;
import com.softwork.freshmarket.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/showusers")
    public LayuiTypeJson<User> showfood(HttpServletRequest httpServletRequest){
        List<User> Users;
        String FoodName = null;
        FoodName = httpServletRequest.getParameter("key[FoodName]");
        if("".equals(FoodName)||FoodName==null)
            Users=userService.list();
        else {
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("idUser",FoodName);
            Users=userService.list(wrapper);
        }
        //    foodplusStores.forEach(FoodplusStore::doFoodID);
        LayuiTypeJson<User> layuiTypeJson = new LayuiTypeJson<>();
        layuiTypeJson.setCount(Users.size());
        layuiTypeJson.setCode(0);
        layuiTypeJson.setMsg("");
        layuiTypeJson.setData(Users);
        return layuiTypeJson;
    }
    @RequestMapping("/addmember")
    public String addmember(@RequestParam(value = "idUser") String name,
                          @RequestParam(value = "psw") String password,
                          @RequestParam(value = "identity") String level){
        User user =new User();
        user.setIdUser(name);
        //food.setNum(0);
        user.setPsw(password);
        user.setIdentity(level);
        if(userService.save(user))
            return "添加成功!";
        else return "0";
    }
    @RequestMapping("/editmember")
    public String editmember(@RequestParam(value = "idUser") String name,
                            @RequestParam(value = "psw") String password,
                            @RequestParam(value = "identity") String level){
        User user =new User();
        user.setIdUser(name);
        //food.setNum(0);
        user.setPsw(password);
        user.setIdentity(level);
        if(userService.updateById(user))
            return "修改成功!";
        else return "0";
    }
    @RequestMapping("/deletemember")
    public String deletemember(@RequestParam(value = "idUser") String name,
                             @RequestParam(value = "psw") String password,
                             @RequestParam(value = "identity") String level){
        User user =new User();
        user.setIdUser(name);
        //food.setNum(0);
        user.setPsw(password);
        user.setIdentity(level);
        if(userService.removeById(user))
            return "删除成功!";
        else return "0";
    }
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

