package com.vinsofts.asus.apphero.model;

/**
 * Created by AT on 11/6/2017.
 */

public class Post {

    /**
     * mCode : 200
     * mErr : 0
     * mUser : {"user_id":"1838","user_name":"ngocbichcnttsp2@gmail.com","email":"ngocbichcnttsp2@gmail.com","password":"7b9e49b79200cb8069dff056cd6aba2b","salt":null,"sex":"0","birthday":"0","addresss":"","status":"1","created_time":"1509959861","site_id":"1913","group":"","app_token":"d7657583058394c828ee150fada65345"}
     * mMsgdefault : OK
     */

    private int mCode;
    private int mErr;
    private User mUser;
    private String mMsgdefault;

    public int getCode() {
        return mCode;
    }

    public void setCode(int mCode) {
        this.mCode = mCode;
    }

    public int getErr() {
        return mErr;
    }

    public void setErr(int mErr) {
        this.mErr = mErr;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public String getMsgdefault() {
        return mMsgdefault;
    }

    public void setMsgdefault(String mMsgdefault) {
        this.mMsgdefault = mMsgdefault;
    }

}
