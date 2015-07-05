package com.nobodyiam.dto;

import java.io.Serializable;

/**
 * Created by Jason on 7/5/15.
 */
public class Greeting implements Serializable{
    private final Long id;
    private final String content;

    public Greeting(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
