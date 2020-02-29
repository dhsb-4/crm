package com.t248.crm.service.customer;

import com.t248.crm.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerService {
    //新增
    public void finQuery(Customer customer);

    //分页查询
    public Page<Customer> finds(String custName, String custNo, String custRegion, String custManagerName, String custLevelLabel, Pageable pageable);

    //查询
    public Customer findByCustNo(String custNo);

    //修改
    public void save(Customer customer);

    public Customer findByCustId(Long custId);

    public void cDel(String custNo);

    public List<Customer> findList();

    //分页查询
    public Page<Customer> findo(String custName, Pageable pageable);

}
