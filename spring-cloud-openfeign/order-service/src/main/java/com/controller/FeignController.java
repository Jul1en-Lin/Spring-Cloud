package com.controller;

import com.entity.ProductInfo;
import com.feignapi.ProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private ProductApi productApi;

    @RequestMapping("/f1")
    public String f1(Integer id){
        return productApi.p1(id);
    }

    @RequestMapping("/f2")
    public String f2(Integer id, String name){
        return productApi.p2(id, name);
    }

    @RequestMapping("/f3")
    public String f3() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1);
        productInfo.setProductName("test");
        return productApi.p3(productInfo);
    }

    @RequestMapping("/f4")
    public String f4() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1);
        productInfo.setProductName("test");
        return productApi.p4(productInfo);
    }
}
