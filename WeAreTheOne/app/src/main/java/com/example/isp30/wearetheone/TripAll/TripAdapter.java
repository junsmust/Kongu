package com.example.isp30.wearetheone.TripAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isp30.wearetheone.R;

import java.util.List;

public class TripAdapter extends BaseAdapter {
    private Context context;
    private List<TripItem> tripItems;

    public TripAdapter(Context context, List<TripItem> tripItems) {
        this.context = context;
        this.tripItems = tripItems;
    }

    @Override
    public int getCount() {
        return tripItems.size();
    }

    @Override
    public Object getItem(int position) {
        return tripItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.triplistitems,parent,false);

       ImageView trip_image = (ImageView)convertView.findViewById(R.id.trip_image);
        TextView title = (TextView)convertView.findViewById(R.id.trip_title);
        TextView subTitle = (TextView)convertView.findViewById(R.id.trip_subtitle);


        TripItem tripItem = tripItems.get(position);
        trip_image.setImageDrawable(tripItem.getPic());
        title.setText(tripItem.getTitle());
        subTitle.setText(tripItem.getSubTitle());


        return convertView;
    }
}
