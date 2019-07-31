package com.ensumble.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.Model.MyCartResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    Context context;
    List<MyCartResponse.DataBean> cartList;

    public CartAdapter(Context context, List<MyCartResponse.DataBean> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.cart_item_title)
        TextView cart_item_title;
        @BindView(R.id.cart_item_category)
        TextView cart_item_category;
        @BindView(R.id.cart_item_base_price)
        TextView cart_item_base_price;
        @BindView(R.id.cart_item_quantity)
        TextView cart_item_quantity;
        @BindView(R.id.cart_item_sub_price)
        TextView cart_item_sub_price;

        @BindView(R.id.cart_item_cover)
        ImageView cart_item_cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.raw_my_cart_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final MyCartResponse.DataBean object = cartList.get(position);

        if(PrefUser.getLanguage(context).equals("ar"))
        {
         holder.cart_item_title.setText(object.getProductDetails().get(0).getAr_title());
         holder.cart_item_category.setText(object.getCategory().get(0).getAr_title());
        }
        else
        {
            holder.cart_item_title.setText(object.getProductDetails().get(0).getEn_title());
            holder.cart_item_category.setText(object.getCategory().get(0).getEn_title());
        }

        holder.cart_item_quantity.setText(object.getCountX()+"");

        holder.cart_item_base_price.setText(object.getProductDetails().get(0).getPrice()+"");
        holder.cart_item_sub_price.setText( (object.getCountX()
        * object.getProductDetails().get(0).getPrice()) + "");

        Glide.with(context)
             .load(Constant.PRODUCT_IMAGE_URL+object.getProductDetails().get(0).getImg())
             .placeholder(context.getResources().getDrawable(R.drawable.placeholder))
             .into(holder.cart_item_cover);


    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }


}
