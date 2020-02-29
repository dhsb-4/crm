package com.t248.crm.entity;

import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stk_id")
    private Long stkId;                 //编号
    /*@Column(name = "stk_prod_id")
    private Long stkProdId;             //商品编号*/
    @Column(name = "stk_warehouse")
    private String stkWarehouse;        //仓库名称
    @Column(name = "stk_ware")
    private String stkWare;             //货位
    @Column(name = "stk_count")
    private Long stkCount;              //数量
    @Column(name = "stk_memo")
    private String stkMemo;             //备注

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "stk_prod_id")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getStkId() {
        return stkId;
    }

    public void setStkId(Long stkId) {
        this.stkId = stkId;
    }


/*  public Long getStkProdId() {
    return stkProdId;
  }

  public void setStkProdId(Long stkProdId) {
    this.stkProdId = stkProdId;
  }*/


    public String getStkWarehouse() {
        return stkWarehouse;
    }

    public void setStkWarehouse(String stkWarehouse) {
        this.stkWarehouse = stkWarehouse;
    }


    public String getStkWare() {
        return stkWare;
    }

    public void setStkWare(String stkWare) {
        this.stkWare = stkWare;
    }


    public Long getStkCount() {
        return stkCount;
    }

    public void setStkCount(Long stkCount) {
        this.stkCount = stkCount;
    }


    public String getStkMemo() {
        return stkMemo;
    }

    public void setStkMemo(String stkMemo) {
        this.stkMemo = stkMemo;
    }

}
