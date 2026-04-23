package com.service;


import com.entity.OrderInfo;
import com.entity.ProductInfo;
import com.mapper.OrderMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient; // 发现服务
    // 保证服务列表多次请求过来都不改变,放到最外层，
    private List<ServiceInstance> instances;


    @PostConstruct // 依赖注入后执行
    public void init() {
        instances = discoveryClient.getInstances("product-service");
    }

//    public OrderInfo selectOrderById(Integer orderId){
//        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
//        // String url = "http://127.0.0.1:9090/product/"+orderInfo.getProductId();
//        // 从 Eureka 中获取服务列表
//        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
//        String uri = instances.get(0).getUri().toString();
//        String url = uri + "/product/" + orderInfo.getProductId();
//        log.info("uri:{}", uri);
//        ProductInfo productInfo = restTemplate.getForObject(url, ProductInfo.class);
//        orderInfo.setProductInfo(productInfo);
//        return orderInfo;
//    }

    private AtomicInteger count = new AtomicInteger(0);

//    // 手动实现负载均衡（轮询）
//    public OrderInfo selectOrderById(Integer orderId) {
//        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
//        // 对服务器数量取余，获取当前索引，达到轮询的效果
//        // 先取值进行取余，然后再完成自增逻辑
//        int index = count.getAndIncrement() % instances.size();
//        String uri = instances.get(index).getUri().toString();
//        String url = uri + "/product/" + orderInfo.getProductId();
//        orderInfo.setProductInfo(restTemplate.getForObject(url, ProductInfo.class));
//        return orderInfo;
//    }

    // 使用 Eureka 负载均衡
    public OrderInfo selectOrderById(Integer orderId) {
        OrderInfo orderInfo = orderMapper.selectOrderById(orderId);
        String url = "http://product-service/product/"+orderInfo.getProductId();
        // restTemplate 会对 url 进行解析，根据 Eureka 中的服务列表，自动选择一个服务实例
        orderInfo.setProductInfo(restTemplate.getForObject(url, ProductInfo.class));
        return orderInfo;
    }
}
