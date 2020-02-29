package com.t248.crm.repository;

import com.t248.crm.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    //查询
    public Customer findByCustNo(String custNo);

    public Customer findByCustId(Long custId);

    @Modifying
    @Transactional
    @Query(value = "delete from Customer where custNo=?1")
    public void cDel(String custNo);
}
