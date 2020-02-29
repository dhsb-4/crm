package com.t248.crm.repository;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.SysRoleRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SysRoleRightRepository extends JpaRepository<SysRoleRight, Long>, JpaSpecificationExecutor<SysRoleRight> {

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role_right where rf_role_id=?1", nativeQuery = true)
    public void sDel(Long rfRoleId);

    public List<SysRoleRight> findByRole(Role role);


}
