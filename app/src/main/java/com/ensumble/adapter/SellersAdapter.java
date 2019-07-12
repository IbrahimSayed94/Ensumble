package com.ensumble.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.Model.SellersResponse;
import com.ensumble.R;
import com.ensumble.view.activity.SellerDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellersAdapter extends RecyclerView.Adapter<SellersAdapter.ViewHolder>
{

    Context context;
    List<SellersResponse.UsersBean> sellerList;

    public SellersAdapter(Context context, List<SellersResponse.UsersBean> sellerList) {
        this.context = context;
        this.sellerList = sellerList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.seller_image)
        ImageView seller_image;
        @BindView(R.id.seller_tag_new)
        ImageView seller_tag_new;

        @BindView(R.id.seller_tag_featured)
        TextView seller_tag_featured;
        @BindView(R.id.seller_tag_sale)
        TextView seller_tag_sale;

        @BindView(R.id.seller_name)
        TextView seller_name;
        @BindView(R.id.seller_mobile)
        TextView seller_mobile;
        @BindView(R.id.seller_category)
        TextView seller_category;

        @BindView(R.id.seller_rate)
        RatingBar seller_rate;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_seller,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SellersResponse.UsersBean object = sellerList.get(position);

        holder.seller_name.setText(object.getName());
        holder.seller_mobile.setText(object.getPhone());

        Glide.with(context)
                .load(Constant.USER_IMAGE_URL+object.getImg())
                .placeholder(R.drawable.placeholder)
                .into(holder.seller_image);

        holder.seller_rate.setRating(object.getRate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SellerDetailsActivity.class);
                intent.putExtra("id",object.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        if(object.getNew().equals("1"))
            holder.seller_tag_new.setVisibility(View.VISIBLE);
        else
            holder.seller_tag_new.setVisibility(View.GONE);
        if(object.getSale().equals("1"))
            holder.seller_tag_sale.setVisibility(View.VISIBLE);
        else
            holder.seller_tag_sale.setVisibility(View.GONE);
        if(object.getFeatured().equals("1"))
            holder.seller_tag_featured.setVisibility(View.VISIBLE);
        else
            holder.seller_tag_featured.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return sellerList.size();
    }


}
