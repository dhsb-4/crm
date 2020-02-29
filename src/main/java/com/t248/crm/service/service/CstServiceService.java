package com.t248.crm.service.service;

import com.t248.crm.entity.CstService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CstServiceService {

    //保存
    public void save(CstService service);

    //分页查询
    public Page<CstService> finds(String svrCustName, String svrTitle, String svrType, Pageable pageable, String condition);

    /**
     * 根据id查询
     *
     */
    public CstService findBySvrId(Long svrId);

    /**
     * 删除
     */
    public void del(Long svrId);
}
