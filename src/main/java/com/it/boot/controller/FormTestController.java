package com.it.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
* 文件上传测试
* */
@Controller
public class FormTestController {

    @GetMapping("/form_layouts")
    public String form_layouts(){

        return "form/form_layouts";
    }

    /*
    * MultipartFile 自动封装上传过来的文件
    * */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("images") MultipartFile[] images) throws IOException {

        if (!headerImg.isEmpty()){
            String originalFilename = headerImg.getOriginalFilename();
            headerImg.transferTo(new File("D:\\Project\\Codes\\images\\copy\\"+originalFilename));
        }
        if (images.length>0){
            for (MultipartFile image : images) {
                if (!image.isEmpty()){
                    String originalFilename = image.getOriginalFilename();
                    image.transferTo(new File("D:\\Project\\Codes\\images\\copy\\"+originalFilename));
                }
            }
        }
        return "main";
    }
}
