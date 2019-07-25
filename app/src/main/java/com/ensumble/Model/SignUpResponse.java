package com.ensumble.Model;

import androidx.annotation.Nullable;

public class SignUpResponse extends BaseResponse
{

    /**
     * code : 200
     * message : Register new user done successfully
     * userdata : {"name":"admin","phone":"01117878254","email":"admi2n@gmfail.com","gender":null,"admin":"0","lat":null,"lng":null,"en_description":null,"ar_description":null,"updated_at":"2019-06-26 13:27:27","created_at":"2019-06-26 13:27:27","id":2}
     */

    @Nullable
    private UserdataBean userdata;

    @Nullable
    public UserdataBean getUserdata() {
        return userdata;
    }

    public void setUserdata(@Nullable UserdataBean userdata) {
        this.userdata = userdata;
    }

    public static class UserdataBean {
        /**
         * name : admin
         * phone : 01117878254
         * email : admi2n@gmfail.com
         * gender : null
         * admin : 0
         * lat : null
         * lng : null
         * en_description : null
         * ar_description : null
         * updated_at : 2019-06-26 13:27:27
         * created_at : 2019-06-26 13:27:27
         * id : 2
         */

        @Nullable
        private String name;
        @Nullable
        private String phone;
        @Nullable
        private String email;
        @Nullable
        private String id;
        @Nullable
        private String address;
        @Nullable
        private  String img;

        @Nullable
        public String getImg() {
            return img;
        }

        public void setImg(@Nullable String img) {
            this.img = img;
        }

        @Nullable
        public String getAddress() {
            return address;
        }

        public void setAddress(@Nullable String address) {
            this.address = address;
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
        public String getEmail() {
            return email;
        }

        public void setEmail(@Nullable String email) {
            this.email = email;
        }

        @Nullable
        public String getId() {
            return id;
        }

        public void setId(@Nullable String id) {
            this.id = id;
        }
    }
}
