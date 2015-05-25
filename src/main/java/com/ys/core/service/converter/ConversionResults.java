package com.ys.core.service.converter;

import java.util.List;

/**
 * Created by rob on 4/14/15.
 */
public class ConversionResults<T> {

    private List<T> convertedRecords;

    private int numRecordsToProcess;
    private int numRecordsProcessed;
    private int numRecordsImported;
    private int numRecordsInError;
    private long batchId;

    public ConversionResults() {
    }

    public List<T> getConvertedRecords() {
        return convertedRecords;
    }

    public void setConvertedRecords(List<T> convertedRecords) {
        this.convertedRecords = convertedRecords;
    }

    public int getNumRecordsToProcess() {
        return numRecordsToProcess;
    }

    public void setNumRecordsToProcess(int numRecordsToProcess) {
        this.numRecordsToProcess = numRecordsToProcess;
    }

    public int getNumRecordsProcessed() {
        return numRecordsProcessed;
    }

    public void setNumRecordsProcessed(int numRecordsProcessed) {
        this.numRecordsProcessed = numRecordsProcessed;
    }

    public int getNumRecordsInError() {
        return numRecordsInError;
    }

    public void setNumRecordsInError(int numRecordsInError) {
        this.numRecordsInError = numRecordsInError;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public int getNumRecordsImported() {
        return numRecordsImported;
    }

    public void setNumRecordsImported(int numRecordsImported) {
        this.numRecordsImported = numRecordsImported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConversionResults)) return false;

        ConversionResults that = (ConversionResults) o;

        if (batchId != that.batchId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (batchId ^ (batchId >>> 32));
    }
}
