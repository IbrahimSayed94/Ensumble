package com.ensumble.Model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse extends BaseResponse
{

    /**
     * Products : [{"id":4,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":6,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":7,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":8,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":9,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":10,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":11,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null,"new":1},{"id":3,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-22 00:00:00","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":1,"date":"2019-06-22","image":{"id":1,"img":"15613146101.jpg"},"new":1}]
     * code : 200
     * message : code sent successfully
     */


    @Nullable
    private List<ProductsBean> Products;


    @Nullable
    public List<ProductsBean> getProducts() {
        return Products;
    }

    public void setProducts(@Nullable List<ProductsBean> products) {
        Products = products;
    }

    public static class ProductsBean {
        /**
         * id : 4
         * ar_title : جزمه
         * en_title : shoes
         * ar_description : جزمه بيضه
         * en_description : sh2ahbbas
         * confirm : 1
         * img : null
         * show : null
         * price : 523
         * sale : 0
         * salePrice : null
         * cat_id : 1
         * created_by : 1
         * updated_by : null
         * created_at : 2019-06-23 12:30:10
         * updated_at : 2019-06-23 12:30:10
         * user : [{"id":1,"name":"admin","img":null,"phone":"01117878258"}]
         * favorite : 0
         * view : 0
         * date : 2019-06-23
         * image : null
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
        private  Image image;


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
        public Image getImage() {
            return image;
        }

        public void setImage(@Nullable Image image) {
            this.image = image;
        }

        public static class UserBean {
            /**
             * id : 1
             * name : admin
             * img : null
             * phone : 01117878258
             */

            @Nullable
            private int id;
            @Nullable
            private String name;
            @Nullable
            private String img;
            @Nullable
            private String phone;

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
        }

        public static class Image
        {
            @Nullable
            private String img;

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
