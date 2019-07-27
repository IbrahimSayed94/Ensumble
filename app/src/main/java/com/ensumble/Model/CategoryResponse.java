package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.ensumble.AppConfig.MyApp;
import com.ensumble.PefManager.PrefUser;

import java.util.List;

public class CategoryResponse extends BaseResponse
{
    @Nullable
    private List<CategoryResponse.DataBean> data;

    @Nullable
    public List<CategoryResponse.DataBean> getData() {
        return data;
    }

    public void setData(@Nullable List<CategoryResponse.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * ar_title : الملابس
         * en_title : Clothes
         * img : 1561730492.ab_pic.jpg
         * CompanyCount : 0
         */

        @Nullable
        private int id;
        @Nullable
        private String ar_title;
        @Nullable
        private String en_title;
        @Nullable
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Nullable
        public String getAr_title() {
            return ar_title;
        }

        public void setAr_title(@Nullable String ar_title) {
            this.ar_title = ar_title;
        }

        @Nullable
        public String getEn_title() {
            return en_title;
        }

        public void setEn_title(@Nullable String en_title) {
            this.en_title = en_title;
        }

        @Nullable
        public String getImg() {
            return img;
        }

        public void setImg(@Nullable String img) {
            this.img = img;
        }
        
        @Override
        public String toString() {
            if(PrefUser.getLanguage(MyApp.getAppContext()).equals("ar"))
                return ar_title;
            else
                return en_title;
        }
    }
}
