package com.example.marius.mentionexercice;


public class Mention {
    // Indente les variables et initialise-les
    private int mLogoUrl;
    private int mAvatarUrl;
    private String mSource;
    private int mTime;
    private String mText;
    private boolean mRead;
    private boolean mWait;

    // Optimise ton code en utilisant le mot-clé static aux arguments :
    // public Mention(final int logo_url, ...);
    public Mention(int logo_url, int avatar_url, String source, int time, String text, boolean read, boolean wait) {
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

    // Qu'est ce que tu as voulu faire ici ???
    public String getTime() {

        String local = String.valueOf(mTime);
        return local;
    }

    public String getSource() {
        return mSource;
    }

    public int getAvatarUrl() {
        return mAvatarUrl;
    }

    public int getLogoUrl() {
        return mLogoUrl;
    }

    public boolean isWait() {
        return mWait;
    }

    public void setWait(boolean mWait) {
        this.mWait = mWait;
    }

}
