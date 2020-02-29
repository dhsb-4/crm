package com.t248.crm.service.service.impl;

import com.t248.crm.entity.CstService;
import com.t248.crm.repository.CstServiceRepository;
import com.t248.crm.service.service.CstServiceService;
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
public class CstServiceServiceImpl implements CstServiceService {

    @Resource
    private CstServiceRepository cstServiceRepository;

    @Override
    public void save(CstService service) {
        cstServiceRepository.save(service);
    }

    @Override
    public Page<CstService> finds(String svrCustName, String svrTitle, String svrType, Pageable pageable,String condition) {
        Specification<CstService> specification = new Specification<CstService>() {
            @Override
            public Predicate toPredicate(Root<CstService> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(svrCustName!=null && !svrCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrCustName"),"%"+svrCustName+"%"));
                }

                if(svrTitle!=null && !svrTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("svrTitle"),"%"+svrTitle+"%"));
                }


                if(svrType!=null && !svrType.equals("")){
                    if (!svrType.equals("请选择")) {
                        predicates.add(criteriaBuilder.like(root.get("svrType"), "%" + svrType + "%"));
                    }
                }
                if(condition.equals("dispatch")){
                    predicates.add(criteriaBuilder.like(root.get("svrStatus"), "新创建"));
                }

                if(condition.equals("dealList")){
                    predicates.add(criteriaBuilder.like(root.get("svrStatus"), "已分配"));
                }

                if(condition.equals("feedbackList")){
                    predicates.add(criteriaBuilder.like(root.get("svrStatus"), "已处理"));
                }

                if(condition.equals("archList")){
                    predicates.add(criteriaBuilder.like(root.get("svrStatus"), "已归档"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return cstServiceRepository.findAll(specification,pageable);
    }

    @Override
    public CstService findBySvrId(Long svrId) {
        return cstServiceRepository.findBySvrId(svrId);
    }

    @Override
    public void del(Long svrId) {
        cstServiceRepository.deleteById(svrId);
    }
}
