package com.ensumble.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensumble.R;
import com.ensumble.view.activity.AddProductActivity;
import com.ensumble.view.activity.MainActivity;
import com.ensumble.view.fragment.HomeFragment;
import com.ensumble.view.fragment.MyFavoriteFragment;
import com.ensumble.view.fragment.SellerCategoriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationMenuAdapter extends RecyclerView.Adapter<NavigationMenuAdapter.ViewHolder> {

    private String[] mMenuItemNameList;
    private TypedArray mMenuDrawableItems;
    private Context context;
    private DrawerLayout drawerLayout;

    Intent intent;

    public NavigationMenuAdapter(String[] mMenuItemNameList, TypedArray mMenuDrawableItems, Context context, DrawerLayout drawerLayout) {
        this.mMenuItemNameList = mMenuItemNameList;
        this.mMenuDrawableItems = mMenuDrawableItems;
        this.context = context;
        this.drawerLayout = drawerLayout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.menu_item_img)
        ImageView imageView;

        @BindView(R.id.menu_item_title)
        TextView text_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_navigation_menu, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.text_title.setText(mMenuItemNameList[i]);
        viewHolder.imageView.setImageResource(mMenuDrawableItems.getResourceId(i, 0));

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (i) {
                    case 0: // home
                       Intent intent=new Intent(context,MainActivity.class);
                       intent.putExtra("flag","homeFragment");
                       context.startActivity(intent);
                        ((Activity)context).finish();
                        break;
                    case 1: // favorite
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new MyFavoriteFragment()).commit();
                        break;
                    case 2: // seller categories
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new SellerCategoriesFragment()).commit();
                        break;
                    case 3: // seller categories
                       Intent intent1 = new Intent(context, AddProductActivity.class);
                       context.startActivity(intent1);
                        break;
                    case 4: // language
                        changeLanguage();
                        break;
                }

                drawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuItemNameList.length;
    }


    private void changeLanguage() {
        SharedPreferences.Editor editor = context.getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        ;
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("language", "ar").equals("ar") || sharedPreferences.getString("language", "ar").equals("")) {
            editor.putString("language", "en");
            editor.apply();
        } else if (sharedPreferences.getString("language", "ar").equals("en")) {
            editor.putString("language", "ar");
            editor.apply();
        }
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("flag", "homeFragment");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).finish();
    } // function of changeLanguage


}

