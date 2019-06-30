package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.BuildConfig;
import com.ensumble.Model.AdvertisingResponse;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.HomeCategoriesResponse;
import com.ensumble.Model.ProductDetailsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailsActivity extends AppCompatActivity {


    int productId = 0;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @BindView(R.id.product_favorite_btn)
    ToggleButton product_favorite_btn;

    @BindView(R.id.product_tag_sale)
    TextView product_tag_sale;
    @BindView(R.id.product_price)
    TextView product_price;
    @BindView(R.id.product_title)
    TextView product_title;
    @BindView(R.id.product_description)
    TextView product_description;
    @BindView(R.id.product_stock)
    TextView product_stock;
    @BindView(R.id.product_seller_name)
    TextView product_seller_name;

    @BindView(R.id.product_seller_image)
    ImageView product_seller_image;

    TextSliderView textSliderView ;
    SliderLayout sliderShow ;


    @BindView(R.id.product_total_price)
    TextView product_total_price;
    @BindView(R.id.product_item_quantity)
    TextView product_item_quantity;

    int  countProduct = 1;
    double singlePrice = 0 , totalPrice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);

        getData();
        initFavoriteButton();

    } // function of onCreate


    private void getData() {
        if (getIntent() != null) {
            productId = getIntent().getIntExtra("id", 0);
            getProductDetails();
        }
    } // function of getData


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user", MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language", "ar"))));
    }// apply fonts


    private void setProductDetailsData(ProductDetailsResponse.ProductBean object) {

        imageSlider(object.getImages());

        if(object.getFavorite().equals("1"))
            product_favorite_btn.setChecked(true);
        else
            product_favorite_btn.setChecked(false);

        if(object.getSale().equals("1"))
            product_tag_sale.setVisibility(View.VISIBLE);
        else
            product_tag_sale.setVisibility(View.GONE);

        product_price.setText(object.getPrice());
        product_total_price.setText(object.getPrice());

        if(!object.getPrice().equals(""))
        singlePrice = Double.parseDouble(object.getPrice());

        if(object.getInstack().equals("1"))
        {
            product_stock.setText(getString(R.string.in_stock));
            product_stock.setTextColor(getResources().getColor(R.color.colorAccentBlue));
        }
        else
        {
            product_stock.setText(getString(R.string.sold_out));
            product_stock.setTextColor(getResources().getColor(R.color.colorAccentRed));
        }


        if(PrefUser.getLanguage(getApplicationContext()).equals("ar"))
        {
            product_title.setText(object.getAr_title());
            product_description.setText(object.getAr_description());
        }
        else
        {
            product_title.setText(object.getEn_title());
            product_description.setText(object.getEn_description());
        }


        product_seller_name.setText(object.getUser().get(0).getName());

        Glide.with(this)
                .load(Constant.PRODUCT_IMAGE_URL+object.getUser().get(0).getImg())
                .placeholder(getDrawable(R.drawable.placeholder))
                .fitCenter()
                .into(product_seller_image);

    } // function of setProductDetailsData


    private void getProductDetails() {
        progress = new CustomDialogProgress();
        progress.init(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.post(Constant.BASE_URL + "Product")
                .addBodyParameter("id", String.valueOf(productId))
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(ProductDetailsResponse.class, new ParsedRequestListener<ProductDetailsResponse>() {

                    @Override
                    public void onResponse(ProductDetailsResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {

                            setProductDetailsData(response.getProduct());

                        } else {
                            Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(), getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // get ProductDetails

    private   void  imageSlider (List<ProductDetailsResponse.ProductBean.ImagesBean> imageList)
    {

        sliderShow = findViewById(R.id.slider);

        for(int i = 0 ; i< imageList.size() ; i++) {
            textSliderView = new TextSliderView(this);
            textSliderView
                    .image(Constant.PRODUCT_IMAGE_URL+imageList.get(i).getImg());
            sliderShow.addSlider(textSliderView);

        }

    } // function of imageSlider


    @OnClick(R.id.product_share_btn)
    public void shareButton()
    {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Ensumble");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    } // function of shareButton


    @OnClick({R.id.product_item_quantity_plusBtn,R.id.product_item_quantity_minusBtn})
    public void click(View view)
    {
        if(view.getId() == R.id.product_item_quantity_plusBtn)
        {
            countProduct++;
            product_item_quantity.setText(countProduct+"");
            totalPrice = countProduct  * singlePrice ;
            product_total_price.setText(totalPrice+"");
        }
        else if(view.getId() == R.id.product_item_quantity_minusBtn)
        {
            if(countProduct != 1) {
                countProduct--;
                product_item_quantity.setText(countProduct + "");
                totalPrice = countProduct * singlePrice;
                product_total_price.setText(totalPrice + "");
            }
        }
    } // function of minus and plus click


    private void initFavoriteButton()
    {
        product_favorite_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    addFavorite(productId,"1");
                else
                    addFavorite(productId,"0");
            }
        });
    } // function of initFavoriteButton

    private void addFavorite(int productId,String favoriteValue)
    {
        progress = new CustomDialogProgress();
        progress.init(this);
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
                .addBodyParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addBodyParameter("value", favoriteValue)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if (response.getCode().equals("200")) {


                        } else {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message), Toast.LENGTH_LONG).show();
                    }
                });
    } // function of addFavorite

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("flag","homeFragment");
        startActivity(intent);
        finish();
    }
} // function of ProductDetailsActivity
