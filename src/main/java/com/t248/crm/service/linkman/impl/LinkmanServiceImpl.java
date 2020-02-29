package com.t248.crm.service.linkman.impl;

import com.t248.crm.entity.Linkman;
import com.t248.crm.repository.LinkmanRepository;
import com.t248.crm.service.linkman.LinkmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkmanServiceImpl implements LinkmanService {
    @Resource
    private LinkmanRepository linkmanRepository;

    @Override
    public List<Linkman> findLinkmanList(String lkmCustNo) {
        return linkmanRepository.findByLkmCustNo(lkmCustNo);
    }

    @Override
    public Linkman findByLkmId(Long lkmId) {
        return linkmanRepository.findByLkmId(lkmId);
    }

    @Override
    public void findByQuery(Linkman linkman) {
        linkmanRepository.save(linkman);
    }

    @Override
    public void findDel(Long lkmId) {
        linkmanRepository.deleteById(lkmId);
    }

    @Override
    public void lDel(String lkmCustNo) {
        linkmanRepository.lDel(lkmCustNo);
    }
}
