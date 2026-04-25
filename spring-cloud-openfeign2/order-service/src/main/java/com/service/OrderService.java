package com.service;

import com.api.ProductApiInterface;
import com.entity.OrderInfo;
import com.entity.ProductInfo;
import com.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductApiInterface productApiInterface;

    public OrderInfo selectOrderById(Integer orderId){
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
//        String url = "http://product-service/product/" + orderInfo.getProductId();
//        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
        ProductInfo productInfo = productApiInterface.getProductById(orderInfo.getProductId());
        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }

}
