package com.ys.em.model;

/**
 * Created by rob on 4/21/15.
 */
public class ProjectSearchConfig {
    private int searchPageSize = 100;

    public ProjectSearchConfig() {
    }

    public int getSearchPageSize() {
        return searchPageSize;
    }

    public void setSearchPageSize(int searchPageSize) {
        this.searchPageSize = searchPageSize;
    }
}
