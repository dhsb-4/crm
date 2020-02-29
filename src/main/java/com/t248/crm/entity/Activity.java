package com.t248.crm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 交往记录实体类
 */
@Entity
@Table(name = "cst_activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atv_id")
    private Long atvId;                     //编号
    @Column(name = "atv_cust_no")
    private String atvCustNo;               //客户编号
    @Column(name = "atv_cust_name")
    private String atvCustName;             //客户姓名
    @Column(name = "atv_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date atvDate;                   //交往日期
    @Column(name = "atv_place")
    private String atvPlace;                //交往地点
    @Column(name = "atv_title")
    private String atvTitle;                //交往概要
    @Column(name = "atv_desc")
    private String atvDesc;                 //详细信息


    public Long getAtvId() {
        return atvId;
    }

    public void setAtvId(Long atvId) {
        this.atvId = atvId;
    }


    public String getAtvCustNo() {
        return atvCustNo;
    }

    public void setAtvCustNo(String atvCustNo) {
        this.atvCustNo = atvCustNo;
    }


    public String getAtvCustName() {
        return atvCustName;
    }

    public void setAtvCustName(String atvCustName) {
        this.atvCustName = atvCustName;
    }


    public Date getAtvDate() {
        return atvDate;
    }

    public void setAtvDate(Date atvDate) {
        this.atvDate = atvDate;
    }


    public String getAtvPlace() {
        return atvPlace;
    }

    public void setAtvPlace(String atvPlace) {
        this.atvPlace = atvPlace;
    }


    public String getAtvTitle() {
        return atvTitle;
    }

    public void setAtvTitle(String atvTitle) {
        this.atvTitle = atvTitle;
    }


    public String getAtvDesc() {
        return atvDesc;
    }

    public void setAtvDesc(String atvDesc) {
        this.atvDesc = atvDesc;
    }

}
