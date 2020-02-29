package com.t248.crm.service.ordersline;

import com.t248.crm.entity.Orders;
import com.t248.crm.entity.OrdersLine;

import java.util.List;

public interface OrdersLineService {
    //根据订单编号查询
    public List<OrdersLine> findByOrders(Orders orders);
}
