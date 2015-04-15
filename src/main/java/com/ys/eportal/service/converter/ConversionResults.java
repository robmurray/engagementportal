package com.ys.eportal.service.converter;

import org.springframework.data.repository.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * Created by rob on 4/14/15.
 */
public class ConversionResults<ID extends Serializable,T> {

    private TreeMap<ID,T>convertedRecords;

    private int numRecordsToProcess;
    private int numRecordsProcessed;
    private int numRecordsInError;
    private int batchId;

    public ConversionResults() {
    }

    public TreeMap<ID,T> getConvertedRecords() {
        return convertedRecords;
    }

    public void setConvertedRecords(TreeMap<ID,T> convertedRecords) {
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

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
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
        return batchId;
    }
}
