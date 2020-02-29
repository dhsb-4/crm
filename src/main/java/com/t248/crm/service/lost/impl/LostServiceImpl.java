package com.t248.crm.service.lost.impl;

import com.t248.crm.entity.Lost;
import com.t248.crm.repository.LostRepository;
import com.t248.crm.service.lost.LostService;
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
public class LostServiceImpl implements LostService {

    @Resource
    private LostRepository lostRepository;

    @Override
    public Page<Lost> finds(String lstCustName, String lstCustManagerName, String lstStatus, Pageable pageable) {
        Specification<Lost> specification= new Specification<Lost>() {
            @Override
            public Predicate toPredicate(Root<Lost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(lstCustName!=null && !lstCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }

                if(lstCustManagerName!=null && !lstCustManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }

                if(lstStatus!=null && !lstStatus.equals("")){
                    if (!lstStatus.equals("全部")){
                        predicates.add(criteriaBuilder.like(root.get("lstStatus"),"%"+lstStatus+"%"));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return lostRepository.findAll(specification,pageable);
    }

    @Override
    public Lost findByLstId(Long lstId) {
        return lostRepository.findByLstId(lstId);
    }

    @Override
    public void save(Lost lost) {
        lostRepository.save(lost);
    }

    @Override
    public Page<Lost> findr(String lstCustName, String lstCustManagerName, Pageable pageable) {
        Specification<Lost> specification= new Specification<Lost>() {
            @Override
            public Predicate toPredicate(Root<Lost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(lstCustName!=null && !lstCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustName"),"%"+lstCustName+"%"));
                }

                if(lstCustManagerName!=null && !lstCustManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("lstCustManagerName"),"%"+lstCustManagerName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return lostRepository.findAll(specification,pageable);
    }

    @Override
    public Lost findByLstCustNo(String custNo) {
        return lostRepository.findByLstCustNo(custNo);
    }
}
