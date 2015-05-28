package com.ys.em.model;

import com.ys.ui.model.AbstractModelBase;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/25/15.
 */
public class SalesOrder extends AbstractModelBase {

    private long salesOrderId;
    private long importControlId;
    private long customerId;
    private String salesOrderNumber;
    private BigDecimal amount;
    private String region;
    private String modelGroup;
    private String stSalesAgentName;
    private Integer activityYear;
    private Integer activityMonthNumber;

    private Date activityDate;
    private String stCustomerName;
    private String btCustomerName;
    private String stChannelName;
    private String productFamilyCode;
    private String forecastGroupCode;
    private Double orderedQuantity;
    private String contractStatusCode;
    private String endUserName;

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
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

    public String getStCustomerName() {
        return stCustomerName;
    }

    public void setStCustomerName(String stCustomerName) {
        this.stCustomerName = stCustomerName;
    }

    public String getBtCustomerName() {
        return btCustomerName;
    }

    public void setBtCustomerName(String btCustomerName) {
        this.btCustomerName = btCustomerName;
    }

    public String getStChannelName() {
        return stChannelName;
    }

    public void setStChannelName(String stChannelName) {
        this.stChannelName = stChannelName;
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
        if (!(o instanceof SalesOrder)) return false;

        SalesOrder that = (SalesOrder) o;

        if (salesOrderId != that.salesOrderId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (salesOrderId ^ (salesOrderId >>> 32));
    }
}
