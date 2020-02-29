package com.t248.crm.service.product;

import com.t248.crm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Page<Product> finds(String prodName, String prodType, String prodBatch, Pageable pageable);
}
