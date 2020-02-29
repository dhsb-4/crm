package com.t248.crm.repository;

import com.t248.crm.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

    /**
     * 根据编号查询
     */
    public Orders findByOdrId(Long odrId);

    @Modifying
    @Transactional
    @Query(value = "delete from Orders where odrCustomerNo=?1")
    public void oDel(String custNo);

    public List<Orders> findByOdrCustomerNo(String custNo);

    @Query(value = "SELECT odr_status,odr_addr, `odr_id`, `odr_customer_no`,MAX(`odr_date`)as odr_date\n" +
            "FROM `orders`\n" +
            "WHERE TIMESTAMPADD(MONTH, 6, `odr_date`)<=NOW()\n" +
            "GROUP BY `odr_customer_no`", nativeQuery = true)
    public List<Orders> findList();
}
