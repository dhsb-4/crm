package com.t248.crm.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 历史订单实体类
 */
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "odr_id")
    private Long odrId;                 //订单编号
    @Column(name = "odr_customer_no")
    private String odrCustomerNo;       //订单客户公司编号
    @Column(name = "odr_date")
    private Date odrDate;               //订单日期
    @Column(name = "odr_addr")
    private String odrAddr;             //地址
    @Column(name = "odr_status")
    private String odrStatus;           //状态

    @OneToMany(targetEntity = OrdersLine.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "orders")
    @JsonIgnore
    private Set<OrdersLine> ordersLines = new HashSet<OrdersLine>();

    public Set<OrdersLine> getOrdersLines() {
        return ordersLines;
    }

    public void setOrdersLines(Set<OrdersLine> ordersLines) {
        this.ordersLines = ordersLines;
    }

    public Long getOdrId() {
        return odrId;
    }

    public void setOdrId(Long odrId) {
        this.odrId = odrId;
    }


    public String getOdrCustomerNo() {
        return odrCustomerNo;
    }

    public void setOdrCustomerNo(String odrCustomerNo) {
        this.odrCustomerNo = odrCustomerNo;
    }


    public Date getOdrDate() {
        return odrDate;
    }

    public void setOdrDate(Date odrDate) {
        this.odrDate = odrDate;
    }


    public String getOdrAddr() {
        return odrAddr;
    }

    public void setOdrAddr(String odrAddr) {
        this.odrAddr = odrAddr;
    }


    public String getOdrStatus() {
        return odrStatus;
    }

    public void setOdrStatus(String odrStatus) {
        this.odrStatus = odrStatus;
    }

}
