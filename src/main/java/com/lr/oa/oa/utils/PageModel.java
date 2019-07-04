package com.lr.oa.oa.utils;

public class PageModel {

    /** 分页总数据条数  */
    private int recordCount;
    /** 当前页面 */
    private int pageNumber=1;
    /** 每页分多少条数据   */
    private int pageSize = 4;

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
