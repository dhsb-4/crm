package com.t248.crm.repository;

import com.t248.crm.entity.Orders;
import com.t248.crm.entity.OrdersLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrdersLineRepository extends JpaRepository<OrdersLine, Long>, JpaSpecificationExecutor<OrdersLine> {

    //根据订单编号查询
    public List<OrdersLine> findByOrders(Orders orders);
}
