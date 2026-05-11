package com.bite.product.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config-server")
public class ConfigController {
    @Value("${data.env:unknown}")
    private String env;

    @RequestMapping("/getEnv")
    public String getEnv(){
        return "data.env:" + env;
    }

}
