package com.example.marius.mentionexercice;


public class Mention {

    private int mLogoUrl;
    private int mAvatarUrl;
    private String mSource;
    private String mTime;
    private String mText;
    private boolean mRead;
    private boolean mWait;

    public Mention(int logo_url, int avatar_url, String source , String time, String text, boolean read, boolean wait){
        this.mLogoUrl = logo_url;
        this.mAvatarUrl = avatar_url;
        this.mSource = source;
        this.mTime = time;
        this.mText = text;
        this.mRead = read;
        this.mWait = wait;
    }

    //Getters and Setters :
    public boolean isRead() {
        return mRead;
    }
    public void setRead(boolean mRead) {
        this.mRead = mRead;
    }
    public String getText() {
        return mText;
    }
    public void setText(String mText) {
        this.mText = mText;
    }
    public String getTime() {
        return mTime;
    }
    public void setTime(String mTime) {
        this.mTime = mTime;
    }
    public String getSource() {
        return mSource;
    }
    public void setSource(String mSource) {
        this.mSource = mSource;
    }
    public int getAvatarUrl() {
        return mAvatarUrl;
    }
    public void setAvatarUrl(int mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }
    public int getLogoUrl() {
        return mLogoUrl;
    }
    public void setLogoUrl(int mLogoUrl) {
        this.mLogoUrl = mLogoUrl;
    }
    public boolean isWait() {
        return mWait;
    }
    public void setWait(boolean mWait) {
        this.mWait = mWait;
    }


}
