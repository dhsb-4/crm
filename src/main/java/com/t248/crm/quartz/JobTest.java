package com.t248.crm.quartz;

import com.t248.crm.entity.Customer;
import com.t248.crm.entity.Lost;
import com.t248.crm.entity.Orders;
import com.t248.crm.service.customer.CustomerService;
import com.t248.crm.service.lost.LostService;
import com.t248.crm.service.orders.OrdersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.annotation.Resource;

public class JobTest implements Job {

    @Resource
    private OrdersService ordersService;

    @Resource
    private CustomerService customerService;

    @Resource
    private LostService lostService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Job定时任务！！");

        for (Orders o : ordersService.findList()) {
            if (lostService.findByLstCustNo(o.getOdrCustomerNo()) == null) {
                Customer customer = customerService.findByCustNo(o.getOdrCustomerNo());
                Lost lost = new Lost();
                lost.setLstCustNo(customer.getCustNo());
                lost.setLstCustName(customer.getCustName());
                lost.setLstCustManagerId(customer.getCustManagerId());
                lost.setLstCustManagerName(customer.getCustManagerName());
                lost.setLstLastOrderDate(o.getOdrDate());
                lost.setLstStatus("暂缓流失");
                lostService.save(lost);
            }
        }

    }
}
