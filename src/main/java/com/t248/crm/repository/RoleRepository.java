package com.t248.crm.repository;


import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import org.codehaus.groovy.antlr.parser.GroovyLexer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    public Role findByRoleId(Long roleId);

    public Role findRoleByUsers(User user);

    public List<Role> findRolesByRoleNameLike(String roleName);
}
