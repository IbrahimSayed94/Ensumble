package com.ensumble.Paging.adapter;

import android.annotation.SuppressLint;
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
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.PagingProductsResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.view.activity.ProductDetailsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductsPagingAdapter extends PagedListAdapter<PagingProductsResponse.ProductsBean.DataBean, ProductsPagingAdapter.ViewHolder> {

    Context context;

    String type;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    CustomToolBar toolBar;


    protected ProductsPagingAdapter(@NonNull DiffUtil.ItemCallback<PagingProductsResponse.ProductsBean.DataBean> diffCallback) {
        super(diffCallback);
    }

    protected ProductsPagingAdapter(@NonNull AsyncDifferConfig<PagingProductsResponse.ProductsBean.DataBean> config) {
        super(config);
    }

    private static DiffUtil.ItemCallback<PagingProductsResponse.ProductsBean.DataBean> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<PagingProductsResponse.ProductsBean.DataBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull PagingProductsResponse.ProductsBean.DataBean listBean, @NonNull PagingProductsResponse.ProductsBean.DataBean t1) {
            return listBean.getId() == t1.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull PagingProductsResponse.ProductsBean.DataBean listBean, @NonNull PagingProductsResponse.ProductsBean.DataBean t1) {
            return listBean.equals(t1);
        }
    };



    public ProductsPagingAdapter(Context context,String type, Activity activity) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.type = type;

        if(! type.equals("search"))
            toolBar = new CustomToolBar(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final  PagingProductsResponse.ProductsBean.DataBean productsBean= getItem(position);

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

                try {
                    Intent intent = new Intent(context, ProductDetailsActivity.class);
                    intent.putExtra("id", productsBean.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                catch (Exception e)
                {}
            }
        });

        holder.product_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addCart(productsBean.getId());
            }
        });
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
                                getCurrentList().remove(position);
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

    private void addCart(int productId)
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
        AndroidNetworking.post(Constant.BASE_URL + "Basket_Store")
                .addBodyParameter("product_id", String.valueOf(productId))
                .addBodyParameter("user_id", PrefUser.getUserId(context))
                .addBodyParameter("count", "1")
                .addBodyParameter("color", "")
                .addBodyParameter("size", "")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {


                            PrefUser.setCartCount(context,PrefUser.getCartCount(context)+1);
                            if(! type.equals("search"))
                                toolBar.setCartCount(PrefUser.getCartCount(context));



                        } else {
                            Toast.makeText(context,context.getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(context,context.getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of addCart
}
