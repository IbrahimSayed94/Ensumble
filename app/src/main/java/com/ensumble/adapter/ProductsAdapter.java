package com.ensumble.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.view.activity.ProductDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {



    Context context;
    List<ProductsResponse.ProductsBean> productList ;
    String type;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    public ProductsAdapter(Context context, List<ProductsResponse.ProductsBean> productList,String type) {
        this.context = context;
        this.productList = productList;
        this.type = type;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.product_cover)
        ImageView product_cover;
        @BindView(R.id.product_tag_new)
        ImageView product_tag_new;


        @BindView(R.id.product_tag_sale)
        TextView product_tag_sale;
        @BindView(R.id.product_title)
        TextView product_title;
        @BindView(R.id.product_seller_name)
        TextView product_seller_name;
        @BindView(R.id.product_price)
        TextView product_price;

        @BindView(R.id.product_favorite_btn)
        ToggleButton product_favorite_btn;

        @BindView(R.id.product_cart_btn)
        Button product_cart_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final  ProductsResponse.ProductsBean productsBean= productList.get(position);

        try {
            Glide.with(context)
                    .load(Constant.PRODUCT_IMAGE_URL+productsBean.getImage().getImg())
                    .fitCenter()
                    .placeholder(context.getDrawable(R.drawable.placeholder))
                    .into(holder.product_cover);
        }
        catch (Exception e)
        {

        }


        if(productsBean.getNewX().equals("1"))
            holder.product_tag_new.setVisibility(View.VISIBLE);
        else
            holder.product_tag_new.setVisibility(View.GONE);

        if(productsBean.getSale().equals("1"))
            holder.product_tag_sale.setVisibility(View.VISIBLE);
        else
            holder.product_tag_sale.setVisibility(View.GONE);

        if(PrefUser.getLanguage(context).equals("ar"))
            holder.product_title.setText(productsBean.getAr_title());
        else
            holder.product_title.setText(productsBean.getEn_title());

        holder.product_seller_name.setText(productsBean.getUser().get(0).getName());

        holder.product_price.setText(productsBean.getPrice());

        if(productsBean.getFavorite().equals("1")) {
            holder.product_favorite_btn.setChecked(true);
        }
        else {
            holder.product_favorite_btn.setChecked(false);
        }



        holder.product_favorite_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    addFavorite(productsBean.getId(),"1",holder.getAdapterPosition());
                else
                    addFavorite(productsBean.getId(),"0",holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("id",productsBean.getId());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
        holder.product_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("QP","product id"+productsBean.getId());

                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("id",productsBean.getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    private void addFavorite(int productId,String favoriteValue,int position)
    {
        progress = new CustomDialogProgress();
        progress.init(context);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.post(Constant.BASE_URL + "AddFavorite")
                .addBodyParameter("product_id", String.valueOf(productId))
                .addBodyParameter("user_id", PrefUser.getUserId(context))
                .addBodyParameter("value", favoriteValue)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {

                            if(type.equals("favorite"))
                            {
                                    productList.remove(position);
                                    notifyItemRemoved(position);
                            }

                        } else {
                            Toast.makeText(context, context.getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(context, context.getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of addFavorite
}
