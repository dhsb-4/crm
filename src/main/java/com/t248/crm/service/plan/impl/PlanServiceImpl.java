package com.t248.crm.service.plan.impl;

import com.t248.crm.entity.Plan;
import com.t248.crm.repository.PlanRepository;
import com.t248.crm.service.plan.PlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    @Resource
    private PlanRepository planRepository;


    @Override
    public List<Plan> planChcIdAll(Long plaChcId) {
        return planRepository.findByPlaChcId(plaChcId);
    }

    @Override
    public void planDel(Long plaId) {
        planRepository.deleteById(plaId);
    }

    @Override
    public void planUpd(Long plaId, String plaTodo) {
        planRepository.findDelete(plaTodo,plaId);
    }

    @Override
    public void planSave(Plan plan) {
        planRepository.save(plan);
    }

    @Override
    public void findOut(String plaResult, Long plaId) {
        planRepository.findOut(plaResult,plaId);
    }
}
