package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyCartResponse extends BaseResponse
{

    /**
     * code : 200
     * data : [{"id":9,"product_id":"14","user_id":"12","count":1,"color":"احمر","size":"S","created_at":"2019-07-28 17:41:13","updated_at":"2019-07-28 17:41:13","productDetails":[{"id":14,"ar_title":"قميص","en_title":"shirt","ar_description":"سيشسي يشسي سشيشس","en_description":"sadas das dasd asad","confirm":"1","img":"1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg","show":null,"price":"450","sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":null,"created_at":"2019-07-28 07:10:52","updated_at":"2019-07-28 07:10:52"}],"Category":[{"ar_title":"قمصان","en_title":"Shirts"}]},{"id":10,"product_id":"14","user_id":"12","count":1,"color":"احمر","size":"S","created_at":"2019-07-28 20:15:59","updated_at":"2019-07-28 20:15:59","productDetails":[{"id":14,"ar_title":"قميص","en_title":"shirt","ar_description":"سيشسي يشسي سشيشس","en_description":"sadas das dasd asad","confirm":"1","img":"1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg","show":null,"price":"450","sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":null,"created_at":"2019-07-28 07:10:52","updated_at":"2019-07-28 07:10:52"}],"Category":[{"ar_title":"قمصان","en_title":"Shirts"}]},{"id":11,"product_id":"11","user_id":"12","count":1,"color":"red","size":"S","created_at":"2019-07-28 20:16:28","updated_at":"2019-07-28 20:16:28","productDetails":[{"id":11,"ar_title":"لوريم ايبسوم","en_title":"dress","ar_description":"لوريم ايبسوملوريم ايبسوملوريم ايبسوم","en_description":"asdasdasd","confirm":"1","img":"1564228793.studio21.jpg","show":null,"price":"500","sale":"0","salePrice":null,"instack":1,"cat_id":"12","mainCat":null,"color":"red - blue - black","size":"S-M-L-XL","created_by":"17","updated_by":null,"created_at":"2019-07-27 11:59:53","updated_at":"2019-07-27 11:59:53"}],"Category":[{"ar_title":"فساتين","en_title":"Dresses"}]}]
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
         * id : 9
         * product_id : 14
         * user_id : 12
         * count : 1
         * color : احمر
         * size : S
         * created_at : 2019-07-28 17:41:13
         * updated_at : 2019-07-28 17:41:13
         * productDetails : [{"id":14,"ar_title":"قميص","en_title":"shirt","ar_description":"سيشسي يشسي سشيشس","en_description":"sadas das dasd asad","confirm":"1","img":"1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg","show":null,"price":"450","sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":null,"created_at":"2019-07-28 07:10:52","updated_at":"2019-07-28 07:10:52"}]
         * Category : [{"ar_title":"قمصان","en_title":"Shirts"}]
         */

        @Nullable
        private int id;
        @Nullable
        private String product_id;
        @Nullable
        private String user_id;
        @Nullable
        @SerializedName("count")
        private int countX;
        @Nullable
        private List<ProductDetailsBean> productDetails;
        @Nullable
        private List<CategoryBean> Category;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Nullable
        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(@Nullable String product_id) {
            this.product_id = product_id;
        }

        @Nullable
        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(@Nullable String user_id) {
            this.user_id = user_id;
        }

        public int getCountX() {
            return countX;
        }

        public void setCountX(int countX) {
            this.countX = countX;
        }

        @Nullable
        public List<ProductDetailsBean> getProductDetails() {
            return productDetails;
        }

        public void setProductDetails(@Nullable List<ProductDetailsBean> productDetails) {
            this.productDetails = productDetails;
        }

        @Nullable
        public List<CategoryBean> getCategory() {
            return Category;
        }

        public void setCategory(@Nullable List<CategoryBean> category) {
            Category = category;
        }

        public static class ProductDetailsBean {
            /**
             * id : 14
             * ar_title : قميص
             * en_title : shirt
             * ar_description : سيشسي يشسي سشيشس
             * en_description : sadas das dasd asad
             * confirm : 1
             * img : 1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg
             * show : null
             * price : 450
             * sale : 1
             * salePrice : null
             * instack : 1
             * cat_id : 6
             * mainCat : null
             * color : احمر - اصفر - اسود
             * size : S -M -L -XL - XXL
             * created_by : 5
             * updated_by : null
             * created_at : 2019-07-28 07:10:52
             * updated_at : 2019-07-28 07:10:52
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
            private String img;
            @Nullable
            private double price;
            @Nullable
            private double salePrice;
            @Nullable
            private int count;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

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
            public String getImg() {
                return img;
            }

            public void setImg(@Nullable String img) {
                this.img = img;
            }

            @Nullable
            public double getPrice() {
                return price;
            }

            public void setPrice(@Nullable double price) {
                this.price = price;
            }

            @Nullable
            public double getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(@Nullable double salePrice) {
                this.salePrice = salePrice;
            }
        }

        public static class CategoryBean {
            /**
             * ar_title : قمصان
             * en_title : Shirts
             */

            @Nullable
            private String ar_title;
            @Nullable
            private String en_title;

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
        }
    }
}
