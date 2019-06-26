package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

public class AdvertisingResponse extends BaseResponse
{

    /**
     * code : 200
     * Status : success
     * data : [{"id":1,"ar_title":"ar","en_title":"en","confirm":"1","img":"15613146102.jpg","created_by":null,"updated_by":null,"created_at":null,"updated_at":null}]
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
         * ar_title : ar
         * en_title : en
         * confirm : 1
         * img : 15613146102.jpg
         * created_by : null
         * updated_by : null
         * created_at : null
         * updated_at : null
         */

        @Nullable
        private int id;
        @Nullable
        private String ar_title;
        @Nullable
        private String en_title;
        @Nullable
        private String confirm;
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
        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(@Nullable String confirm) {
            this.confirm = confirm;
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
