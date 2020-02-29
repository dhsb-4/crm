package com.t248.crm.repository;

import com.t248.crm.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {

    //查询全部
    public List<Activity> findByAtvCustNo(String atvCustNo);

    //根据id查询
    public Activity findByAtvId(Long atvId);

    //删除
    @Modifying
    @Transactional
    @Query(value = "delete from Activity where atvCustNo=?1")
    public void aDel(String atvCustNo);
}
