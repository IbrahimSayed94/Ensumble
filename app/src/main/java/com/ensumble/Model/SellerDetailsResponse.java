package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Native;
import java.util.List;

public class SellerDetailsResponse extends BaseResponse
{

    /**
     * User : {"id":5,"name":"admin","phone":"0111757575","img":null,"Products":[{"id":4,"ar_title":"فستان","en_title":"dress","ar_description":"asdasdasdasdsad","en_description":"asdasdas","confirm":"1","img":"1561730835.w2.jpg","show":null,"price":"400","sale":"1","salePrice":"350","instack":0,"cat_id":"4","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:07:15","updated_at":"2019-06-28 14:07:15","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-06-28","image":null,"new":0},{"id":3,"ar_title":"جيبه","en_title":"skirt","ar_description":"asa","en_description":"as","confirm":"1","img":"1561730788.w4.jpg","show":null,"price":"250","sale":"1","salePrice":"123","instack":0,"cat_id":"4","created_by":"5","updated_by":"1","created_at":"2019-06-28 14:06:28","updated_at":"2019-07-03 18:28:30","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-06-28","image":{"id":1,"img":"15613146101.jpg"},"new":0},{"id":2,"ar_title":"تيشرت","en_title":"t-shirt","ar_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","en_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","confirm":"1","img":"1561730722.m4.jpg","show":null,"price":"600","sale":"1","salePrice":"450","instack":0,"cat_id":"3","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:05:22","updated_at":"2019-06-28 14:05:22","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":3,"date":"2019-06-28","image":null,"new":0},{"id":1,"ar_title":"قميص","en_title":"shemis","ar_description":"dsafdasfdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","en_description":"dsafdasfdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","confirm":"1","img":"1561730665.m1.jpg","show":null,"price":"500","sale":"1","salePrice":"350","instack":0,"cat_id":"3","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:04:25","updated_at":"2019-06-28 14:04:25","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":1,"date":"2019-06-28","image":null,"new":0}],"New":1,"featured":1,"sale":1,"rate":0}
     * Status : success
     * code : 200
     * message : code sent successfully
     */

    @Nullable
    private UserBeanX User;

    @Nullable
    public UserBeanX getUser() {
        return User;
    }

    public void setUser(@Nullable UserBeanX user) {
        User = user;
    }

    public static class UserBeanX {
        /**
         * id : 5
         * name : admin
         * phone : 0111757575
         * img : null
         * Products : [{"id":4,"ar_title":"فستان","en_title":"dress","ar_description":"asdasdasdasdsad","en_description":"asdasdas","confirm":"1","img":"1561730835.w2.jpg","show":null,"price":"400","sale":"1","salePrice":"350","instack":0,"cat_id":"4","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:07:15","updated_at":"2019-06-28 14:07:15","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-06-28","image":null,"new":0},{"id":3,"ar_title":"جيبه","en_title":"skirt","ar_description":"asa","en_description":"as","confirm":"1","img":"1561730788.w4.jpg","show":null,"price":"250","sale":"1","salePrice":"123","instack":0,"cat_id":"4","created_by":"5","updated_by":"1","created_at":"2019-06-28 14:06:28","updated_at":"2019-07-03 18:28:30","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-06-28","image":{"id":1,"img":"15613146101.jpg"},"new":0},{"id":2,"ar_title":"تيشرت","en_title":"t-shirt","ar_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","en_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","confirm":"1","img":"1561730722.m4.jpg","show":null,"price":"600","sale":"1","salePrice":"450","instack":0,"cat_id":"3","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:05:22","updated_at":"2019-06-28 14:05:22","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":3,"date":"2019-06-28","image":null,"new":0},{"id":1,"ar_title":"قميص","en_title":"shemis","ar_description":"dsafdasfdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","en_description":"dsafdasfdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","confirm":"1","img":"1561730665.m1.jpg","show":null,"price":"500","sale":"1","salePrice":"350","instack":0,"cat_id":"3","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:04:25","updated_at":"2019-06-28 14:04:25","user":[{"id":5,"name":"admin","img":null,"phone":"0111757575"}],"favorite":0,"view":1,"date":"2019-06-28","image":null,"new":0}]
         * New : 1
         * featured : 1
         * sale : 1
         * rate : 0
         */

        @Nullable
        private int id;
        @Nullable
        private String name;
        @Nullable
        private String phone;
        @Nullable
        private String img;
        @Nullable
        private String New;
        @Nullable
        private String featured;
        @Nullable
        private String sale;
        @Nullable
        private float rate;
        @Nullable
        private List<ProductsResponse.ProductsBean> Products;

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
        public String getPhone() {
            return phone;
        }

        public void setPhone(@Nullable String phone) {
            this.phone = phone;
        }

        @Nullable
        public String getImg() {
            return img;
        }

        public void setImg(@Nullable String img) {
            this.img = img;
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

        public float getRate() {
            return rate;
        }

        public void setRate(float rate) {
            this.rate = rate;
        }

        @Nullable
        public List<ProductsResponse.ProductsBean> getProducts() {
            return Products;
        }

        public void setProducts(@Nullable List<ProductsResponse.ProductsBean> products) {
            Products = products;
        }
    }
}
