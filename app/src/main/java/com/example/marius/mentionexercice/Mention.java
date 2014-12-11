package com.example.marius.mentionexercice;


import android.text.SpannableString;

public class Mention {

    private int mLogoUrl = -1;
    private int mAvatarUrl = -1;
    private String mSource = null;
    private int mTime = -1;
    private SpannableString mText = null;
    private State mState = null;


    public Mention(final int logo_url, final int avatar_url, final String source, final int time, final SpannableString text, final State state) {
        this.mLogoUrl = logo_url;
        this.mAvatarUrl = avatar_url;
        this.mSource = source;
        this.mTime = time;
        this.mText = text;
        this.mState = state;
    }

    //Getters and Setters :
    public State getState() {
        return mState;
    }

    public void setState(State mState) {
        this.mState = mState;
    }

    public SpannableString getText() {
        return mText;
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

    public enum State {
        UNREAD,
        READ,
        WAIT,
    }


}
