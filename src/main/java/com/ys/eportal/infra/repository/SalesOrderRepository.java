package com.ys.eportal.infra.repository;

import com.ys.eportal.infra.domain.SalesOrderEntity;
import com.ys.eportal.infra.domain.SalesOrderStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


public interface SalesOrderRepository extends CrudRepository<SalesOrderEntity, Long> {

    public List<SalesOrderEntity> findBySalesOrderNumber(String salesOrderNumber);

    public List<SalesOrderEntity> findByImportControlId(long importControlId);
    ///public List<SalesOrderEntity> findByStatus(String status);

    //public List<SalesOrderEntity> findByStCustomerNameLike(String name);
   // public Set<SalesOrderEntity> findByCustomerCustomerId(long customerId);

    @Query(value = "select so.status, count(*) from ep_sales_order so group by so.status", nativeQuery = true)
    public List<Object[]> retrieveOrderStats();


}