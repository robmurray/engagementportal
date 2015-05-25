package com.ys.core.infra.domain.ep;

import com.ys.core.infra.domain.AbstractDomainBase;

import javax.persistence.*;

@Entity
@Table(name = "import_oracleobi_stage")
public class ImportOracleObiStage extends AbstractDomainBase {
    @Id
    @GeneratedValue
    private long ImportOracleObiStageId;

    @Basic
    private long importControlId;

    @Basic
    private String importStatus;

    @Basic
    private String orderNumber;

    @Basic
    private String fnetRegion1;

    @Basic
    private String salesAgentName;

    @Basic
    private String stAgentName;

    @Basic
    private String activityYear;
    @Basic
    private String activityMonth;

    @Basic
    private String activityDate;

    @Basic
    private String stCustomerName;
    @Basic
    private String stChannelName;

    @Basic
    private String btCustomerName;

    @Basic
    private String productFamilyCode;
    @Basic
    private String modelGroupCode;
    @Basic
    private String forecastGroupCode;
    @Basic
    private String orderedQuantity;
    @Basic
    private String netUsd;
    @Basic
    private String contractStatusCode;
    @Basic
    private String endUserName;

    public ImportOracleObiStage() {
    }

    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    public long getImportOracleObiStageId() {
        return ImportOracleObiStageId;
    }

    public void setImportOracleObiStageId(long importOracleObiStageId) {
        ImportOracleObiStageId = importOracleObiStageId;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportOracleObiStage)) return false;

        ImportOracleObiStage that = (ImportOracleObiStage) o;

        if (ImportOracleObiStageId != that.ImportOracleObiStageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (ImportOracleObiStageId ^ (ImportOracleObiStageId >>> 32));
    }
}
