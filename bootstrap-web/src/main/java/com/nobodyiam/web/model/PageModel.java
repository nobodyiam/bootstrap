package com.nobodyiam.web.model;

import java.util.List;

/**
 * Created by Jason on 7/19/15.
 */
public class PageModel<T> {
    private final int total;
    private final List<T> items;

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
}
