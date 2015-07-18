package com.nobodyiam.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jason on 7/5/15.
 */
public class Greeting implements Serializable{
    private long id;
    private String content;
    private Date updateTime;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
