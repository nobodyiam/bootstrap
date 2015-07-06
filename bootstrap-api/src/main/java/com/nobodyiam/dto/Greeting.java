package com.nobodyiam.dto;

import java.io.Serializable;

/**
 * Created by Jason on 7/5/15.
 */
public class Greeting implements Serializable{
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
