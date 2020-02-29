package com.t248.crm.service.user.impl;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.repository.RoleRepository;
import com.t248.crm.service.user.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Page<Role> findRoles(String roleName, Pageable pageable) {
        Specification<Role> specification = new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(roleName!=null && !roleName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("roleName"),"%"+roleName+"%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return roleRepository.findAll(specification,pageable);
    }

    @Override
    public Role finadAll(Long roleId) {

        return roleRepository.findByRoleId(roleId);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role getRoleByUser(User user) {
        return roleRepository.findRoleByUsers(user);
    }

    @Override
    public List<Role> findRoles(String roleName) {
        List<Role> list = null;
        if (roleName!=null){
            list = roleRepository.findRolesByRoleNameLike("%"+roleName+"%");
        }else{
            list = roleRepository.findAll();
        }
        return list;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
