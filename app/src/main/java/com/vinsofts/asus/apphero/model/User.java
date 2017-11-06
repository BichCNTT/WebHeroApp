package com.vinsofts.asus.apphero.model;

/**
 * Created by AT on 11/2/2017.
 */

public class User {
    private int mId;
    private String mEmail;
    private String mPassword;
    private String mSalt;
    private String mSex;
    private String mBirthDay;
    private String mAddress;
    private String mStatus;
    private String mCreateTime;
    private String mSiteId;
    private String mGroup;
    private String mAppToken;

    public User(int mId, String mEmail, String mPassword, String mSalt, String mSex, String mBirthDay, String mAddress, String mStatus, String mCreateTime, String mSiteId, String mGroup, String mAppToken) {
        this.mId = mId;
        this.mEmail = mEmail;
        this.mPassword = mPassword;
        this.mSalt = mSalt;
        this.mSex = mSex;
        this.mBirthDay = mBirthDay;
        this.mAddress = mAddress;
        this.mStatus = mStatus;
        this.mCreateTime = mCreateTime;
        this.mSiteId = mSiteId;
        this.mGroup = mGroup;
        this.mAppToken = mAppToken;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getSalt() {
        return mSalt;
    }

    public void setSalt(String mSalt) {
        this.mSalt = mSalt;
    }

    public String getSex() {
        return mSex;
    }

    public void setSex(String mSex) {
        this.mSex = mSex;
    }

    public String getBirthDay() {
        return mBirthDay;
    }

    public void setBirthDay(String mBirthDay) {
        this.mBirthDay = mBirthDay;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String mCreateTime) {
        this.mCreateTime = mCreateTime;
    }

    public String getSiteId() {
        return mSiteId;
    }

    public void setSiteId(String mSiteId) {
        this.mSiteId = mSiteId;
    }

    public String getGroup() {
        return mGroup;
    }

    public void setGroup(String mGroup) {
        this.mGroup = mGroup;
    }

    public String getAppToken() {
        return mAppToken;
    }

    public void setAppToken(String mAppToken) {
        this.mAppToken = mAppToken;
    }
}