package com.t248.crm.service.plan;

import com.t248.crm.entity.Plan;

import java.util.List;

public interface PlanService {
    //根据销售id查询
    public List<Plan> planChcIdAll(Long plaChcId);

    //删除计划项
    public void planDel(Long plaId);

    //修改计划项
    public void planUpd(Long plaId, String plaTodo);

    //添加计划项
    public void planSave(Plan plan);

    //保存执行计划
    public void findOut(String plaResult, Long plaId);
}
