package com.t248.crm.service.lost;

import com.t248.crm.entity.Lost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LostService {
    //分页查询
    public Page<Lost> finds(String lstCustName, String lstCustManagerName, String lstStatus, Pageable pageable);

    //根据id查询
    public Lost findByLstId(Long lstId);

    //保存
    public void save(Lost lost);

    //分页查询
    public Page<Lost> findr(String lstCustName, String lstCustManagerName, Pageable pageable);

    public Lost findByLstCustNo(String custNo);

}
