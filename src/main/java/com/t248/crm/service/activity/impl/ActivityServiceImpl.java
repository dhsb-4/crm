package com.t248.crm.service.activity.impl;

import com.t248.crm.entity.Activity;
import com.t248.crm.repository.ActivityRepository;
import com.t248.crm.service.activity.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityRepository activityRepository;


    @Override
    public List<Activity> findActivityList(String atvCustNo) {
        return activityRepository.findByAtvCustNo(atvCustNo);
    }

    @Override
    public Activity findByAtvId(Long atvId) {
        return activityRepository.findByAtvId(atvId);
    }

    @Override
    public void findSave(Activity activity) {
        activityRepository.save(activity);
    }

    @Override
    public void findDel(Long atvId) {
        activityRepository.deleteById(atvId);
    }

    @Override
    public void aDel(String atvCustNo) {
        activityRepository.aDel(atvCustNo);
    }
}
