package com.ys.em.infra.repository;

import com.ys.em.infra.domain.SalesOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface SalesOrderRepository extends CrudRepository<SalesOrderEntity, Long> {

    public List<SalesOrderEntity> findBySalesOrderNumber(String salesOrderNumber);

    public List<SalesOrderEntity> findByImportControlId(long importControlId);
    ///public List<SalesOrderEntity> findByStatus(String status);

    //public List<SalesOrderEntity> findByStCustomerNameLike(String name);
   // public Set<SalesOrderEntity> findByCustomerCustomerId(long customerId);

    @Query(value = "select so.status, count(*) from ep_sales_order so group by so.status", nativeQuery = true)
    public List<Object[]> retrieveOrderStats();


}