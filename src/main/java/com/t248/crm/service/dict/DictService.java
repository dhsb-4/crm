package com.t248.crm.service.dict;

import com.t248.crm.entity.Dict;
import com.t248.crm.vo.Dictinit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DictService {
    public List<Dict> findAll();

    public List<Dict> findByDictType(String dictType);

    public Dict findByDictItem(String dictItem);

    public List<Dictinit> findLelList();

    public List<Dictinit> findFwList();

    public Page<Dict> findl(String dictValue, String dictItem, Pageable pageable, String name);

    public int getMax();

    public void save(Dict dict);

    public Dict findByDictId(Long dictId);

    public void Del(Long dictId);



}
