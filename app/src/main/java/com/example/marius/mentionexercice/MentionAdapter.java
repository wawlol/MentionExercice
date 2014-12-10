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

            viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            viewHolder.text = (TextView) convertView.findViewById(R.id.texte);
            viewHolder.time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.source = (TextView) convertView.findViewById(R.id.source);
            viewHolder.bluebutton = convertView.findViewById(R.id.bluebutton);
            viewHolder.rLayout = (RelativeLayout) convertView.findViewById(R.id.mainlayout);
            viewHolder.waitingAnim = (ProgressBar) convertView.findViewById(R.id.progressbar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mention.getState() == Mention.State.UNREAD) {
            viewHolder.bluebutton.setVisibility(View.VISIBLE);
            viewHolder.waitingAnim.setVisibility(View.INVISIBLE);
        } else if (mention.getState() == Mention.State.READ) {
            viewHolder.waitingAnim.setVisibility(View.INVISIBLE);
            viewHolder.bluebutton.setVisibility(View.INVISIBLE);
        } else if (mention.getState() == Mention.State.WAIT) {
            viewHolder.bluebutton.setVisibility(View.INVISIBLE);
            viewHolder.waitingAnim.setVisibility(View.VISIBLE);
        }

        viewHolder.logo.setImageResource(mention.getLogoUrl());
        viewHolder.avatar.setImageResource(mention.getAvatarUrl());
        viewHolder.text.setText(mention.getText());
        viewHolder.time.setText(mention.getTime());
        viewHolder.source.setText(mention.getSource());

        if (position % 2 == 1) {
            viewHolder.rLayout.setBackgroundResource(R.color.darkgray);
        } else {
            viewHolder.rLayout.setBackgroundResource(R.color.lightgray);
        }
        return convertView;
    }

    private static class ViewHolder {
        private TextView text = null;
        private TextView time = null;
        private TextView source = null;
        private ImageView logo = null;
        private ImageView avatar = null;
        private View bluebutton = null;
        private RelativeLayout rLayout = null;
        private ProgressBar waitingAnim = null;
    }
}
