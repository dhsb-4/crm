package com.t248.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 销售机会实体类
 */
@Entity
@Table(name = "sal_chance")
public class Chance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chc_id")
    private Long chcId;           //编号
    @Column(name = "chc_source")
    private String chcSource;     //机会来源
    @Column(name = "chc_cust_name")
    private String chcCustName;   //客户名称
    @Column(name = "chc_title")
    private String chcTitle;      //概要
    @Column(name = "chc_rate")
    private Long chcRate;       //成功几率
    @Column(name = "chc_linkman")
    private String chcLinkman;    //负责人
    @Column(name = "chc_tel")
    private String chcTel;      //联系电话
    @Column(name = "chc_desc")
    private String chcDesc;     //机会描述
    @Column(name = "chc_create_id")
    private Long chcCreateId;   //创建人id
    @Column(name = "chc_create_by")
    private String chcCreateBy; //创建人
    @Column(name = "chc_create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chcCreateDate;//创建时间
  /*@Column(name = "chc_due_id")
  private Long chcDueId;*/

    @Column(name = "chc_due_to")
    private String chcDueTo;    //指派人

    @Column(name = "chc_due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date chcDueDate;    //指派时间

    @Column(name = "chc_status")
    private String chcStatus;   //状态

    @Column(name = "cust_region")
    private String custRegion;//地区

    @Column(name = "cust_level_label")
    private String custLevelLabel;  //客户等级

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "chc_due_id")
    private User user;

    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }

    public String getCustLevelLabel() {
        return custLevelLabel;
    }

    public void setCustLevelLabel(String custLevelLabel) {
        this.custLevelLabel = custLevelLabel;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getChcId() {
        return chcId;
    }

    public void setChcId(Long chcId) {
        this.chcId = chcId;
    }


    public String getChcSource() {
        return chcSource;
    }

    public void setChcSource(String chcSource) {
        this.chcSource = chcSource;
    }


    public String getChcCustName() {
        return chcCustName;
    }

    public void setChcCustName(String chcCustName) {
        this.chcCustName = chcCustName;
    }


    public String getChcTitle() {
        return chcTitle;
    }

    public void setChcTitle(String chcTitle) {
        this.chcTitle = chcTitle;
    }


    public Long getChcRate() {
        return chcRate;
    }

    public void setChcRate(Long chcRate) {
        this.chcRate = chcRate;
    }


    public String getChcLinkman() {
        return chcLinkman;
    }

    public void setChcLinkman(String chcLinkman) {
        this.chcLinkman = chcLinkman;
    }


    public String getChcTel() {
        return chcTel;
    }

    public void setChcTel(String chcTel) {
        this.chcTel = chcTel;
    }


    public String getChcDesc() {
        return chcDesc;
    }

    public void setChcDesc(String chcDesc) {
        this.chcDesc = chcDesc;
    }


    public Long getChcCreateId() {
        return chcCreateId;
    }

    public void setChcCreateId(Long chcCreateId) {
        this.chcCreateId = chcCreateId;
    }


    public String getChcCreateBy() {
        return chcCreateBy;
    }

    public void setChcCreateBy(String chcCreateBy) {
        this.chcCreateBy = chcCreateBy;
    }


    public String getChcDueTo() {
        return chcDueTo;
    }

    public void setChcDueTo(String chcDueTo) {
        this.chcDueTo = chcDueTo;
    }


    public Date getChcCreateDate() {
        return chcCreateDate;
    }

    public void setChcCreateDate(Date chcCreateDate) {
        this.chcCreateDate = chcCreateDate;
    }

    public Date getChcDueDate() {
        return chcDueDate;
    }

    public void setChcDueDate(Date chcDueDate) {
        this.chcDueDate = chcDueDate;
    }

    public String getChcStatus() {
        return chcStatus;
    }

    public void setChcStatus(String chcStatus) {
        this.chcStatus = chcStatus;
    }

}
