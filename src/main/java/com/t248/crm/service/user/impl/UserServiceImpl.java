package com.t248.crm.service.user.impl;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.User;
import com.t248.crm.repository.UserRepository;
import com.t248.crm.service.user.UserService;
import org.springframework.cache.annotation.Cacheable;
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
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User login(String usrName, String usrPassword) {
        return userRepository.login(usrName,usrPassword);
    }

    @Cacheable(value = "getUser",keyGenerator = "keyGenerator")
    public User getUser(String usrName) {
        return userRepository.findByUsrNameLike(usrName);
    }

    @Override
    public List<User> findAll(Role role) {
        return userRepository.findByRole(role);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long usrId) {
        userRepository.deleteById(usrId);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long usrId) {
        return userRepository.findByUsrId(usrId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findUsers(String usrName, Long roleId, Pageable pageable) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(usrName!=null && !usrName.equals("")){
                    predicates.add(criteriaBuilder.like(root.get("usrName"),"%"+usrName+"%"));
                }
                if (roleId!=null && roleId.longValue()!=0l){
                    Role role = new Role();
                    role.setRoleId(roleId);
                    predicates.add(criteriaBuilder.equal(root.get("role"),role));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return userRepository.findAll(specification,pageable);
    }
}
