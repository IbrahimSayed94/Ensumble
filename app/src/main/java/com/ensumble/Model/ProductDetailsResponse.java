package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetailsResponse extends BaseResponse
{

    /**
     * Product : {"id":3,"ar_title":"جيبه","en_title":"skirt","ar_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","en_description":"شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل","confirm":"1","img":"1561730788.w4.jpg","show":null,"price":"250","sale":"1","salePrice":"150","instack":0,"cat_id":"4","created_by":"5","updated_by":null,"created_at":"2019-06-28 14:06:28","updated_at":"2019-06-28 14:06:28","user":[{"id":5,"name":"admin","img":null}],"favorite":1,"view":1,"date":"2019-06-28","images":[{"id":1,"img":"15613146101.jpg"},{"id":2,"img":"15613146102.jpg"}],"new":1}
     * code : 200
     * message : code sent successfully
     */

    @Nullable
    private ProductBean Product;

    @Nullable
    public ProductBean getProduct() {
        return Product;
    }

    public void setProduct(@Nullable ProductBean product) {
        Product = product;
    }

    public static class ProductBean {
        /**
         * id : 3
         * ar_title : جيبه
         * en_title : skirt
         * ar_description : شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل
         * en_description : شسيشسششششششششششششششششششششششششششسيسشلسيشلشسيشسششششششششششششششششششششششششششسيسشلسيشل
         * confirm : 1
         * img : 1561730788.w4.jpg
         * show : null
         * price : 250
         * sale : 1
         * salePrice : 150
         * instack : 0
         * cat_id : 4
         * created_by : 5
         * updated_by : null
         * created_at : 2019-06-28 14:06:28
         * updated_at : 2019-06-28 14:06:28
         * user : [{"id":5,"name":"admin","img":null}]
         * favorite : 1
         * view : 1
         * date : 2019-06-28
         * images : [{"id":1,"img":"15613146101.jpg"},{"id":2,"img":"15613146102.jpg"}]
         * new : 1
         */

        @Nullable
        private int id;
        @Nullable
        private String ar_title;
        @Nullable
        private String en_title;
        @Nullable
        private String ar_description;
        @Nullable
        private String en_description;
        @Nullable
        private String confirm;
        @Nullable
        private String img;
        @Nullable
        private String show;
        @Nullable
        private String price;
        @Nullable
        private String sale;
        @Nullable
        private String salePrice;
        @Nullable
        private String instack;
        @Nullable
        private String cat_id;
        @Nullable
        private String created_by;
        @Nullable
        private String updated_by;
        @Nullable
        private String created_at;
        @Nullable
        private String updated_at;
        @Nullable
        private String favorite;
        @Nullable
        private String view;
        @Nullable
        private String date;
        @Nullable
        @SerializedName("new")
        private String newX;
        @Nullable
        private List<UserBean> user;
        @Nullable
        private List<ImagesBean> images;

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

        @Nullable
        public String getShow() {
            return show;
        }

        public void setShow(@Nullable String show) {
            this.show = show;
        }

        @Nullable
        public String getPrice() {
            return price;
        }

        public void setPrice(@Nullable String price) {
            this.price = price;
        }

        @Nullable
        public String getSale() {
            return sale;
        }

        public void setSale(@Nullable String sale) {
            this.sale = sale;
        }

        @Nullable
        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(@Nullable String salePrice) {
            this.salePrice = salePrice;
        }

        @Nullable
        public String getInstack() {
            return instack;
        }

        public void setInstack(@Nullable String instack) {
            this.instack = instack;
        }

        @Nullable
        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(@Nullable String cat_id) {
            this.cat_id = cat_id;
        }

        @Nullable
        public String getCreated_by() {
            return created_by;
        }

        public void setCreated_by(@Nullable String created_by) {
            this.created_by = created_by;
        }

        @Nullable
        public String getUpdated_by() {
            return updated_by;
        }

        public void setUpdated_by(@Nullable String updated_by) {
            this.updated_by = updated_by;
        }

        @Nullable
        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(@Nullable String created_at) {
            this.created_at = created_at;
        }

        @Nullable
        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(@Nullable String updated_at) {
            this.updated_at = updated_at;
        }

        @Nullable
        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(@Nullable String favorite) {
            this.favorite = favorite;
        }

        @Nullable
        public String getView() {
            return view;
        }

        public void setView(@Nullable String view) {
            this.view = view;
        }

        @Nullable
        public String getDate() {
            return date;
        }

        public void setDate(@Nullable String date) {
            this.date = date;
        }

        @Nullable
        public String getNewX() {
            return newX;
        }

        public void setNewX(@Nullable String newX) {
            this.newX = newX;
        }

        @Nullable
        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(@Nullable List<UserBean> user) {
            this.user = user;
        }

        @Nullable
        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(@Nullable List<ImagesBean> images) {
            this.images = images;
        }

        public static class UserBean {
            /**
             * id : 5
             * name : admin
             * img : null
             */

            @Nullable
            private int id;
            @Nullable
            private String name;
            @Nullable
            private String img;

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


        }

        public static class ImagesBean {
            /**
             * id : 1
             * img : 15613146101.jpg
             */

            @Nullable
            private int id;
            @Nullable
            private String img;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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
}
