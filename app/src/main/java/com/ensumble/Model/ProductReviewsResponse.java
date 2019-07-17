package com.ensumble.Model;

import androidx.annotation.Nullable;

import java.util.List;

public class ProductReviewsResponse extends BaseResponse
{

    /**
     * code : 200
     * rate : 4
     * count : 1
     * Message : [{"id":1,"message":null,"user_id":"1","value":"4","created_at":"2019-07-15 00:00:00","user":[{"id":1,"name":"admin","img":"1563223165.d1.jpg"}],"date":"2019-07-15"}]
     */


    @Nullable
    private float rate;
    @Nullable
    private int count;
    @Nullable
    private List<MessageBean> Message;

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Nullable
    public List<MessageBean> getMessage() {
        return Message;
    }

    public void setMessage(@Nullable List<MessageBean> message) {
        Message = message;
    }

    public static class MessageBean {
        /**
         * id : 1
         * message : null
         * user_id : 1
         * value : 4
         * created_at : 2019-07-15 00:00:00
         * user : [{"id":1,"name":"admin","img":"1563223165.d1.jpg"}]
         * date : 2019-07-15
         */

        @Nullable
        private int id;
        @Nullable
        private String message;
        @Nullable
        private String user_id;
        @Nullable
        private float value;
        @Nullable
        private String created_at;
        @Nullable
        private String date;
        @Nullable
        private List<UserBean> user;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Nullable
        public String getMessage() {
            return message;
        }

        public void setMessage(@Nullable String message) {
            this.message = message;
        }

        @Nullable
        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(@Nullable String user_id) {
            this.user_id = user_id;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        @Nullable
        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(@Nullable String created_at) {
            this.created_at = created_at;
        }

        @Nullable
        public String getDate() {
            return date;
        }

        public void setDate(@Nullable String date) {
            this.date = date;
        }

        @Nullable
        public List<UserBean> getUser() {
            return user;
        }

        public void setUser(@Nullable List<UserBean> user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 1
             * name : admin
             * img : 1563223165.d1.jpg
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
    }
}
