package com.t248.crm.service.activity;

import com.t248.crm.entity.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 查询全部
     */
    public List<Activity> findActivityList(String atvCustNo);

    //根据id查询
    public Activity findByAtvId(Long atvId);

    //保存
    public void findSave(Activity activity);

    //删除
    public void findDel(Long atvId);

    //根据客户编号删除
    public void aDel(String atvCustNo);
}
