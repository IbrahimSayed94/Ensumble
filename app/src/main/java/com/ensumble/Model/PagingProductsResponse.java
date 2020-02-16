package com.ensumble.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class PagingProductsResponse
{


    /**
     * Products : {"current_page":1,"data":[{"id":17,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"dasdas","en_description":"dasdasda","confirm":"1","img":"1565368197.1 (1).jpg","show":null,"price":450,"sale":"0","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":null,"created_at":"2019-08-09 16:29:57","updated_at":"2019-08-09 16:29:57","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":1,"date":"2019-08-09","image":{"id":28,"img":"15653681970.jpeg"},"new":0},{"id":16,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"ghfhg","en_description":"fhgddgf","confirm":"1","img":"1565367706.c3299622-7328-40ba-addc-eef0733a2899.jpeg","show":null,"price":450,"sale":"0","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":null,"created_at":"2019-08-09 16:21:46","updated_at":"2019-08-09 16:21:46","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":2,"date":"2019-08-09","image":{"id":26,"img":"15653677060.jpg"},"new":0},{"id":15,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"dasdas das","en_description":"dasdas","confirm":"1","img":"1565232856.1 (1).jpg","show":null,"price":450,"sale":"1","salePrice":"300","instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":"5","created_at":"2019-08-08 02:54:16","updated_at":"2019-08-08 04:56:05","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":1,"date":"2019-08-08","image":{"id":23,"img":"15652328560.jpg"},"new":0},{"id":14,"ar_title":"قميص","en_title":"shirt","ar_description":"سيشسي يشسي سشيشس","en_description":"sadas das dasd asad","confirm":"1","img":"1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg","show":null,"price":450,"sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":null,"created_at":"2019-07-28 07:10:52","updated_at":"2019-07-28 07:10:52","user":[{"id":5,"name":"admin","img":"1563223165.d1.jpg","phone":"0111757575"}],"favorite":0,"view":4,"date":"2019-07-28","image":{"id":20,"img":"15642978520.jpg"},"new":0},{"id":13,"ar_title":"قميص","en_title":"shirt","ar_description":"يسشي يشسي","en_description":"sdas dasdas","confirm":"1","img":"1564297672.b783601f7e1b2e8fd65c35ec2bee8dee8b69c0f5.jpg","show":null,"price":450,"sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":"5","created_at":"2019-07-28 07:07:52","updated_at":"2019-07-28 07:08:34","user":[{"id":5,"name":"admin","img":"1563223165.d1.jpg","phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-07-28","image":{"id":18,"img":"15642976720.jpeg"},"new":0}],"first_page_url":"https://ensumble.imanaytam.com/api/Products?page=1","from":1,"last_page":2,"last_page_url":"https://ensumble.imanaytam.com/api/Products?page=2","next_page_url":"https://ensumble.imanaytam.com/api/Products?page=2","path":"https://ensumble.imanaytam.com/api/Products","per_page":5,"prev_page_url":null,"to":5,"total":6}
     * Status : success
     * code : 200
     * message : code sent successfully
     */

    @Nullable
    private ProductsBean Products;
    @Nullable
    private String Status;
    @Nullable
    private int code;
    @Nullable
    private String message;


    public ProductsBean getProducts() {
        return Products;
    }

    public void setProducts(ProductsBean products) {
        Products = products;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ProductsBean {
        /**
         * current_page : 1
         * data : [{"id":17,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"dasdas","en_description":"dasdasda","confirm":"1","img":"1565368197.1 (1).jpg","show":null,"price":450,"sale":"0","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":null,"created_at":"2019-08-09 16:29:57","updated_at":"2019-08-09 16:29:57","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":1,"date":"2019-08-09","image":{"id":28,"img":"15653681970.jpeg"},"new":0},{"id":16,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"ghfhg","en_description":"fhgddgf","confirm":"1","img":"1565367706.c3299622-7328-40ba-addc-eef0733a2899.jpeg","show":null,"price":450,"sale":"0","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":null,"created_at":"2019-08-09 16:21:46","updated_at":"2019-08-09 16:21:46","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":2,"date":"2019-08-09","image":{"id":26,"img":"15653677060.jpg"},"new":0},{"id":15,"ar_title":"تى شيرت","en_title":"shirt","ar_description":"dasdas das","en_description":"dasdas","confirm":"1","img":"1565232856.1 (1).jpg","show":null,"price":450,"sale":"1","salePrice":"300","instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"7","updated_by":"5","created_at":"2019-08-08 02:54:16","updated_at":"2019-08-08 04:56:05","user":[{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}],"favorite":0,"view":1,"date":"2019-08-08","image":{"id":23,"img":"15652328560.jpg"},"new":0},{"id":14,"ar_title":"قميص","en_title":"shirt","ar_description":"سيشسي يشسي سشيشس","en_description":"sadas das dasd asad","confirm":"1","img":"1564297852.New-2017-Cotton-Striped-font-b-Mens-b-font-Dress-Shirts-Long-Sleeve-Casual-Shirt-font.jpg","show":null,"price":450,"sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":null,"created_at":"2019-07-28 07:10:52","updated_at":"2019-07-28 07:10:52","user":[{"id":5,"name":"admin","img":"1563223165.d1.jpg","phone":"0111757575"}],"favorite":0,"view":4,"date":"2019-07-28","image":{"id":20,"img":"15642978520.jpg"},"new":0},{"id":13,"ar_title":"قميص","en_title":"shirt","ar_description":"يسشي يشسي","en_description":"sdas dasdas","confirm":"1","img":"1564297672.b783601f7e1b2e8fd65c35ec2bee8dee8b69c0f5.jpg","show":null,"price":450,"sale":"1","salePrice":null,"instack":1,"cat_id":"6","mainCat":null,"color":"احمر - اصفر - اسود","size":"S -M -L -XL - XXL","created_by":"5","updated_by":"5","created_at":"2019-07-28 07:07:52","updated_at":"2019-07-28 07:08:34","user":[{"id":5,"name":"admin","img":"1563223165.d1.jpg","phone":"0111757575"}],"favorite":0,"view":2,"date":"2019-07-28","image":{"id":18,"img":"15642976720.jpeg"},"new":0}]
         * first_page_url : https://ensumble.imanaytam.com/api/Products?page=1
         * from : 1
         * last_page : 2
         * last_page_url : https://ensumble.imanaytam.com/api/Products?page=2
         * next_page_url : https://ensumble.imanaytam.com/api/Products?page=2
         * path : https://ensumble.imanaytam.com/api/Products
         * per_page : 5
         * prev_page_url : null
         * to : 5
         * total : 6
         */

        @Nullable
        private int current_page;
        @Nullable
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 17
             * ar_title : تى شيرت
             * en_title : shirt
             * ar_description : dasdas
             * en_description : dasdasda
             * confirm : 1
             * img : 1565368197.1 (1).jpg
             * show : null
             * price : 450
             * sale : 0
             * salePrice : null
             * instack : 1
             * cat_id : 6
             * mainCat : null
             * color : احمر - اصفر - اسود
             * size : S -M -L -XL - XXL
             * created_by : 7
             * updated_by : null
             * created_at : 2019-08-09 16:29:57
             * updated_at : 2019-08-09 16:29:57
             * user : [{"id":7,"name":"mohamed magdy","img":"1563223165.d1.jpg","phone":"1011875157"}]
             * favorite : 0
             * view : 1
             * date : 2019-08-09
             * image : {"id":28,"img":"15653681970.jpeg"}
             * new : 0
             */

            @androidx.annotation.Nullable
            private int id;
            @androidx.annotation.Nullable
            private String ar_title;
            @androidx.annotation.Nullable
            private String en_title;
            @androidx.annotation.Nullable
            private String ar_description;
            @androidx.annotation.Nullable
            private String en_description;
            @androidx.annotation.Nullable
            private String confirm;
            @androidx.annotation.Nullable
            private String img;
            @androidx.annotation.Nullable
            private String show;
            @androidx.annotation.Nullable
            private String price;
            @androidx.annotation.Nullable
            private String sale;
            @androidx.annotation.Nullable
            private String salePrice;
            @androidx.annotation.Nullable
            private String cat_id;
            @androidx.annotation.Nullable
            private String created_by;
            @androidx.annotation.Nullable
            private String updated_by;
            @androidx.annotation.Nullable
            private String created_at;
            @androidx.annotation.Nullable
            private String updated_at;
            @androidx.annotation.Nullable
            private String favorite;
            @androidx.annotation.Nullable
            private String view;
            @androidx.annotation.Nullable
            private String date;
            @androidx.annotation.Nullable
            @SerializedName("new")
            private String newX;
            @androidx.annotation.Nullable
            private List<ProductsResponse.ProductsBean.UserBean> user;
            @androidx.annotation.Nullable
            private ProductsResponse.ProductsBean.Image image;


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @androidx.annotation.Nullable
            public String getAr_title() {
                return ar_title;
            }

            public void setAr_title(@androidx.annotation.Nullable String ar_title) {
                this.ar_title = ar_title;
            }

            @androidx.annotation.Nullable
            public String getEn_title() {
                return en_title;
            }

            public void setEn_title(@androidx.annotation.Nullable String en_title) {
                this.en_title = en_title;
            }

            @androidx.annotation.Nullable
            public String getAr_description() {
                return ar_description;
            }

            public void setAr_description(@androidx.annotation.Nullable String ar_description) {
                this.ar_description = ar_description;
            }

            @androidx.annotation.Nullable
            public String getEn_description() {
                return en_description;
            }

            public void setEn_description(@androidx.annotation.Nullable String en_description) {
                this.en_description = en_description;
            }

            @androidx.annotation.Nullable
            public String getConfirm() {
                return confirm;
            }

            public void setConfirm(@androidx.annotation.Nullable String confirm) {
                this.confirm = confirm;
            }

            @androidx.annotation.Nullable
            public String getImg() {
                return img;
            }

            public void setImg(@androidx.annotation.Nullable String img) {
                this.img = img;
            }

            @androidx.annotation.Nullable
            public String getShow() {
                return show;
            }

            public void setShow(@androidx.annotation.Nullable String show) {
                this.show = show;
            }

            @androidx.annotation.Nullable
            public String getPrice() {
                return price;
            }

            public void setPrice(@androidx.annotation.Nullable String price) {
                this.price = price;
            }

            @androidx.annotation.Nullable
            public String getSale() {
                return sale;
            }

            public void setSale(@androidx.annotation.Nullable String sale) {
                this.sale = sale;
            }

            @androidx.annotation.Nullable
            public String getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(@androidx.annotation.Nullable String salePrice) {
                this.salePrice = salePrice;
            }

            @androidx.annotation.Nullable
            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(@androidx.annotation.Nullable String cat_id) {
                this.cat_id = cat_id;
            }

            @androidx.annotation.Nullable
            public String getCreated_by() {
                return created_by;
            }

            public void setCreated_by(@androidx.annotation.Nullable String created_by) {
                this.created_by = created_by;
            }

            @androidx.annotation.Nullable
            public String getUpdated_by() {
                return updated_by;
            }

            public void setUpdated_by(@androidx.annotation.Nullable String updated_by) {
                this.updated_by = updated_by;
            }

            @androidx.annotation.Nullable
            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(@androidx.annotation.Nullable String created_at) {
                this.created_at = created_at;
            }

            @androidx.annotation.Nullable
            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(@androidx.annotation.Nullable String updated_at) {
                this.updated_at = updated_at;
            }

            @androidx.annotation.Nullable
            public String getFavorite() {
                return favorite;
            }

            public void setFavorite(@androidx.annotation.Nullable String favorite) {
                this.favorite = favorite;
            }

            @androidx.annotation.Nullable
            public String getView() {
                return view;
            }

            public void setView(@androidx.annotation.Nullable String view) {
                this.view = view;
            }

            @androidx.annotation.Nullable
            public String getDate() {
                return date;
            }

            public void setDate(@androidx.annotation.Nullable String date) {
                this.date = date;
            }

            @androidx.annotation.Nullable
            public String getNewX() {
                return newX;
            }

            public void setNewX(@androidx.annotation.Nullable String newX) {
                this.newX = newX;
            }

            @androidx.annotation.Nullable
            public List<ProductsResponse.ProductsBean.UserBean> getUser() {
                return user;
            }

            public void setUser(@androidx.annotation.Nullable List<ProductsResponse.ProductsBean.UserBean> user) {
                this.user = user;
            }

            @androidx.annotation.Nullable
            public ProductsResponse.ProductsBean.Image getImage() {
                return image;
            }

            public void setImage(@androidx.annotation.Nullable ProductsResponse.ProductsBean.Image image) {
                this.image = image;
            }

            public static class UserBean {
                /**
                 * id : 1
                 * name : admin
                 * img : null
                 * phone : 01117878258
                 */

                @androidx.annotation.Nullable
                private int id;
                @androidx.annotation.Nullable
                private String name;
                @androidx.annotation.Nullable
                private String img;
                @androidx.annotation.Nullable
                private String phone;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                @androidx.annotation.Nullable
                public String getName() {
                    return name;
                }

                public void setName(@androidx.annotation.Nullable String name) {
                    this.name = name;
                }

                @androidx.annotation.Nullable
                public String getImg() {
                    return img;
                }

                public void setImg(@androidx.annotation.Nullable String img) {
                    this.img = img;
                }

                @androidx.annotation.Nullable
                public String getPhone() {
                    return phone;
                }

                public void setPhone(@androidx.annotation.Nullable String phone) {
                    this.phone = phone;
                }
            }

            public static class Image
            {
                @androidx.annotation.Nullable
                private String img;

                @androidx.annotation.Nullable
                public String getImg() {
                    return img;
                }

                public void setImg(@androidx.annotation.Nullable String img) {
                    this.img = img;
                }
            }

        }
    }
}
