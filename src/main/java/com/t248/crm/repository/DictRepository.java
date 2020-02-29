package com.t248.crm.repository;

import com.t248.crm.entity.Dict;
import com.t248.crm.vo.Dictinit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictRepository extends JpaRepository<Dict, Long>, JpaSpecificationExecutor<Dict> {

    public List<Dict> findByDictType(String dictType);

    public Dict findByDictItem(String dictItem);


    @Query(value = "SElECT d.dict_value as id,d.dict_item as name,COUNT(c.cust_level_label) as count FROM bas_dict d LEFT JOIN cst_customer c\n" +
            "ON d.`dict_value`=c.`cust_level`\n" +
            "WHERE d.dict_type='客户等级' \n" +
            "GROUP BY d.dict_item\n" +
            "ORDER BY d.dict_value ASC", nativeQuery = true)
    public List<Dictinit> findLelList();

    @Query(value = "SElECT (@i=@i+1) as id,d.dict_item as name,COUNT(s.svr_type) as count,(SELECT@i=0) FROM bas_dict d LEFT JOIN cst_service s\n" +
            "ON d.dict_value = s.svr_type\n" +
            "WHERE d.dict_type='服务类型' \n" +
            "GROUP BY d.dict_item\n" +
            "ORDER BY id ASC ", nativeQuery = true)
    public List<Dictinit> findFwList();

    @Query(value = "SELECT MAX(dict_value) FROM bas_dict where dict_type='客户等级'", nativeQuery = true)
    public int getMax();

    public Dict findByDictId(Long dictId);
}
