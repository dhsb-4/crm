package com.t248.crm.service.linkman;

import com.t248.crm.entity.Linkman;

import java.util.List;

public interface LinkmanService {
    /**
     * 根据客户编号查询
     */
    public List<Linkman> findLinkmanList(String lkmCustNo);

    //根据id查询
    public Linkman findByLkmId(Long lkmId);

    //保存
    public void findByQuery(Linkman linkman);

    //删除
    public void findDel(Long lkmId);

    //根据客户编号删除
    public void lDel(String lkmCustNo);




}
