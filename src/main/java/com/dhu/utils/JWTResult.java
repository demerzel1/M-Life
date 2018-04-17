package com.dhu.utils;

/**
 * Created by demerzel on 2018/4/18.
 */
public class JWTResult {
    private boolean status;
    private String uid;
    private String msg;
    public JWTResult() {
        super();
    }
    public JWTResult(boolean status, String uid, String msg) {
        super();
        this.status = status;
        this.uid = uid;
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
