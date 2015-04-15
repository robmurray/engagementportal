package com.ys.eportal.infra.domain;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "import_control")
public class ImportControl extends AbstractDomainBase{
    @Id
    @GeneratedValue
    private long importControlId;
    private int numberOfRecords;
    private String fileName;
    private File importFile;
    private String encoding;
    private String status;


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

    public File getImportFile() {
        return importFile;
    }

    public void setImportFile(File importFile) {
        this.importFile = importFile;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportControl)) return false;

        ImportControl that = (ImportControl) o;

        if (importControlId != that.importControlId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (importControlId ^ (importControlId >>> 32));
    }
}
