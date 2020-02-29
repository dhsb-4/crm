package com.t248.crm.service.orders;

import com.t248.crm.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrdersService {
    /**
     * 分页查询
     */
    public Page<Orders> finds(Pageable pageable, String odrCustomerNo);

    public Orders findByOdrId(Long odrId);

    public void oDel(String custNo);

    public List<Orders> findByOdrCustomerNo(String custNo);

    public List<Orders> findList();
}
