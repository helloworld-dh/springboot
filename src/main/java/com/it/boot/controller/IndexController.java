package com.it.boot.controller;

import com.it.boot.pojo.User;
import com.it.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

//    @Autowired
//    StringRedisTemplate redisTemplate;


    /*
     * 登录页
     * */
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model) {

        if (!StringUtils.isEmpty(user.getUserName()) && StringUtils.hasLength(user.getUserPassword())) {
            //把登录成功的用户保存起来
            session.setAttribute("loginUser", user);
            //登录成功重定向到main.html
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }

    }

    /*
     * 去main页面
     * */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model) {

        //是否登录， 拦截器，过滤器
//        Object loginUser = session.getAttribute("loginUser");
//        if (loginUser!=null){
//            return "main";
//        }else {
//            //回到登录页面
//            model.addAttribute("msg","请重新登录");
//            return "login";
//        }
//        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//
//        String s = opsForValue.get("/main.html");
//        String s1 = opsForValue.get("/sql");
//
//        model.addAttribute("mainCount",s);
//        model.addAttribute("sqlCount",s1);
        return "main";
    }

    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user", long.class);
        return aLong.toString();
    }

    @GetMapping("/user")
    @ResponseBody
    public User getById(@RequestParam("id") int id){
        User user = userService.getUserById(id);

        return user;
    }

}
