package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

public class HomeCategoriesResponse extends BaseResponse
{

    /**
     * code : 200
     * data : [{"id":1,"ar_title":"ملابس","en_title":"clothing","img":"asdasdas.jpg"}]
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
         * id : 1
         * ar_title : ملابس
         * en_title : clothing
         * img : asdasdas.jpg
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
    }
}
