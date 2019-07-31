package com.ensumble.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.ensumble.AppConfig.Constant;
import com.ensumble.AppConfig.CustomDialogProgress;
import com.ensumble.Model.AboutResponse;
import com.ensumble.Model.MyCartResponse;
import com.ensumble.PefManager.PrefUser;
import com.ensumble.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutFragment extends Fragment
{

    Context context;

    //progress
    CustomDialogProgress progress;
    Handler handler;

    @BindView(R.id.about_title)
    TextView about_title;

    @BindView(R.id.about_description)
    TextView about_description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);


        getAbout();

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

    private void setData(AboutResponse.Data data)
    {
         if(PrefUser.getLanguage(context).equals("ar"))
         {
             about_title.setText(data.getAr_title());
             about_description.setText(data.getAr_description());
         }
         else
         {
             about_title.setText(data.getEn_title());
             about_description.setText(data.getEn_description());
         }
    } // function of setData

    private void getAbout()
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
        AndroidNetworking.get(Constant.BASE_URL+"AboutUs")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(AboutResponse.class, new ParsedRequestListener<AboutResponse>() {

                    @Override
                    public void onResponse(AboutResponse response) {
                        progress.dismiss();

                        if(response.getCode().equals("200"))
                        {
                            setData(response.getData().get(0));
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
    } // function of getAbout
} // class of AboutFragment
