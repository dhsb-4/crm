package com.t248.crm.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role_right")
public class SysRoleRight implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rfId;
 /* @Column(name = "rf_role_id")
  private Long rfRoleId;
  @Column(name = "rf_right_code")
  private String rfRightCode;*/

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rf_role_id")
    private Role role;

    @ManyToOne(targetEntity = Right.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "rf_right_code")
    private Right right;


    public Long getRfId() {
        return rfId;
    }

    public void setRfId(Long rfId) {
        this.rfId = rfId;
    }


/*  public Long getRfRoleId() {
    return rfRoleId;
  }

  public void setRfRoleId(Long rfRoleId) {
    this.rfRoleId = rfRoleId;
  }


  public String getRfRightCode() {
    return rfRightCode;
  }

  public void setRfRightCode(String rfRightCode) {
    this.rfRightCode = rfRightCode;
  }*/

}
