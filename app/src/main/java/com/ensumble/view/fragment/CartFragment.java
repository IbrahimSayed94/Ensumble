package com.ensumble.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment
{
    Context context;

    @BindView(R.id.cart_items_recycler)
    RecyclerView recyclerView;

    //progress
    CustomDialogProgress progress;
    Handler handler;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        ButterKnife.bind(this, view);


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
} // class of CartFragment
