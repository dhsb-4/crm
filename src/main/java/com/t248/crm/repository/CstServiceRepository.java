package com.t248.crm.repository;

import com.t248.crm.entity.CstService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CstServiceRepository extends JpaRepository<CstService, Long>, JpaSpecificationExecutor<CstService> {
    /**
     * 根据id查询
     */
    public CstService findBySvrId(Long svrId);
}
