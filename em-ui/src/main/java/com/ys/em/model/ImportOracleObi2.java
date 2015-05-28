package com.ys.em.model;

import java.math.BigDecimal;
import java.util.Date;

public class ImportOracleObi2 {
    private long ImportOracleObiID;

    private String fnetRegion1;
    private String salesAgentName;
    private String stAgentName;
    private Integer activityYear;
    private Integer activityMonth;
    private Date activityDate;
    private String stCustomerName;
    private String stChannelName;
    private String btCustomerName;
    private String orderNumber;
    private String productFamilyCode;
    private String modelGroupCode;
    private String forecastGroupCode;
    private Integer orderedQuantity;
    private BigDecimal netUsd;
    private String contractStatusCode;
    private String endUserName;

    private Date shipDate;
    private Date planningMeetingDate;
    private Date kickoffMeetingDate;
    private Date importProcessedDate;
    private String importStatus;


    public ImportOracleObi2() {
    }

    public long getImportOracleObiID() {
        return ImportOracleObiID;
    }

    public void setImportOracleObiID(long importOracleObiID) {
        ImportOracleObiID = importOracleObiID;
    }

    public String getFnetRegion1() {
        return fnetRegion1;
    }

    public void setFnetRegion1(String fnetRegion1) {
        this.fnetRegion1 = fnetRegion1;
    }

    public String getSalesAgentName() {
        return salesAgentName;
    }

    public void setSalesAgentName(String salesAgentName) {
        this.salesAgentName = salesAgentName;
    }

    public String getStAgentName() {
        return stAgentName;
    }

    public void setStAgentName(String stAgentName) {
        this.stAgentName = stAgentName;
    }

    public Integer getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(Integer activityYear) {
        this.activityYear = activityYear;
    }

    public Integer getActivityMonth() {
        return activityMonth;
    }

    public void setActivityMonth(Integer activityMonth) {
        this.activityMonth = activityMonth;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProductFamilyCode() {
        return productFamilyCode;
    }

    public void setProductFamilyCode(String productFamilyCode) {
        this.productFamilyCode = productFamilyCode;
    }

    public String getModelGroupCode() {
        return modelGroupCode;
    }

    public void setModelGroupCode(String modelGroupCode) {
        this.modelGroupCode = modelGroupCode;
    }

    public String getForecastGroupCode() {
        return forecastGroupCode;
    }

    public void setForecastGroupCode(String forecastGroupCode) {
        this.forecastGroupCode = forecastGroupCode;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public BigDecimal getNetUsd() {
        return netUsd;
    }

    public void setNetUsd(BigDecimal netUsd) {
        this.netUsd = netUsd;
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

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public Date getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Date planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    public Date getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Date kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    public Date getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(Date importProcessedDate) {
        this.importProcessedDate = importProcessedDate;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }
}
