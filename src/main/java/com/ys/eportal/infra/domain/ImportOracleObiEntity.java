package com.ys.eportal.infra.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@javax.persistence.Table(name = "import_OracleOBI", schema = "", catalog = "engagementportal")
public class ImportOracleObiEntity extends AbstractDomainBase {
    @Id
    @GeneratedValue
    private long ImportOracleObiID;
    private long importControlId;

    private String fnetRegion1;
    private String salesAgentName;
    private String stAgentName;
    private Integer activityYear;
    private Integer activityMonth;
    private Date activityDate;
    private Date shipDate;
    private Date planningMeetingDate;
    private Date kickoffMeetingDate;
    private String stCustomerName;
    private String stChannelName;
    private String btCustomerName;
    private Integer orderNumber;
    private String productFamilyCode;
    private String modelGroupCode;
    private String forecastGroupCode;
    private Integer orderedQuantity;
    private BigDecimal netUsd;
    private String contractStatusCode;
    private String endUserName;
    private Date importProcessedDate;
    private String importStatus;

    public long getImportOracleObiID() {
        return ImportOracleObiID;
    }

    public void setImportOracleObiID(long importOracleObiID) {
        ImportOracleObiID = importOracleObiID;
    }

    public String getStAgentName() {
        return stAgentName;
    }

    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public void setStAgentName(String stAgentName) {
        this.stAgentName = stAgentName;
    }

    @Basic
    @javax.persistence.Column(name = "FNET Region 1")
    public String getFnetRegion1() {
        return fnetRegion1;
    }

    public void setFnetRegion1(String fnetRegion1) {
        this.fnetRegion1 = fnetRegion1;
    }

    @Basic
    @javax.persistence.Column(name = "salesAgentName")
    public String getSalesAgentName() {
        return salesAgentName;
    }

    public void setSalesAgentName(String salesAgentName) {
        this.salesAgentName = salesAgentName;
    }

    @Basic
    @javax.persistence.Column(name = "activityYear")
    public Integer getActivityYear() {
        return activityYear;
    }

    public void setActivityYear(Integer activityYear) {
        this.activityYear = activityYear;
    }

    @Basic
    @javax.persistence.Column(name = "activityMonth")
    public Integer getActivityMonth() {
        return activityMonth;
    }

    public void setActivityMonth(Integer activityMonth) {
        this.activityMonth = activityMonth;
    }

    @Basic
    @javax.persistence.Column(name = "activityDate")
    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    @Basic
    @javax.persistence.Column(name = "shipDate")
    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    @Basic
    @javax.persistence.Column(name = "planningMeetingDate")
    public Date getPlanningMeetingDate() {
        return planningMeetingDate;
    }

    public void setPlanningMeetingDate(Date planningMeetingDate) {
        this.planningMeetingDate = planningMeetingDate;
    }

    @Basic
    @javax.persistence.Column(name = "kickoffMeetingDate")
    public Date getKickoffMeetingDate() {
        return kickoffMeetingDate;
    }

    public void setKickoffMeetingDate(Date kickoffMeetingDate) {
        this.kickoffMeetingDate = kickoffMeetingDate;
    }

    @Basic
    @javax.persistence.Column(name = "stCustomerName")
    public String getStCustomerName() {
        return stCustomerName;
    }

    public void setStCustomerName(String stCustomerName) {
        this.stCustomerName = stCustomerName;
    }

    @Basic
    @javax.persistence.Column(name = "stChannelName")
    public String getStChannelName() {
        return stChannelName;
    }

    public void setStChannelName(String stChannelName) {
        this.stChannelName = stChannelName;
    }

    @Basic
    @javax.persistence.Column(name = "btCustomerName")
    public String getBtCustomerName() {
        return btCustomerName;
    }

    public void setBtCustomerName(String btCustomerName) {
        this.btCustomerName = btCustomerName;
    }

    @Basic
    @javax.persistence.Column(name = "orderNumber")
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @javax.persistence.Column(name = "productFamilyCode")
    public String getProductFamilyCode() {
        return productFamilyCode;
    }

    public void setProductFamilyCode(String productFamilyCode) {
        this.productFamilyCode = productFamilyCode;
    }

    @Basic
    @javax.persistence.Column(name = "modelGroupCode")
    public String getModelGroupCode() {
        return modelGroupCode;
    }

    public void setModelGroupCode(String modelGroupCode) {
        this.modelGroupCode = modelGroupCode;
    }

    @Basic
    @javax.persistence.Column(name = "forecastGroupCode")
    public String getForecastGroupCode() {
        return forecastGroupCode;
    }

    public void setForecastGroupCode(String forecastGroupCode) {
        this.forecastGroupCode = forecastGroupCode;
    }

    @Basic
    @javax.persistence.Column(name = "orderedQuantity")
    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    @Basic
    @javax.persistence.Column(name = "netUSD")
    public BigDecimal getNetUsd() {
        return netUsd;
    }

    public void setNetUsd(BigDecimal netUsd) {
        this.netUsd = netUsd;
    }

    @Basic
    @javax.persistence.Column(name = "contractStatusCode")
    public String getContractStatusCode() {
        return contractStatusCode;
    }

    public void setContractStatusCode(String contractStatusCode) {
        this.contractStatusCode = contractStatusCode;
    }

    @Basic
    @javax.persistence.Column(name = "endUserName")
    public String getEndUserName() {
        return endUserName;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    @Basic
    @javax.persistence.Column(name = "importProcessedDate")
    public Date getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(Date importProcessedDate) {
        this.importProcessedDate = importProcessedDate;
    }

    @Basic
    @javax.persistence.Column(name = "importStatus")
    public String getImportStatus() {
        return importStatus;
    }

    public void setImportStatus(String importStatus) {
        this.importStatus = importStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImportOracleObiEntity that = (ImportOracleObiEntity) o;

        if (activityDate != null ? !activityDate.equals(that.activityDate) : that.activityDate != null) return false;
        if (activityMonth != null ? !activityMonth.equals(that.activityMonth) : that.activityMonth != null)
            return false;
        if (activityYear != null ? !activityYear.equals(that.activityYear) : that.activityYear != null) return false;
        if (btCustomerName != null ? !btCustomerName.equals(that.btCustomerName) : that.btCustomerName != null)
            return false;
        if (contractStatusCode != null ? !contractStatusCode.equals(that.contractStatusCode) : that.contractStatusCode != null)
            return false;
        if (endUserName != null ? !endUserName.equals(that.endUserName) : that.endUserName != null) return false;
        if (fnetRegion1 != null ? !fnetRegion1.equals(that.fnetRegion1) : that.fnetRegion1 != null) return false;
        if (forecastGroupCode != null ? !forecastGroupCode.equals(that.forecastGroupCode) : that.forecastGroupCode != null)
            return false;
        if (importProcessedDate != null ? !importProcessedDate.equals(that.importProcessedDate) : that.importProcessedDate != null)
            return false;
        if (importStatus != null ? !importStatus.equals(that.importStatus) : that.importStatus != null) return false;
        if (kickoffMeetingDate != null ? !kickoffMeetingDate.equals(that.kickoffMeetingDate) : that.kickoffMeetingDate != null)
            return false;
        if (modelGroupCode != null ? !modelGroupCode.equals(that.modelGroupCode) : that.modelGroupCode != null)
            return false;
        if (netUsd != null ? !netUsd.equals(that.netUsd) : that.netUsd != null) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (orderedQuantity != null ? !orderedQuantity.equals(that.orderedQuantity) : that.orderedQuantity != null)
            return false;
        if (planningMeetingDate != null ? !planningMeetingDate.equals(that.planningMeetingDate) : that.planningMeetingDate != null)
            return false;
        if (productFamilyCode != null ? !productFamilyCode.equals(that.productFamilyCode) : that.productFamilyCode != null)
            return false;
        if (salesAgentName != null ? !salesAgentName.equals(that.salesAgentName) : that.salesAgentName != null)
            return false;
        if (shipDate != null ? !shipDate.equals(that.shipDate) : that.shipDate != null) return false;
        if (stChannelName != null ? !stChannelName.equals(that.stChannelName) : that.stChannelName != null)
            return false;
        if (stCustomerName != null ? !stCustomerName.equals(that.stCustomerName) : that.stCustomerName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fnetRegion1 != null ? fnetRegion1.hashCode() : 0;
        result = 31 * result + (salesAgentName != null ? salesAgentName.hashCode() : 0);
        result = 31 * result + (activityYear != null ? activityYear.hashCode() : 0);
        result = 31 * result + (activityMonth != null ? activityMonth.hashCode() : 0);
        result = 31 * result + (activityDate != null ? activityDate.hashCode() : 0);
        result = 31 * result + (shipDate != null ? shipDate.hashCode() : 0);
        result = 31 * result + (planningMeetingDate != null ? planningMeetingDate.hashCode() : 0);
        result = 31 * result + (kickoffMeetingDate != null ? kickoffMeetingDate.hashCode() : 0);
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
        result = 31 * result + (importProcessedDate != null ? importProcessedDate.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        return result;
    }
}
