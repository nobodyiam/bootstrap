package com.nobodyiam.web.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jason on 7/19/15.
 */
public class PageModel<T> {
    private volatile int total;
    private volatile List<T> items;

    public PageModel() {
        this(0, Collections.<T>emptyList());
    }

    public PageModel(int total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
