package com.t248.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 客户流失实体类
 */
@Entity
@Table(name = "cst_lost")
public class Lost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lst_id")
    private Long lstId;             //编号
    @Column(name = "lst_cust_no")
    private String lstCustNo;       //客户编号
    @Column(name = "lst_cust_name")
    private String lstCustName;     //客户姓名
    @Column(name = "lst_cust_manager_id")
    private Long lstCustManagerId;  //客户经理编号
    @Column(name = "lst_cust_manager_name")
    private String lstCustManagerName;    //客户经理姓名
    @Column(name = "lst_last_order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lstLastOrderDate;        //上次下单时间
    @Column(name = "lst_lost_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lstLostDate;             //确定流失时间
    @Column(name = "lst_delay")
    private String lstDelay;              //暂缓措施
    @Column(name = "lst_reason")
    private String lstReason;             //流失原因
    @Column(name = "lst_status")
    private String lstStatus;             //流失状态


    public Long getLstId() {
        return lstId;
    }

    public void setLstId(Long lstId) {
        this.lstId = lstId;
    }


    public String getLstCustNo() {
        return lstCustNo;
    }

    public void setLstCustNo(String lstCustNo) {
        this.lstCustNo = lstCustNo;
    }


    public String getLstCustName() {
        return lstCustName;
    }

    public void setLstCustName(String lstCustName) {
        this.lstCustName = lstCustName;
    }


    public Long getLstCustManagerId() {
        return lstCustManagerId;
    }

    public void setLstCustManagerId(Long lstCustManagerId) {
        this.lstCustManagerId = lstCustManagerId;
    }


    public String getLstCustManagerName() {
        return lstCustManagerName;
    }

    public void setLstCustManagerName(String lstCustManagerName) {
        this.lstCustManagerName = lstCustManagerName;
    }


    public Date getLstLastOrderDate() {
        return lstLastOrderDate;
    }

    public void setLstLastOrderDate(Date lstLastOrderDate) {
        this.lstLastOrderDate = lstLastOrderDate;
    }


    public Date getLstLostDate() {
        return lstLostDate;
    }

    public void setLstLostDate(Date lstLostDate) {
        this.lstLostDate = lstLostDate;
    }


    public String getLstDelay() {
        return lstDelay;
    }

    public void setLstDelay(String lstDelay) {
        this.lstDelay = lstDelay;
    }


    public String getLstReason() {
        return lstReason;
    }

    public void setLstReason(String lstReason) {
        this.lstReason = lstReason;
    }


    public String getLstStatus() {
        return lstStatus;
    }

    public void setLstStatus(String lstStatus) {
        this.lstStatus = lstStatus;
    }

}
