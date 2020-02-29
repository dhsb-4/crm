package com.t248.crm.repository;

import com.t248.crm.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {
    //根据销售编号查询
    public List<Plan> findByPlaChcId(Long plaChcId);

    //根据id修改
    @Transactional
    @Modifying
    @Query("update Plan set plaTodo=?1 where plaId=?2")
    public void findDelete(String plaTodo, Long plaId);

    @Transactional
    @Modifying
    @Query("update Plan set plaResult=?1 where plaId=?2")
    public void findOut(String plaResult, Long plaId);


}
