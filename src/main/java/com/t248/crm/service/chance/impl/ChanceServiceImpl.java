package com.t248.crm.service.chance.impl;

import com.t248.crm.entity.Chance;
import com.t248.crm.repository.ChanceRepository;
import com.t248.crm.service.chance.ChanceService;
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
public class ChanceServiceImpl implements ChanceService {

    @Resource
    private ChanceRepository chanceRepository;


    @Override
    public Page<Chance> finds(String chcCustName, String chcTitle,String chcStatus, Pageable pageable) {
        Specification<Chance> specification= new Specification<Chance>() {
            @Override
            public Predicate toPredicate(Root<Chance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(chcCustName!=null && !chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if(chcTitle!=null && !chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                predicates.add(criteriaBuilder.like(root.get("chcStatus"),chcStatus));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return chanceRepository.findAll(specification,pageable);
    }

    @Override
    public void save(Chance chance) {
        chanceRepository.save(chance);
    }

    @Override
    public Chance eait(Long id) {
        return chanceRepository.findByChcId(id);
    }

    @Override
    public void del(Long chcId) {
        chanceRepository.deleteById(chcId);
    }

    @Override
    public Page<Chance> planfind(String chcCustName, String chcTitle, String chcLinkman, Pageable pageable) {
        Specification<Chance> specification= new Specification<Chance>() {
            @Override
            public Predicate toPredicate(Root<Chance> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(chcCustName!=null && !chcCustName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcCustName"),"%"+chcCustName+"%"));
                }
                if(chcTitle!=null && !chcTitle.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcTitle"),"%"+chcTitle+"%"));
                }
                if(chcLinkman!=null && !chcLinkman.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("chcLinkman"),"%"+chcLinkman+"%"));
                }
                predicates.add(criteriaBuilder.notLike(root.get("chcStatus"),"未分配"));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return chanceRepository.findAll(specification,pageable);
    }

    @Override
    public void finUpd(Long chcId, String chcStatus) {
        chanceRepository.finUpd(chcId,chcStatus);
    }
}
