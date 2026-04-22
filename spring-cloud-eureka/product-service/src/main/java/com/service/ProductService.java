package com.service;

import com.entity.ProductInfo;
import com.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductInfo selectProductById(Integer id){
        return productMapper.selectProductById(id);
    }
}
