package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

public class SellersResponse extends BaseResponse
{

    /**
     * code : 200
     * Users : [{"id":3,"name":"ibrahim","img":null,"phone":"01099605695","rate":4}]
     */

    @Nullable
    private List<UsersBean> Users;


    @Nullable
    public List<UsersBean> getUsers() {
        return Users;
    }

    public void setUsers(@Nullable List<UsersBean> users) {
        Users = users;
    }

    public static class UsersBean {
        /**
         * id : 3
         * name : ibrahim
         * img : null
         * phone : 01099605695
         * rate : 4
         */

        @Nullable
        private int id;
        @Nullable
        private String name;
        @Nullable
        private String img;
        @Nullable
        private String phone;
        @Nullable
        private float rate;
        @Nullable
        private String New;
        @Nullable
        private String featured;
        @Nullable
        private String sale;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Nullable
        public String getName() {
            return name;
        }

        public void setName(@Nullable String name) {
            this.name = name;
        }

        @Nullable
        public String getImg() {
            return img;
        }

        public void setImg(@Nullable String img) {
            this.img = img;
        }

        @Nullable
        public String getPhone() {
            return phone;
        }

        public void setPhone(@Nullable String phone) {
            this.phone = phone;
        }

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        @Nullable
        public String getNew() {
            return New;
        }

        public void setNew(@Nullable String aNew) {
            New = aNew;
        }

        @Nullable
        public String getFeatured() {
            return featured;
        }

        public void setFeatured(@Nullable String featured) {
            this.featured = featured;
        }

        @Nullable
        public String getSale() {
            return sale;
        }

        public void setSale(@Nullable String sale) {
            this.sale = sale;
        }
    }
}
