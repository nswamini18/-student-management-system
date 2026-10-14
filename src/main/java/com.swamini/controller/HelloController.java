package com.swamini.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
        public String hey(){
        String s="helllo from springboot";
        return s;
    }
}
