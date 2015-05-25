package com.ys.core.infra.domain.ep;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by rob on 4/8/15.
 */
@Entity
@Table(name = "import_Salesforce")
public class ImportSalesforceEntity implements Serializable {

    @Id
    @GeneratedValue
    private long importSalesforceId;

    @Basic(fetch = FetchType.EAGER)
    @Column(name = "importProcessedDate")
    private Timestamp importProcessedDate;


    @Basic(fetch = FetchType.EAGER)
    @Column(name = "importStatus")
    private String importStatus;

    public long getImportSalesforceId() {
        return importSalesforceId;
    }

    public void setImportSalesforceId(long importSalesforceId) {
        this.importSalesforceId = importSalesforceId;
    }

    public Timestamp getImportProcessedDate() {
        return importProcessedDate;
    }

    public void setImportProcessedDate(Timestamp importProcessedDate) {
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
        if (!(o instanceof ImportSalesforceEntity)) return false;

        ImportSalesforceEntity that = (ImportSalesforceEntity) o;

        if (importSalesforceId != that.importSalesforceId) return false;
        if (importProcessedDate != null ? !importProcessedDate.equals(that.importProcessedDate) : that.importProcessedDate != null)
            return false;
        if (importStatus != null ? !importStatus.equals(that.importStatus) : that.importStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (importSalesforceId ^ (importSalesforceId >>> 32));
        result = 31 * result + (importProcessedDate != null ? importProcessedDate.hashCode() : 0);
        result = 31 * result + (importStatus != null ? importStatus.hashCode() : 0);
        return result;
    }
}
