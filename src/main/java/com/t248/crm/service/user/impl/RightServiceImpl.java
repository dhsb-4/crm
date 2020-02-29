package com.t248.crm.service.user.impl;

import com.t248.crm.entity.Right;
import com.t248.crm.entity.Role;
import com.t248.crm.repository.RightRepository;
import com.t248.crm.service.user.RightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RightServiceImpl implements RightService {

    @Resource
    private RightRepository rightRepository;

    @Override
    public List<Right> findAllRights() {
        return rightRepository.findAll();
    }
    
}
