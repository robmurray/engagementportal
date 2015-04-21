package com.ys.eportal.infra.domain;

/**
 * Created by rob on 4/20/15.
 */
public class SalesOrderStats {
    private String status;
    private int count;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
