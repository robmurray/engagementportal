package com.ys.em.infra.domain;


import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "ep_SalesOrder")
public class SalesOrderEntity extends AbstractDomainBase {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "salesOrderId")
    private long salesOrderId;

    private long importControlId;

    @OneToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    private String salesOrderNumber;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "amount")
    private BigDecimal amount;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "region")
    private String region;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "modelGroup")
    private String modelGroup;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "stSalesAgentName")
    private String stSalesAgentName;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "activityYear")
    private Integer activityYear;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "activityMonthNumber")
    private Integer activityMonthNumber;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "activityDate")
    private Date activityDate;


   @OneToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;
/*
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "stCustomerName")
    private String stCustomerName;
*/
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "btCustomerName")
    private String btCustomerName;



    @Basic(fetch = FetchType.EAGER)
    @Column(name = "stChannelName")
    private String stChannelName;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "productFamilyCode")
    private String productFamilyCode;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "forecastGroupCode")
    private String forecastGroupCode;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "orderedQuantity")
    private Double orderedQuantity;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "contractStatusCode")
    private String contractStatusCode;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "endUserName")
    private String endUserName;

    public SalesOrderEntity() {


    }

    public long getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(long salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getModelGroup() {
        return modelGroup;
    }

    public void setModelGroup(String modelGroup) {
        this.modelGroup = modelGroup;
    }

    public String getStSalesAgentName() {
        return stSalesAgentName;
    }

    public void setStSalesAgentName(String stSalesAgentName) {
        this.stSalesAgentName = stSalesAgentName;
    }

    public Integer getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(Integer activityYear) {
        this.activityYear = activityYear;
    }

    public Integer getActivityMonthNumber() {
        return activityMonthNumber;
    }

    public void setActivityMonthNumber(Integer activityMonthNumber) {
        this.activityMonthNumber = activityMonthNumber;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public String getStChannelName() {
        return stChannelName;
    }

    public void setStChannelName(String stChannelName) {
        this.stChannelName = stChannelName;
    }

    public String getBtCustomerName() {
        return btCustomerName;
    }

    public void setBtCustomerName(String btCustomerName) {
        this.btCustomerName = btCustomerName;
    }

    public String getProductFamilyCode() {
        return productFamilyCode;
    }

    public void setProductFamilyCode(String productFamilyCode) {
        this.productFamilyCode = productFamilyCode;
    }

    public String getForecastGroupCode() {
        return forecastGroupCode;
    }

    public void setForecastGroupCode(String forecastGroupCode) {
        this.forecastGroupCode = forecastGroupCode;
    }

    public Double getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Double orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getContractStatusCode() {
        return contractStatusCode;
    }

    public void setContractStatusCode(String contractStatusCode) {
        this.contractStatusCode = contractStatusCode;
    }

    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalesOrderEntity)) return false;

        SalesOrderEntity that = (SalesOrderEntity) o;

        if (salesOrderId != that.salesOrderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (salesOrderId ^ (salesOrderId >>> 32));
    }
}