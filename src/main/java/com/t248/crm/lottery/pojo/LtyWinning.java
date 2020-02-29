package com.t248.crm.lottery.pojo;

import javax.persistence.*;

@Entity
@Table(name = "lty_winning")
public class LtyWinning {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "w_id")
  private Long w_Id;
  @Column(name = "w_name")
  private String w_Name;
  @Column(name = "w_type")
  private String w_Type;
  @Column(name = "w_prize")
  private String w_Prize;


  public Long getW_Id() {
    return w_Id;
  }

  public void setW_Id(Long w_Id) {
    this.w_Id = w_Id;
  }


  public String getW_Name() {
    return w_Name;
  }

  public void setW_Name(String w_Name) {
    this.w_Name = w_Name;
  }


  public String getW_Type() {
    return w_Type;
  }

  public void setW_Type(String w_Type) {
    this.w_Type = w_Type;
  }


  public String getW_Prize() {
    return w_Prize;
  }

  public void setW_Prize(String w_Prize) {
    this.w_Prize = w_Prize;
  }

}
