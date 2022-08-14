package com.spring.summer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author CXB
 * @ClassName HelloController
 * @date 2022/8/13 16:44
 * @Description TODO
 */
@RestController
public class HelloController {


    @GetMapping("/")
    public String hero(){
        return "aike";
    }
}
