package com.t248.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cst_service")
public class CstService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "svr_id")
    private Long svrId;                         //编号
    @Column(name = "svr_type")
    private String svrType;                     //服务类型
    @Column(name = "svr_title")
    private String svrTitle;                    //服务概要
    /* @Column(name = "svr_cust_no")
     private String svrCustNo;                   //客户编号*/
    @Column(name = "svr_cust_name")
    private String svrCustName;                 //客户名称
    @Column(name = "svr_status")
    private String svrStatus;                   //服务状态
    @Column(name = "svr_request")
    private String svrRequest;                  //服务请求
    @Column(name = "svr_create_id")
    private Long svrCreateId;                   //创建人编号
    @Column(name = "svr_create_by")
    private String svrCreateBy;                 //创建人名称
    @Column(name = "svr_create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrCreateDate;                 //创建时间
    @Column(name = "svr_due_id")
    private Long svrDueId;                      //分配客户经理编号
    @Column(name = "svr_due_to")
    private String svrDueTo;                    //客户经理名称
    @Column(name = "svr_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrDueDate;                    //分配日期
    @Column(name = "svr_deal")
    private String svrDeal;                     //服务处理
    @Column(name = "svr_deal_id")
    private Long svrDealId;                     //服务人id
    @Column(name = "svr_deal_by")
    private String svrDealBy;                   //服务人名称
    @Column(name = "svr_deal_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date svrDealDate;                   //处理日期
    @Column(name = "svr_result")
    private String svrResult;                   //处理结果
    @Column(name = "svr_satisfy")
    private Long svrSatisfy;                    //满意度

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "svr_cust_no")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getSvrId() {
        return svrId;
    }

    public void setSvrId(Long svrId) {
        this.svrId = svrId;
    }


    public String getSvrType() {
        return svrType;
    }

    public void setSvrType(String svrType) {
        this.svrType = svrType;
    }


    public String getSvrTitle() {
        return svrTitle;
    }

    public void setSvrTitle(String svrTitle) {
        this.svrTitle = svrTitle;
    }


/*
  public String getSvrCustNo() {
    return svrCustNo;
  }

  public void setSvrCustNo(String svrCustNo) {
    this.svrCustNo = svrCustNo;
  }

*/

    public String getSvrCustName() {
        return svrCustName;
    }

    public void setSvrCustName(String svrCustName) {
        this.svrCustName = svrCustName;
    }


    public String getSvrStatus() {
        return svrStatus;
    }

    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }


    public String getSvrRequest() {
        return svrRequest;
    }

    public void setSvrRequest(String svrRequest) {
        this.svrRequest = svrRequest;
    }


    public Long getSvrCreateId() {
        return svrCreateId;
    }

    public void setSvrCreateId(Long svrCreateId) {
        this.svrCreateId = svrCreateId;
    }


    public String getSvrCreateBy() {
        return svrCreateBy;
    }

    public void setSvrCreateBy(String svrCreateBy) {
        this.svrCreateBy = svrCreateBy;
    }


    public Date getSvrCreateDate() {
        return svrCreateDate;
    }

    public void setSvrCreateDate(Date svrCreateDate) {
        this.svrCreateDate = svrCreateDate;
    }


    public Long getSvrDueId() {
        return svrDueId;
    }

    public void setSvrDueId(Long svrDueId) {
        this.svrDueId = svrDueId;
    }


    public String getSvrDueTo() {
        return svrDueTo;
    }

    public void setSvrDueTo(String svrDueTo) {
        this.svrDueTo = svrDueTo;
    }


    public Date getSvrDueDate() {
        return svrDueDate;
    }

    public void setSvrDueDate(Date svrDueDate) {
        this.svrDueDate = svrDueDate;
    }


    public String getSvrDeal() {
        return svrDeal;
    }

    public void setSvrDeal(String svrDeal) {
        this.svrDeal = svrDeal;
    }


    public Long getSvrDealId() {
        return svrDealId;
    }

    public void setSvrDealId(Long svrDealId) {
        this.svrDealId = svrDealId;
    }


    public String getSvrDealBy() {
        return svrDealBy;
    }

    public void setSvrDealBy(String svrDealBy) {
        this.svrDealBy = svrDealBy;
    }


    public Date getSvrDealDate() {
        return svrDealDate;
    }

    public void setSvrDealDate(Date svrDealDate) {
        this.svrDealDate = svrDealDate;
    }


    public String getSvrResult() {
        return svrResult;
    }

    public void setSvrResult(String svrResult) {
        this.svrResult = svrResult;
    }


    public Long getSvrSatisfy() {
        return svrSatisfy;
    }

    public void setSvrSatisfy(Long svrSatisfy) {
        this.svrSatisfy = svrSatisfy;
    }

}
