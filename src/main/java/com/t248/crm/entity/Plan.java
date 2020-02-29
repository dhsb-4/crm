package com.t248.crm.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 客户开发计划实体类
 */
@Entity
@Table(name = "sal_plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pla_id")
    private long plaId;       //编号
    @Column(name = "pla_chc_id")
    private long plaChcId;    //机会销售编号
    @Column(name = "pla_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date plaDate;     //日期
    @Column(name = "pla_todo")
    private String plaTodo;   //计划项
    @Column(name = "pla_result")
    private String plaResult;//执行结果


    public long getPlaId() {
        return plaId;
    }

    public void setPlaId(long plaId) {
        this.plaId = plaId;
    }


    public long getPlaChcId() {
        return plaChcId;
    }

    public void setPlaChcId(long plaChcId) {
        this.plaChcId = plaChcId;
    }


    public Date getPlaDate() {
        return plaDate;
    }

    public void setPlaDate(Date plaDate) {
        this.plaDate = plaDate;
    }


    public String getPlaTodo() {
        return plaTodo;
    }

    public void setPlaTodo(String plaTodo) {
        this.plaTodo = plaTodo;
    }


    public String getPlaResult() {
        return plaResult;
    }

    public void setPlaResult(String plaResult) {
        this.plaResult = plaResult;
    }

}
