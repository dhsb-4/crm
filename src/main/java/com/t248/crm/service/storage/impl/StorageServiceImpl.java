package com.t248.crm.service.storage.impl;
import com.t248.crm.entity.Storage;
import com.t248.crm.repository.StorageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.t248.crm.service.storage.StorageService;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Override
    public Page<Storage> finds(String prodName, String stkWarehouse, Pageable pageable) {
        Specification<Storage> specification= new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(prodName!=null && !prodName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("product").get("prodName"),"%"+prodName+"%"));
                }
                if(stkWarehouse!=null && !stkWarehouse.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("stkWarehouse"),"%"+stkWarehouse+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return storageRepository.findAll(specification,pageable);
    }
}
