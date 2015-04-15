package com.ys.eportal.model;

import java.math.BigDecimal;
import java.util.Date;

public class ImportOracleObi {
    private long ImportOracleObiID;

    private String fnetRegion1;
    private String salesAgentName;
    private String stAgentName;
    private String activityYear;
    private String activityMonth;
    private String activityDate;
    private String stCustomerName;
    private String stChannelName;
    private String btCustomerName;
    private String orderNumber;
    private String productFamilyCode;
    private String modelGroupCode;
    private String forecastGroupCode;
    private String orderedQuantity;
    private String netUsd;
    private String contractStatusCode;
    private String endUserName;

    private String shipDate;
    private String planningMeetingDate;
    private String kickoffMeetingDate;
    private String importProcessedDate;
    private String importStatus;


    public ImportOracleObi() {
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

    public String getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(String activityYear) {
        this.activityYear = activityYear;
    }

    public String getActivityMonth() {
        return activityMonth;
    }

    public void setActivityMonth(String activityMonth) {
        this.activityMonth = activityMonth;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
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

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getNetUsd() {
        return netUsd;
    }

    public void setNetUsd(String netUsd) {
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

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(String planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    public String getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(String kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    public String getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(String importProcessedDate) {
        this.importProcessedDate = importProcessedDate;
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportOracleObi)) return false;

        ImportOracleObi oracleObi = (ImportOracleObi) o;

        if (ImportOracleObiID != oracleObi.ImportOracleObiID) return false;
        if (activityDate != null ? !activityDate.equals(oracleObi.activityDate) : oracleObi.activityDate != null)
            return false;
        if (activityMonth != null ? !activityMonth.equals(oracleObi.activityMonth) : oracleObi.activityMonth != null)
            return false;
        if (activityYear != null ? !activityYear.equals(oracleObi.activityYear) : oracleObi.activityYear != null)
            return false;
        if (btCustomerName != null ? !btCustomerName.equals(oracleObi.btCustomerName) : oracleObi.btCustomerName != null)
            return false;
        if (contractStatusCode != null ? !contractStatusCode.equals(oracleObi.contractStatusCode) : oracleObi.contractStatusCode != null)
            return false;
        if (endUserName != null ? !endUserName.equals(oracleObi.endUserName) : oracleObi.endUserName != null)
            return false;
        if (fnetRegion1 != null ? !fnetRegion1.equals(oracleObi.fnetRegion1) : oracleObi.fnetRegion1 != null)
            return false;
        if (forecastGroupCode != null ? !forecastGroupCode.equals(oracleObi.forecastGroupCode) : oracleObi.forecastGroupCode != null)
            return false;
        if (importProcessedDate != null ? !importProcessedDate.equals(oracleObi.importProcessedDate) : oracleObi.importProcessedDate != null)
            return false;
        if (importStatus != null ? !importStatus.equals(oracleObi.importStatus) : oracleObi.importStatus != null)
            return false;
        if (kickoffMeetingDate != null ? !kickoffMeetingDate.equals(oracleObi.kickoffMeetingDate) : oracleObi.kickoffMeetingDate != null)
            return false;
        if (modelGroupCode != null ? !modelGroupCode.equals(oracleObi.modelGroupCode) : oracleObi.modelGroupCode != null)
            return false;
        if (netUsd != null ? !netUsd.equals(oracleObi.netUsd) : oracleObi.netUsd != null) return false;
        if (orderNumber != null ? !orderNumber.equals(oracleObi.orderNumber) : oracleObi.orderNumber != null)
            return false;
        if (orderedQuantity != null ? !orderedQuantity.equals(oracleObi.orderedQuantity) : oracleObi.orderedQuantity != null)
            return false;
        if (planningMeetingDate != null ? !planningMeetingDate.equals(oracleObi.planningMeetingDate) : oracleObi.planningMeetingDate != null)
            return false;
        if (productFamilyCode != null ? !productFamilyCode.equals(oracleObi.productFamilyCode) : oracleObi.productFamilyCode != null)
            return false;
        if (salesAgentName != null ? !salesAgentName.equals(oracleObi.salesAgentName) : oracleObi.salesAgentName != null)
            return false;
        if (shipDate != null ? !shipDate.equals(oracleObi.shipDate) : oracleObi.shipDate != null) return false;
        if (stAgentName != null ? !stAgentName.equals(oracleObi.stAgentName) : oracleObi.stAgentName != null)
            return false;
        if (stChannelName != null ? !stChannelName.equals(oracleObi.stChannelName) : oracleObi.stChannelName != null)
            return false;
        if (stCustomerName != null ? !stCustomerName.equals(oracleObi.stCustomerName) : oracleObi.stCustomerName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ImportOracleObiID ^ (ImportOracleObiID >>> 32));
        result = 31 * result + (fnetRegion1 != null ? fnetRegion1.hashCode() : 0);
        result = 31 * result + (salesAgentName != null ? salesAgentName.hashCode() : 0);
        result = 31 * result + (stAgentName != null ? stAgentName.hashCode() : 0);
        result = 31 * result + (activityYear != null ? activityYear.hashCode() : 0);
        result = 31 * result + (activityMonth != null ? activityMonth.hashCode() : 0);
        result = 31 * result + (activityDate != null ? activityDate.hashCode() : 0);
        result = 31 * result + (stCustomerName != null ? stCustomerName.hashCode() : 0);
        result = 31 * result + (stChannelName != null ? stChannelName.hashCode() : 0);
        result = 31 * result + (btCustomerName != null ? btCustomerName.hashCode() : 0);
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (productFamilyCode != null ? productFamilyCode.hashCode() : 0);
        result = 31 * result + (modelGroupCode != null ? modelGroupCode.hashCode() : 0);
        result = 31 * result + (forecastGroupCode != null ? forecastGroupCode.hashCode() : 0);
        result = 31 * result + (orderedQuantity != null ? orderedQuantity.hashCode() : 0);
        result = 31 * result + (netUsd != null ? netUsd.hashCode() : 0);
        result = 31 * result + (contractStatusCode != null ? contractStatusCode.hashCode() : 0);
        result = 31 * result + (endUserName != null ? endUserName.hashCode() : 0);
        result = 31 * result + (shipDate != null ? shipDate.hashCode() : 0);
        result = 31 * result + (planningMeetingDate != null ? planningMeetingDate.hashCode() : 0);
        result = 31 * result + (kickoffMeetingDate != null ? kickoffMeetingDate.hashCode() : 0);
        result = 31 * result + (importProcessedDate != null ? importProcessedDate.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        return result;
    }
}
