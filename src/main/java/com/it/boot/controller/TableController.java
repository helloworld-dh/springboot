package com.it.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.it.boot.pojo.Admin;
import com.it.boot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TableController {

    @Autowired
    AdminService adminService;


    @GetMapping("/basic_table")
    public String basic_table(){

        return "table/basic_table";
    }

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //表格内容的遍历
//        List<User> users = Arrays.asList(new User(1,"zhangsan","124124"),
//                new User(2,"lisi","2awdaw"),
//                new User(3,"haha","Aaaa"),
//                new User(4,"awdawd","awdaw"));
//
//        model.addAttribute("users",users);
//        if (users.size()>3){
//            throw new UserTooManyException();
//        }
        //从数据库中查出user表中的用户进行搜索

        //List<Admin> list = adminService.list();
        //model.addAttribute("admins",list);
        //分页查询数据
        Page<Admin> adminPage = new Page<>(pn, 2);
        IPage<Admin> page = adminService.page(adminPage, null);
        long current = page.getCurrent();
        long pages = page.getPages();
        long total = page.getTotal();
        List<Admin> records = page.getRecords();
        model.addAttribute("pages",page);

        return "table/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table(){

        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table(){

        return "/table/editable_table";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Long id,
                             @RequestParam(value ="pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){
        adminService.removeById(id);
        ra.addAttribute("pn",pn);

        return "redirect:/dynamic_table";
    }

    @PostMapping("/admin/add")
    public String addAdmin(Admin admin){
        adminService.save(admin);

        return "redirect:/dynamic_table";
    }

    @GetMapping("/admin/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Long id, Model model){

        Admin updateObject = adminService.getById(id);
        System.out.println(updateObject);
        model.addAttribute("admin",updateObject);
        return "/table/responsive_table";
    }

    @PostMapping("/admin/update")
    public String updateAdmin(Admin admin){

        adminService.updateById(admin);

        return "redirect:/dynamic_table";

    }




}
