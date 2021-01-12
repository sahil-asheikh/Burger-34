package com.begawoinc.burger34admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<BannerHolder> {

    private Context mContext;
    private List<Banner> myBanner;


    public MyAdapter(Context mContext, List<Banner> myBanner) {
        this.mContext = mContext;
        this.myBanner = myBanner;
    }

    @Override
    public BannerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bannerdata,parent,false);

        return new  BannerHolder(mView);
    }

    @Override
    public void onBindViewHolder(final BannerHolder holder, int position) {

        Glide.with(mContext)
                .load(myBanner.get(position).getItemImage())
                .into(holder.imageView);

        //holder.imageView.setImageResource(myBanner.get(position).getItemImage());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("Image",myBanner.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("keyValue",myBanner.get(holder.getAdapterPosition()).getKey());
                mContext.startActivity(intent);


            }
        });



    }

    @Override
    public int getItemCount() {
        return myBanner.size();
    }
}

class BannerHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    CardView mCardView;

    public BannerHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mCardView = itemView.findViewById(R.id.card_view);
    }
}
