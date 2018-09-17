package com.example.isp30.wearetheone.HelpAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isp30.wearetheone.R;

import java.util.List;

public class Help_Embassy_Adapter extends BaseAdapter {
    private Context context;
    private List<Help_Embassy_item> help_embassy_items;

    public Help_Embassy_Adapter(Context context, List<Help_Embassy_item> help_embassy_items) {
        this.context = context;
        this.help_embassy_items = help_embassy_items;
    }

    @Override
    public int getCount() {
        return help_embassy_items.size();
    }

    @Override
    public Object getItem(int position) {
        return help_embassy_items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.embassylist,parent,false);

        ImageView flag = (ImageView)convertView.findViewById(R.id.flagImg);
        TextView embassyNmae = (TextView)convertView.findViewById(R.id.embassyName);

        Help_Embassy_item item  = help_embassy_items.get(position);
        flag.setImageResource(item.getFlag());
        embassyNmae.setText(item.getFlagName());

        return convertView;
    }
}
