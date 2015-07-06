package com.nobodyiam.web.model;

/**
 * Created by Jason on 7/6/15.
 */
public class ErrorResult {
    private final int code;
    private final String msg;

    public ErrorResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
