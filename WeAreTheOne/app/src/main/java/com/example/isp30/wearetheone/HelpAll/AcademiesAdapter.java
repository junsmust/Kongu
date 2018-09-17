package com.example.isp30.wearetheone.HelpAll;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.isp30.wearetheone.R;

import java.util.List;

public class AcademiesAdapter extends RecyclerView.Adapter<AcademiesAdapter.ViewHolder> {

   private List<AcademiesItems> listItem;
   private int itemLayout;
   private Intent intent;

    public AcademiesAdapter(List<AcademiesItems> listItem, int itemLayout) {
        this.listItem = listItem;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AcademiesItems item = listItem.get(position);
        holder.imageView.setImageResource(R.drawable.ic_lock_open_black_24dp);
        holder.title.setText(item.getName());
        holder.itemView.setTag(item);


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }



    //뷰 재활용을 위한 viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            imageView = (ImageView)itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.academy1);

        }

        @Override
        public void onClick(View v) {

            switch (getPosition()){
                case 0:
                    Intent intent = new Intent(v.getContext(),k_YeonseiKorean.class);
                    v.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(v.getContext(),Academy_2.class);
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(v.getContext(),Academy_3.class);
                    v.getContext().startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(v.getContext(),Academy_4.class);
                    v.getContext().startActivity(intent);
                    break;

            }


        }
    }
}
