package com.t248.crm.repository;

import com.t248.crm.entity.Linkman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface LinkmanRepository extends JpaRepository<Linkman, Long>, JpaSpecificationExecutor<Linkman> {

    //根据客户编号查询
    public List<Linkman> findByLkmCustNo(String lkmCusNo);

    //根据id查询
    public Linkman findByLkmId(Long lkmId);

    //删除
    @Modifying
    @Transactional
    @Query(value = "delete from Linkman where lkmCustNo=?1")
    public void lDel(String lkmCustNo);
}
