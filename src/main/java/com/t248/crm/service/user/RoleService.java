package com.t248.crm.service.user;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    public List<Role> findAllRoles();

    public void addRole(Role role);

    public Page<Role> findRoles(String roleName, Pageable pageable);

    public Role finadAll(Long roleId);

    public void deleteRole(Long roleId);

    public Role getRoleByUser(User user);

    public List<Role> findRoles(String roleName);

    public void save(Role role);
}
