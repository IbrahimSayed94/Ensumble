package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

import butterknife.BindView;

public class ProductsResponse extends BaseResponse
{

    /**
     * Products : [{"id":3,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":1,"date":"2019-06-23","image":{"id":1,"img":"15613146101.jpg"}},{"id":4,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":6,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":7,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":8,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":9,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":10,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null},{"id":11,"ar_title":"جزمه","en_title":"shoes","ar_description":"جزمه بيضه","en_description":"sh2ahbbas","confirm":"1","img":null,"show":null,"price":"523","sale":"0","salePrice":null,"cat_id":"1","created_by":"1","updated_by":null,"created_at":"2019-06-23 12:30:10","updated_at":"2019-06-23 12:30:10","user":[{"id":1,"name":"admin","img":null,"phone":"01117878258"}],"favorite":0,"view":0,"date":"2019-06-23","image":null}]
     * Status : success
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
         * id : 3
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
         * view : 1
         * date : 2019-06-23
         * image : {"id":1,"img":"15613146101.jpg"}
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
        private int favorite;
        @Nullable
        private int view;
        @Nullable
        private String date;
        @Nullable
        private ImageBean image;
        @Nullable
        private List<UserBean> user;


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

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
            this.favorite = favorite;
        }

        public int getView() {
            return view;
        }

        public void setView(int view) {
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
        public ImageBean getImage() {
            return image;
        }

        public void setImage(@Nullable ImageBean image) {
            this.image = image;
        }

        @Nullable
        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(@Nullable List<UserBean> user) {
            this.user = user;
        }

        public static class ImageBean {
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
    }
}
