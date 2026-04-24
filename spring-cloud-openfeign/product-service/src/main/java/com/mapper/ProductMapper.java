package com.mapper;

import com.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("select * from product_detail where id = #{id}")
    ProductInfo selectProductById(Integer id);
}
