package com.example.isp30.wearetheone.HelpAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.isp30.wearetheone.R;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends BaseAdapter {
    private Context context;
    private List<HelpItem> helpItems;

    public HelpAdapter(Context context, List<HelpItem> helpItems) {
        this.context = context;
        this.helpItems = helpItems;
    }

    @Override
    public int getCount() {
        return helpItems.size();
    }

    @Override
    public Object getItem(int position) {
        return helpItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.helpitem,parent,false);

        TextView title = convertView.findViewById(R.id.titles_help);
        TextView subTitle = convertView.findViewById(R.id.subtitle_help);

        HelpItem helpItem  = helpItems.get(position);
        title.setText(helpItem.getTitle());
        subTitle.setText(helpItem.getSubTitle());

        return convertView;
    }
}
