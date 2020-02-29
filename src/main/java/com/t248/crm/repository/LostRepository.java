package com.t248.crm.repository;

import com.t248.crm.entity.Lost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LostRepository extends JpaRepository<Lost, Long>, JpaSpecificationExecutor<Lost> {
    //查询
    public Lost findByLstId(Long lstId);

    //查询
    public Lost findByLstCustNo(String custNo);
}
