package com.example.isp30.wearetheone;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.transition.FragmentTransitionSupport;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class KoreanAdapters extends RecyclerView.Adapter<KoreanAdapters.ViewHolder> {

   private List<KoreanItems> listItem;
   private int itemLayout;
   private Intent intent;

    public KoreanAdapters(List<KoreanItems> listItem, int itemLayout) {
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
        KoreanItems item = listItem.get(position);
        holder.img.setImageResource(item.getImg());
        holder.textView.setText(item.getText());
        holder.itemView.setTag(item);


    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }



    //뷰 재활용을 위한 viewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            img = (ImageView)itemView.findViewById(R.id.angryImg);
            textView = (TextView)itemView.findViewById(R.id.angry);
        }

        @Override
        public void onClick(View v) {

            switch (getPosition()){
                case 0:
                    Intent intent = new Intent(v.getContext(),CommonTalk.class);
                    v.getContext().startActivity(intent);
                    break;
                case 1:
                    intent = new Intent(v.getContext(),DatingTalk.class);
                    v.getContext().startActivity(intent);
                    break;
                case 2:
                    intent = new Intent(v.getContext(),DrinkingTalk.class);
                    v.getContext().startActivity(intent);
                    break;
                case 3:
                    intent = new Intent(v.getContext(),GetAngry.class);
                    v.getContext().startActivity(intent);
                    break;

            }


        }
    }
}
