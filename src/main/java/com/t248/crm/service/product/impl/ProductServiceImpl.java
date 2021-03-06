package com.t248.crm.service.product.impl;

import com.t248.crm.entity.Product;
import com.t248.crm.repository.ProductRepository;
import com.t248.crm.service.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductRepository productRepository;

    @Override
    public Page<Product> finds(String prodName, String prodType, String prodBatch, Pageable pageable) {
        Specification<Product> specification= new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(prodName!=null && !prodName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodName"),"%"+prodName+"%"));
                }
                if(prodType!=null && !prodType.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodType"),"%"+prodType+"%"));
                }
                if(prodBatch!=null && !prodBatch.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("prodBatch"),"%"+prodBatch+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return productRepository.findAll(specification,pageable);
    }
}
