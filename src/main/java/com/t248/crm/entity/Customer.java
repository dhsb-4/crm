package com.t248.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 客户信息实体类
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;                      //编号
    @Id
    @Column(name = "cust_no")
    private String custNo;                    //客户编号
    @Column(name = "cust_name")
    private String custName;                  //客户名称
    @Column(name = "cust_region")
    private String custRegion;                //地区
    @Column(name = "cust_manager_id")
    private Long custManagerId;               //客户经理编号
    @Column(name = "cust_manager_name")
    private String custManagerName;           //客户经理名称
    @Column(name = "cust_level")
    private String custLevel;                   //客户等级
    @Column(name = "cust_level_label")
    private String custLevelLabel;            //客户等级名称
    @Column(name = "cust_satisfy")
    private Long custSatisfy;                 //满意度
    @Column(name = "cust_credit")
    private Long custCredit;                  //信用度
    @Column(name = "cust_addr")
    private String custAddr;                  //地址
    @Column(name = "cust_zip")
    private String custZip;                   //邮政编码
    @Column(name = "cust_tel")
    private String custTel;                   //电话
    @Column(name = "cust_fax")
    private String custFax;                   //传真
    @Column(name = "cust_website")
    private String custWebsite;               //网址
    @Column(name = "cust_licence_no")
    private String custLicenceNo;             //营业执照注册号
    @Column(name = "cust_chieftain")
    private String custChieftain;             //法人
    @Column(name = "cust_bankroll")
    private Long custBankroll;                //注册资金
    @Column(name = "cust_turnover")
    private Long custTurnover;                //营业额
    @Column(name = "cust_bank")
    private String custBank;                  //开户银行
    @Column(name = "cust_bank_account")
    private String custBankAccount;           //银行账号
    @Column(name = "cust_local_tax_no")
    private String custLocalTaxNo;            //地税登记号
    @Column(name = "cust_national_tax_no")
    private String custNationalTaxNo;         //国税登记号
    @Column(name = "cust_status")
    private String custStatus;                //客户状态

    @OneToMany(targetEntity = CstService.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonIgnore
    private Set<CstService> cusServices = new HashSet<CstService>();

    public Set<CstService> getCusServices() {
        return cusServices;
    }

    public void setCusServices(Set<CstService> cusServices) {
        this.cusServices = cusServices;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }


    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }


    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }


    public String getCustRegion() {
        return custRegion;
    }

    public void setCustRegion(String custRegion) {
        this.custRegion = custRegion;
    }


    public Long getCustManagerId() {
        return custManagerId;
    }

    public void setCustManagerId(Long custManagerId) {
        this.custManagerId = custManagerId;
    }


    public String getCustManagerName() {
        return custManagerName;
    }

    public void setCustManagerName(String custManagerName) {
        this.custManagerName = custManagerName;
    }


    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }


    public String getCustLevelLabel() {
        return custLevelLabel;
    }

    public void setCustLevelLabel(String custLevelLabel) {
        this.custLevelLabel = custLevelLabel;
    }


    public Long getCustSatisfy() {
        return custSatisfy;
    }

    public void setCustSatisfy(Long custSatisfy) {
        this.custSatisfy = custSatisfy;
    }


    public Long getCustCredit() {
        return custCredit;
    }

    public void setCustCredit(Long custCredit) {
        this.custCredit = custCredit;
    }


    public String getCustAddr() {
        return custAddr;
    }

    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }


    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }


    public String getCustTel() {
        return custTel;
    }

    public void setCustTel(String custTel) {
        this.custTel = custTel;
    }


    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }


    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }


    public String getCustLicenceNo() {
        return custLicenceNo;
    }

    public void setCustLicenceNo(String custLicenceNo) {
        this.custLicenceNo = custLicenceNo;
    }


    public String getCustChieftain() {
        return custChieftain;
    }

    public void setCustChieftain(String custChieftain) {
        this.custChieftain = custChieftain;
    }


    public Long getCustBankroll() {
        return custBankroll;
    }

    public void setCustBankroll(Long custBankroll) {
        this.custBankroll = custBankroll;
    }


    public Long getCustTurnover() {
        return custTurnover;
    }

    public void setCustTurnover(Long custTurnover) {
        this.custTurnover = custTurnover;
    }


    public String getCustBank() {
        return custBank;
    }

    public void setCustBank(String custBank) {
        this.custBank = custBank;
    }


    public String getCustBankAccount() {
        return custBankAccount;
    }

    public void setCustBankAccount(String custBankAccount) {
        this.custBankAccount = custBankAccount;
    }


    public String getCustLocalTaxNo() {
        return custLocalTaxNo;
    }

    public void setCustLocalTaxNo(String custLocalTaxNo) {
        this.custLocalTaxNo = custLocalTaxNo;
    }


    public String getCustNationalTaxNo() {
        return custNationalTaxNo;
    }

    public void setCustNationalTaxNo(String custNationalTaxNo) {
        this.custNationalTaxNo = custNationalTaxNo;
    }


    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

}
