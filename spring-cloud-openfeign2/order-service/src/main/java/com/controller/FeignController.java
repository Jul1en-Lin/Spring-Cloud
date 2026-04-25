package com.controller;

import com.api.ProductApiInterface;
import com.entity.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private ProductApiInterface productApiInterface;

    @RequestMapping("/f1")
    public String f1(Integer id){
        return productApiInterface.p1(id);
    }

    @RequestMapping("/f2")
    public String f2(Integer id, String name){
        return productApiInterface.p2(id, name);
    }

    @RequestMapping("/f3")
    public String f3() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1);
        productInfo.setProductName("test");
        return productApiInterface.p3(productInfo);
    }

    @RequestMapping("/f4")
    public String f4() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(1);
        productInfo.setProductName("test");
        return productApiInterface.p4(productInfo);
    }
}
