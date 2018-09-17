package com.example.isp30.wearetheone.TripAll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isp30.wearetheone.R;

import java.util.List;

class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {
    private static final String TAG = "MyRecyclerAdapter";

    private Context context;
    private List<TripItem> items;
    private int itemnLayout;

    public MyRecyclerAdapter(Context context, List<TripItem> items, int itemnLayout) {
        this.context = context;
        this.items = items;
        this.itemnLayout = itemnLayout;
    }


    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemnLayout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageDrawable(items.get(position).getPic());
        holder.keyMenu.setText(items.get(position).getTitle());
        holder.menuDetail.setText(items.get(position).getSubTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"아이템 클릭"+position,Toast.LENGTH_SHORT);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"아이템 롱 클릭"+position,Toast.LENGTH_SHORT);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView keyMenu;
        public TextView menuDetail;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.cardImg);
            keyMenu = (TextView)itemView.findViewById(R.id.key);
            menuDetail = (TextView)itemView.findViewById(R.id.specific);

        }
    }
}
