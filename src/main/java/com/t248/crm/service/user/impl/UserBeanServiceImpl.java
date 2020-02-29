package com.t248.crm.service.user.impl;

import com.t248.crm.entity.UserBean;
import com.t248.crm.repository.UserBeanRepository;
import com.t248.crm.service.user.UserBeanService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserBeanServiceImpl implements UserBeanService {

    @Resource
    private UserBeanRepository userBeanRepository;

    @Cacheable(value = "getUser",keyGenerator = "keyGenerator")
    public UserBean getUser(String usrName) {
        return userBeanRepository.findByUsrNameLike(usrName);
    }

}
