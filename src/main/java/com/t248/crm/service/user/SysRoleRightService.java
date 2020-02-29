package com.t248.crm.service.user;

import com.t248.crm.entity.Role;
import com.t248.crm.entity.SysRoleRight;

import java.util.List;

public interface SysRoleRightService {


    public void sDel(Long rfRoleId);

    public List<SysRoleRight> findSysList(Role role);
}
