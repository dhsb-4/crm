package com.t248.crm.repository;

import com.t248.crm.entity.Chance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface ChanceRepository extends JpaRepository<Chance, Long>, JpaSpecificationExecutor<Chance> {

    public Chance findByChcId(Long id);

    /**
     * 修改
     */
    @Transactional
    @Modifying
    @Query("update Chance set chcStatus=?2 where chcId = ?1")
    public void finUpd(Long chcId, String chcStatus);


}
