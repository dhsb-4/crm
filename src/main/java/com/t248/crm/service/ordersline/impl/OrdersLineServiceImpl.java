package com.t248.crm.service.ordersline.impl;

import com.t248.crm.entity.Orders;
import com.t248.crm.entity.OrdersLine;
import com.t248.crm.repository.OrdersLineRepository;
import com.t248.crm.repository.OrdersRepository;
import com.t248.crm.service.orders.OrdersService;
import com.t248.crm.service.ordersline.OrdersLineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersLineServiceImpl implements OrdersLineService {

    @Resource
    private OrdersLineRepository ordersLineRepository;


    @Override
    public List<OrdersLine> findByOrders(Orders orders) {
        return ordersLineRepository.findByOrders(orders);
    }
}
