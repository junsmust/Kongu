package com.example.isp30.wearetheone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.isp30.wearetheone.HelpAll.HelpItem;

import java.util.List;

public class BoardAdapter extends BaseAdapter {
    private Context context;
    private List<BoardItems> boardItems;

    public BoardAdapter(Context context, List<BoardItems> boardItems) {
        this.context = context;
        this.boardItems = boardItems;
    }

    @Override
    public int getCount() {
        return boardItems.size();
    }

    @Override
    public Object getItem(int position) {
        return boardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.boarditem,parent,false);

        TextView title = convertView.findViewById(R.id.title_board);
        TextView subTitle = convertView.findViewById(R.id.writer_board);
        TextView time = convertView.findViewById(R.id.current_time_board);


        BoardItems boardItems2 = boardItems.get(position);
        title.setText(boardItems2.getTitle());
        subTitle.setText(boardItems2.getWirter());
        time.setText(boardItems2.getTime());

        return convertView;
    }
}
