package com.t248.crm.service.user.impl;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.SysRoleRight;
import com.t248.crm.repository.SysRoleRightRepository;
import com.t248.crm.service.user.SysRoleRightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleRightServiceImpl implements SysRoleRightService {

    @Resource
    private SysRoleRightRepository sysRoleRightRepository;


    @Override
    public void sDel(Long rfRoleId) {
        sysRoleRightRepository.sDel(rfRoleId);
    }

    @Override
    public List<SysRoleRight> findSysList(Role role) {
        return sysRoleRightRepository.findByRole(role);
    }
}
