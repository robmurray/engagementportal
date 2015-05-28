package com.ys.em.infra.domain;

import com.ys.common.infra.domain.AbstractDomainBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "import_control")
public class ImportControlEntity extends AbstractDomainBase {
    @Id
    @GeneratedValue
    private long importControlId;
    private int numberOfRecords;
    private String fileName;

    //private byte[] importFile;
    private String encoding;
    private ImportControlStatus status;


    public long getImportControlId() {
        return importControlId;
    }

    public void setImportControlId(long importControlId) {
        this.importControlId = importControlId;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public ImportControlStatus getStatus() {
        return status;
    }

    public void setStatus(ImportControlStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportControlEntity)) return false;

        ImportControlEntity that = (ImportControlEntity) o;

        if (importControlId != that.importControlId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (importControlId ^ (importControlId >>> 32));
    }
}
