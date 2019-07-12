package com.ensumble.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.Model.SellerCategoriesResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.view.activity.SellersActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SellerCategoryAdapter extends RecyclerView.Adapter<SellerCategoryAdapter.ViewHolder> {


    Context context;
    List<SellerCategoriesResponse.DataBean> categoryList;

    public SellerCategoryAdapter(Context context, List<SellerCategoriesResponse.DataBean> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.seller_category_image)
        ImageView seller_category_image;

        @BindView(R.id.seller_category_title)
        TextView seller_category_title;
        @BindView(R.id.seller_category_count)
        TextView seller_category_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_categories_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final SellerCategoriesResponse.DataBean object = categoryList.get(position);

        Glide.with(context)
             .load(Constant.CATEGORY_IMAGE_URL+object.getImg())
             .placeholder(R.drawable.placeholder)
             .into(holder.seller_category_image);

        if(PrefUser.getLanguage(context).equals("ar"))
            holder.seller_category_title.setText(object.getAr_title());
        else
            holder.seller_category_title.setText(object.getEn_title());

        holder.seller_category_count.setText(object.getCompanyCount()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,SellersActivity.class);
                intent.putExtra("id",object.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

}
