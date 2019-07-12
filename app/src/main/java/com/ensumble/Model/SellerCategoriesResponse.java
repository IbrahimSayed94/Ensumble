package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.ensumble.AppConfig.MyApp;
import com.ensumble.PefManager.PrefUser;

import java.util.List;

public class SellerCategoriesResponse extends BaseResponse
{

    /**
     * code : 200
     * data : [{"id":2,"ar_title":"الملابس","en_title":"Clothes","img":"1561730492.ab_pic.jpg","CompanyCount":0},{"id":3,"ar_title":"رجالي","en_title":"mens","img":"1561730519.bb2.jpg","CompanyCount":1},{"id":4,"ar_title":"حريمي","en_title":"womens","img":"1561815572.background.jpg","CompanyCount":1},{"id":5,"ar_title":"اطفالى","en_title":"children","img":"1561815672.children-clothing-photography-studio-brighton-uk.jpg","CompanyCount":0}]
     */

    @Nullable
    private List<DataBean> data;

    @Nullable
    public List<DataBean> getData() {
        return data;
    }

    public void setData(@Nullable List<DataBean> data) {
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
        @Nullable
        private int CompanyCount;

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

        public int getCompanyCount() {
            return CompanyCount;
        }

        public void setCompanyCount(int companyCount) {
            CompanyCount = companyCount;
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
