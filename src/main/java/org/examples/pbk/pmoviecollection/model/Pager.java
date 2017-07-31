package org.examples.pbk.pmoviecollection.model;

/**
 * Created by Vitalik on 26.07.2017.
 */
public class Pager {
    private int page;
    private int totalPages;

    public Pager(int page, int totalPages) {
        this.page = page;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
