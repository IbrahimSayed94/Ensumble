package com.ensumble.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.AppConfig.CustomToolBar;
import com.ensumble.AppConfig.MyContextWrapper;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.SellerCategoriesResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.ImageAdapter;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddProductActivity extends AppCompatActivity {



    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private static final int REQUEST_CODE = 123;
    private ArrayList<String> mResults = new ArrayList<>();

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @BindView(R.id.spinner_category)
    Spinner spinner_category;

    int categoryId=0;

    @BindView(R.id.ed_product_price)
    EditText ed_product_price;
    @BindView(R.id.ed_product_sale)
    EditText ed_product_sale;
    @BindView(R.id.ed__product_arabic_title)
    EditText ed__product_arabic_title;
    @BindView(R.id.ed_product_english_title)
    EditText ed_product_english_title;
    @BindView(R.id.ed_product_arabic_description)
    EditText ed_product_arabic_description;
    @BindView(R.id.ed_product_english_description)
    EditText ed_product_english_description;

    String price=""  , sale="" , arabicTitle="" , englistTitle="" , arabicDescription="" , englishDescription="";

    ArrayList<File> fileList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        ButterKnife.bind(this);
        CustomToolBar toolBar = new CustomToolBar(this);
        toolBar.setTitle(getString(R.string.addProduct));
        toolBar.setCartCount(PrefUser.getCartCount(getApplicationContext()));


        getSellerCategories();

    } // function of onCreate

    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user",MODE_PRIVATE);
        super.attachBaseContext(CalligraphyContextWrapper.wrap(new MyContextWrapper(newBase).wrap(sharedPreferences.getString("language","ar"))));
    }// apply fonts


    private void initImageList(ArrayList<String> imageList)
    {
        ImageAdapter adapter=new ImageAdapter(getApplicationContext(),imageList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initImageList

    @OnClick(R.id.bt_upload_image)
    public  void selectImagesButton()
    {
      openMultipleImageGallery();
    } // function of selectImagesButton

    private void openMultipleImageGallery()
    {
// start multiple photos selector
        Intent intent = new Intent(AddProductActivity.this, ImagesSelectorActivity.class);
// max number of images to be selected
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER, 6);
// min size of image which will be shown; to filter tiny images (mainly icons)
        intent.putExtra(SelectorSettings.SELECTOR_MIN_IMAGE_SIZE, 100000);
// show camera or not
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA, true);
// pass current selected images as the initial value
        intent.putStringArrayListExtra(SelectorSettings.SELECTOR_INITIAL_SELECTED_LIST, mResults);
// start the selector
        startActivityForResult(intent, REQUEST_CODE);
    } // function of openMultipleImageGallery

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get selected images from selector
        if(requestCode == REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                initImageList(mResults);

                StringBuffer sb = new StringBuffer();
                sb.append(String.format(getString(R.string.totalImages)+" "+mResults.size())).append("\n");
                for(String result : mResults) {
                    sb.append(result).append("\n");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    } // function of onActivityResult

    private void getSellerCategories()
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
        AndroidNetworking.get(Constant.BASE_URL+"CategoriesOfCompany")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(SellerCategoriesResponse.class, new ParsedRequestListener<SellerCategoriesResponse>() {

                    @Override
                    public void onResponse(SellerCategoriesResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getData().size() > 0)
                                setSpinner_category(response.getData());

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of getSellerCategories

    private void setSpinner_category(List<SellerCategoriesResponse.DataBean> categoryList)
    {
        ArrayAdapter<SellerCategoriesResponse.DataBean> adapter =
                new ArrayAdapter<>(getApplicationContext(),  R.layout.spinner_text, categoryList);
        adapter.setDropDownViewResource( R.layout.spinner_text);

        spinner_category.setAdapter(adapter);


        spinner_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                categoryId = categoryList.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    } // function of setCity

    @OnClick(R.id.bt_upload_product)
    public void uploadProductButton()
    {
        if(validateProductData())
        uploadProduct();
        else
            Toast.makeText(getApplicationContext(),getString(R.string.completeFields),Toast.LENGTH_LONG).show();
    } // function of uploadProductButton

    private boolean validateProductData()
    {
        price = ed_product_price.getText().toString();
        sale = ed_product_sale.getText().toString();
        arabicTitle = ed__product_arabic_title.getText().toString();
        englistTitle = ed_product_english_title.getText().toString();
        arabicDescription = ed_product_arabic_description.getText().toString();
        englishDescription = ed_product_english_description.getText().toString();

        for(String uri : mResults)
        {
            fileList.add(new File(uri));
            Log.e("QP","uri : "+uri);
        }

        if(price.equals("") || sale.equals("") || arabicTitle.equals("")
        || englistTitle.equals("") || arabicDescription.equals("") || englishDescription.equals(""))
            return false;
        if(fileList.size()<1)
            return false;
        if(categoryId == 0)
            return false;

        return true;
    } // functio of validateProductData

    private void uploadProduct()
    {

        Log.e("QP","images : "+fileList.size());
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
        AndroidNetworking.upload(Constant.BASE_URL+"Store_Product")
                .addMultipartFileList("images[]",fileList)
                .addMultipartParameter("ar_title", arabicTitle)
                .addMultipartParameter("en_title", englistTitle)
                .addMultipartParameter("ar_description", arabicDescription)
                .addMultipartParameter("en_description", englishDescription)
                .addMultipartParameter("cat_id", String.valueOf(categoryId))
                .addMultipartParameter("price", price)
                .addMultipartParameter("user_id", PrefUser.getUserId(getApplicationContext()))
                .addMultipartParameter("sale", sale)
                .addMultipartParameter("salePrice", "")
                .addMultipartParameter("instack", "1")
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                        Log.e("QP","inProgress...."+bytesUploaded);
                    }
                })
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>()  {
                    @Override
                    public void onResponse(BaseResponse response) {


                        progress.dismiss();
                        if(response.getCode().equals("200"))
                        {
                          Toast.makeText(getApplicationContext(),getString(R.string.successfullyDone)+"\n"+
                                  "count : "+response.getCounts()+" : check : "+response.getCheck(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        progress.dismiss();
                        Toast.makeText(getApplicationContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function of uploadProduct
} // class of AddProductActivity
