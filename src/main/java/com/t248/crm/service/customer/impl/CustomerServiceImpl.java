package com.t248.crm.service.customer.impl;

import com.t248.crm.entity.Customer;
import com.t248.crm.repository.CustomerRepository;
import com.t248.crm.service.customer.CustomerService;
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
public class CustomerServiceImpl implements CustomerService  {

    @Resource
    private CustomerRepository customerRepository;

    @Override
    public void finQuery(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Page<Customer> finds(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel, Pageable pageable) {
        Specification<Customer> specification= new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(custName!=null && !custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }

                if(custNo!=null && !custNo.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custNo"),"%"+custNo+"%"));
                }

                if(custRegion!=null && !custRegion.equals("")){
                    if (!custRegion.equals("请选择")){
                        predicates.add(criteriaBuilder.like(root.get("custRegion"),"%"+custRegion+"%"));
                    }
                }

                if(custManagerName!=null && !custManagerName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custManagerName"),"%"+custManagerName+"%"));
                }

                if(custLevelLabel!=null && !custLevelLabel.equals("")){
                    if (!custLevelLabel.equals("请选择")) {
                        predicates.add(criteriaBuilder.like(root.get("custLevelLabel"), "%" + custLevelLabel + "%"));
                    }
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return customerRepository.findAll(specification,pageable);
    }

    @Override
    public Customer findByCustNo(String custNo) {
        return customerRepository.findByCustNo(custNo);
    }


    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer findByCustId(Long custId) {
        return customerRepository.findByCustId(custId);
    }

    @Override
    public void cDel(String custNo) {
        customerRepository.cDel(custNo);
    }

    @Override
    public List<Customer> findList() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findo(String custName, Pageable pageable) {
        Specification<Customer> specification= new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(custName!=null && !custName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("custName"),"%"+custName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return customerRepository.findAll(specification,pageable);
    }
}
