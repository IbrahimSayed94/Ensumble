package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

public class AboutResponse extends BaseResponse
{
    @Nullable
    List<Data> data;

    @Nullable
    public List<Data> getData() {
        return data;
    }

    public void setData(@Nullable List<Data> data) {
        this.data = data;
    }

    public class Data
    {
        @Nullable
        private  String ar_title;
        @Nullable
        private  String en_title;
        @Nullable
        private  String ar_description;
        @Nullable
        private  String en_description;

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
        public String getAr_description() {
            return ar_description;
        }

        public void setAr_description(@Nullable String ar_description) {
            this.ar_description = ar_description;
        }

        @Nullable
        public String getEn_description() {
            return en_description;
        }

        public void setEn_description(@Nullable String en_description) {
            this.en_description = en_description;
        }
    }
}
