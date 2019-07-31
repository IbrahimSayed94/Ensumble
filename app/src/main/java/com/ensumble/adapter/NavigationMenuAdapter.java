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

import com.ensumble.BuildConfig;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;
import com.ensumble.view.activity.AddProductActivity;
import com.ensumble.view.activity.LoginActivity;
import com.ensumble.view.activity.MainActivity;
import com.ensumble.view.fragment.AboutFragment;
import com.ensumble.view.fragment.CategoriesFragment;
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
                    case 2: // stores
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new SellerCategoriesFragment()).commit();
                        break;
                    case 3: // categories
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new CategoriesFragment()).commit();
                        break;
                    case 4: // about
                        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, new AboutFragment()).commit();
                        break;
                    case 5: // share
                        shareApp();
                        break;
                    case 6: // language
                        changeLanguage();
                        break;
                    case 7: // logOut
                        logOut();
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


    private void logOut()
    {
        PrefUser.setLogin(context,false);
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        context.startActivity(intent);
        ((Activity)context).finish();
    } // function of logOut

    private void shareApp()
    {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Maak Maak");
            String shareMessage= "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch(Exception e) {
            //e.toString();
        }
    } // function of shareApp
}

