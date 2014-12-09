package com.example.marius.mentionexercice;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MentionAdapter extends ArrayAdapter<Mention> {

    // Il y a aussi la possibilité de passer le layout au constructeur de MentionAdapter. Tu aurais donc pu appeler super(context, resource, mentions)
    public MentionAdapter(Context context, ArrayList<Mention> mentions) {
        super(context, 0, mentions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Mention mention = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mention, parent, false);

            viewHolder.mLogo = (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.mAvatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.texte);
            viewHolder.mTime = (TextView) convertView.findViewById(R.id.time);
            viewHolder.mSource = (TextView) convertView.findViewById(R.id.source);
            viewHolder.mRead = (ImageView) convertView.findViewById(R.id.bluebutton);
            viewHolder.mRlayout = (RelativeLayout) convertView.findViewById(R.id.mainlayout);
            viewHolder.mWait = (ProgressBar) convertView.findViewById(R.id.progressbar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Un peu rébarbatif ! Tu pourrais utiliser un design patter "Etat". Exemple :
        // public enum State {
        //        UNREAD,
        //        WAIT,
        //        READ
        //    }
        //
        //private State mState = null;
        //
        //if (mention.getState() == State.UNREAD) {...}
        //else if (mention.getState() == State.WAIT) {...}
        //else if ...
        if(mention.isWait()){
            viewHolder.mWait.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.mWait.setVisibility(View.VISIBLE);
        }

        if (mention.isRead()) {
            viewHolder.mRead.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mRead.setVisibility(View.INVISIBLE);
        }
        viewHolder.mLogo.setImageResource(mention.getLogoUrl());
        viewHolder.mAvatar.setImageResource(mention.getAvatarUrl());
        viewHolder.mText.setText(mention.getText());
        viewHolder.mTime.setText(mention.getTime());
        viewHolder.mSource.setText(mention.getSource());

        if (position % 2 == 1) {
            viewHolder.mRlayout.setBackgroundResource(R.color.darkgray);
        } else {
            viewHolder.mRlayout.setBackgroundResource(R.color.lightgray);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView mText;
        TextView mTime;
        TextView mSource;
        ImageView mLogo;
        ImageView mAvatar;
        ImageView mRead;
        RelativeLayout mRlayout;
        ProgressBar mWait;
    }
}
