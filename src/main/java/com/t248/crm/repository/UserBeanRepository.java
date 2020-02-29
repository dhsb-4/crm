package com.t248.crm.repository;

import com.t248.crm.entity.User;
import com.t248.crm.entity.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserBeanRepository extends JpaRepository<UserBean, Long>, JpaSpecificationExecutor<UserBean> {
    public UserBean findByUsrNameLike(String usrName);


}
