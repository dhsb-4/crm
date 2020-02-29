package com.t248.crm.lottery.pojo;

import javax.persistence.*;

@Entity
@Table(name = "lty_user")
public class LtyUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "l.id")
  private Long l_Id;
  @Column(name = "l_name")
  private String l_Name;
  @Column(name = "l_password")
  private String l_Password;
  @Column(name = "l_tyoe")
  private String l_Tyoe;
  @Column(name = "l_eamil")
  private String l_Eamil;

  public String getL_Eamil() {
    return l_Eamil;
  }

  public void setL_Eamil(String l_Eamil) {
    this.l_Eamil = l_Eamil;
  }

  public Long getL_Id() {
    return l_Id;
  }

  public void setL_Id(Long l_Id) {
    this.l_Id = l_Id;
  }


  public String getL_Name() {
    return l_Name;
  }

  public void setL_Name(String l_Name) {
    this.l_Name = l_Name;
  }


  public String getL_Password() {
    return l_Password;
  }

  public void setL_Password(String l_Password) {
    this.l_Password = l_Password;
  }


  public String getL_Tyoe() {
    return l_Tyoe;
  }

  public void setL_Tyoe(String l_Tyoe) {
    this.l_Tyoe = l_Tyoe;
  }

}
