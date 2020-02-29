package com.t248.crm.service.chance;

import com.t248.crm.entity.Chance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChanceService {
    public Page<Chance> finds(String chcCustName, String chcTitle, String chcStatus, Pageable pageable);

    public void save(Chance chance);

    public Chance eait(Long id);

    public void del(Long chcId);

    public Page<Chance> planfind(String chcCustName, String chcTitle, String chcLinkman, Pageable pageable);

    public void finUpd(Long chcId, String chcStatus);

}
