package com.ensumble.view.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.BaseResponse;
import com.ensumble.Model.MyCartResponse;
import com.ensumble.Model.ProductsResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.adapter.CartAdapter;
import com.ensumble.view.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartFragment extends Fragment
{
    Context context;

    @BindView(R.id.cart_items_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.cart_total_price)
    TextView cart_total_price;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    Dialog conFirmCheckOutDialog;
    Button dialog_yes , dialog_no;

    boolean cartEmpty = true ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        ButterKnife.bind(this, view);


        initConfirmationDialog();
        getCartData();

        return view;
    } // function of onCreateView

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    } // function of onAattach

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } // function of onCreate


    private void initCartList(List<MyCartResponse.DataBean> cartList)
    {
        CartAdapter adapter=new CartAdapter(context,cartList);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function od initCartList


    private void getCartData()
    {
        progress = new CustomDialogProgress();
        progress.init(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.post(Constant.BASE_URL+"MyBasket")
                .addBodyParameter("user_id", PrefUser.getUserId(getContext()))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(MyCartResponse.class, new ParsedRequestListener<MyCartResponse>() {

                    @Override
                    public void onResponse(MyCartResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            if(response.getData().size() > 0) {
                                initCartList(response.getData());
                                getTotalPrice(response.getData());
                                cartEmpty = false;
                            }

                        }
                        else
                        {
                            Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } // function og getCartData


    private void getTotalPrice(List<MyCartResponse.DataBean> cartList)
    {
        double totalPrice = 0;

         for(int i=0;i<cartList.size();i++)
         {
             totalPrice = totalPrice + ( cartList.get(i).getProductDetails().get(0).getPrice() *
                     cartList.get(i).getCountX() ) ;
         }

        cart_total_price.setText(totalPrice+"");
    } // function of getTotalPrice

    @OnClick(R.id.cart_checkout_btn)
    public void checkOutButton()
    {
       if(cartEmpty)
       {
           Toast.makeText(context,getString(R.string.notFound),Toast.LENGTH_LONG).show();
       }
       else
       {
           conFirmCheckOutDialog.show();
       }
    } // function of checkOutButton


    private void initConfirmationDialog()
    {
        conFirmCheckOutDialog = new Dialog(context);
        conFirmCheckOutDialog.setCancelable(false);
        conFirmCheckOutDialog.setContentView(R.layout.dialog_cart_check_out_confirmation);
        conFirmCheckOutDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        conFirmCheckOutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        dialog_yes = conFirmCheckOutDialog.findViewById(R.id.dialog_yes);
        dialog_no = conFirmCheckOutDialog.findViewById(R.id.dialog_no);

        dialog_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conFirmCheckOutDialog.dismiss();
            }
        });

        dialog_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conFirmCheckOutDialog.dismiss();
                checkOut();
            }
        });
    } // function of initConfirmationDialog

    private void checkOut()
    {
        progress = new CustomDialogProgress();
        progress.init(getContext());
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.dismiss();
                super.handleMessage(msg);
            }

        };
        progress.show();
        AndroidNetworking.post(Constant.BASE_URL+"Store_Order")
                .addBodyParameter("user_id", PrefUser.getUserId(getContext()))
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(BaseResponse.class, new ParsedRequestListener<BaseResponse>() {

                    @Override
                    public void onResponse(BaseResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            Toast.makeText(context,getString(R.string.checkOutMessage),Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(context, MainActivity.class);
                            intent.putExtra("flag","homeFragment");
                            startActivity(intent);
                            ((Activity) context).finish();
                        }
                        else
                        {
                            Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progress.dismiss();
                        Toast.makeText(getContext(),getString(R.string.api_failure_message),Toast.LENGTH_LONG).show();
                    }
                });
    } //  function of checkOut
} // class of CartFragment
