package com.zhangbin.tool.web;

import java.util.List;

/**
 * ClassName: Page <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/5/5
 * @since JDK1.8
 */
public class Page<T> {

    /**
     * 数据集合
     */
    private List<T> list;

    /**
     * 是否首页
     */
    private boolean hasPreviousPage;

    /**
     * 是否尾页
     */
    private boolean hasNextPage;

    /**
     * 数据总数
     */
    private long total;

    /**
     * 分页总页数
     */
    private long pages;

    private int pageNum;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
