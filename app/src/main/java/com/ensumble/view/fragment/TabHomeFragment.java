package com.ensumble.view.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ensumble.R;
import com.ensumble.adapter.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabHomeFragment extends Fragment
{


    int position=-1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabHomeFragment tabFragment = new TabHomeFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    } // function of getInstance

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);
        ButterKnife.bind(this, view);

        fillProductList();
        return view;
    } // function of onCreateView

    private void getFragmentData()
    {
        position = getArguments().getInt("pos");
    } // function of getFragmentData


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentData();
    } // function of onCreate


    private void initProductList(List<String> productList)
    {
        ProductsAdapter adapter=new ProductsAdapter(getContext(),productList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    } // function of initProductList


    private void fillProductList()
    {
        List<String> productList = new ArrayList<>();
        productList.add("");
        productList.add("");
        productList.add("");
        productList.add("");
        productList.add("");
        productList.add("");
        initProductList(productList);

    } // function of fillProductList
} // calss of TabHomeFragment
