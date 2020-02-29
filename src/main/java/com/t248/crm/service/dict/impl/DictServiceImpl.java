package com.t248.crm.service.dict.impl;

import com.t248.crm.entity.Dict;
import com.t248.crm.repository.DictRepository;
import com.t248.crm.service.dict.DictService;
import com.t248.crm.vo.Dictinit;
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
public class DictServiceImpl implements DictService {

    @Resource
    private DictRepository dictRepository;


    @Override
    public List<Dict> findAll() {
        return dictRepository.findAll();
    }

    @Override
    public List<Dict> findByDictType(String dictType) {
        return dictRepository.findByDictType(dictType);
    }

    @Override
    public Dict findByDictItem(String dictItem) {
        return dictRepository.findByDictItem(dictItem);
    }

    @Override
    public List<Dictinit> findLelList() {
        return dictRepository.findLelList();
    }

    @Override
    public List<Dictinit> findFwList() {
        return dictRepository.findFwList();
    }

    @Override
    public Page<Dict> findl(String dictValue, String dictItem, Pageable pageable,String name) {
        Specification<Dict> specification= new Specification<Dict>() {
            @Override
            public Predicate toPredicate(Root<Dict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(dictValue!=null && !dictValue.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictValue"),"%"+dictValue+"%"));
                }
                if(dictItem!=null && !dictItem.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("dictItem"),"%"+dictItem+"%"));
                }

                if(name.equals("gradeList")){
                    predicates.add(criteriaBuilder.like(root.get("dictType"),"%"+"客户等级"+"%"));
                }

                if(name.equals("serveList")){
                    predicates.add(criteriaBuilder.like(root.get("dictType"),"%"+"服务类型"+"%"));
                }

                if(name.equals("dealList")){
                    predicates.add(criteriaBuilder.like(root.get("dictType"),"%"+"地区"+"%"));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return dictRepository.findAll(specification,pageable);
    }

    @Override
    public int getMax() {
        return dictRepository.getMax();
    }

    @Override
    public void save(Dict dict) {
        dictRepository.save(dict);
    }

    @Override
    public Dict findByDictId(Long dictId) {
        return dictRepository.findByDictId(dictId);
    }

    @Override
    public void Del(Long dictId) {
        dictRepository.deleteById(dictId);
    }
}
