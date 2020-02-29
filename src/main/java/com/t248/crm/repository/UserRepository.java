package com.t248.crm.repository;


import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where u.usrName=?1 and u.usrPassword=?2")
    public User login(String usrName, String usrPassword);

    public User findByUsrId(Long usrId);

    public User findByUsrNameLike(String usrName);

    public List<User> findByRole(Role role);


}