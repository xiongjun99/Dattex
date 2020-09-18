package com.temp.dattex.bean;

import java.util.List;

public class FuncListBean {

    /**
     * data : {"android":[{"address":"string","content":"string","createTime":"2020-08-20T15:52:48.874Z","createUser":"string","icon":"string","id":0,"isEnabled":"string","memo":"string","plantform":"string","title":"string","type":"string","updateTime":"2020-08-20T15:52:48.874Z","updateUser":"string"}],"ios":[{"address":"string","content":"string","createTime":"2020-08-20T15:52:48.874Z","createUser":"string","icon":"string","id":0,"isEnabled":"string","memo":"string","plantform":"string","title":"string","type":"string","updateTime":"2020-08-20T15:52:48.874Z","updateUser":"string"}]}
     * errCode : 0
     * errMsg : string
     * success : true
     */

        private List<AndroidBean> android;
        private List<IosBean> ios;

        public List<AndroidBean> getAndroid() {
            return android;
        }

        public void setAndroid(List<AndroidBean> android) {
            this.android = android;
        }

        public List<IosBean> getIos() {
            return ios;
        }

        public void setIos(List<IosBean> ios) {
            this.ios = ios;
        }

        public static class AndroidBean {
            /**
             * address : string
             * content : string
             * createTime : 2020-08-20T15:52:48.874Z
             * createUser : string
             * icon : string
             * id : 0
             * isEnabled : string
             * memo : string
             * plantform : string
             * title : string
             * type : string
             * updateTime : 2020-08-20T15:52:48.874Z
             * updateUser : string
             */

            private String address;
            private String content;
            private String createTime;
            private String createUser;
            private String icon;
            private int id;
            private String isEnabled;
            private String memo;
            private String plantform;
            private String title;
            private String type;
            private String updateTime;
            private String updateUser;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsEnabled() {
                return isEnabled;
            }

            public void setIsEnabled(String isEnabled) {
                this.isEnabled = isEnabled;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPlantform() {
                return plantform;
            }

            public void setPlantform(String plantform) {
                this.plantform = plantform;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }

        public static class IosBean {
            /**
             * address : string
             * content : string
             * createTime : 2020-08-20T15:52:48.874Z
             * createUser : string
             * icon : string
             * id : 0
             * isEnabled : string
             * memo : string
             * plantform : string
             * title : string
             * type : string
             * updateTime : 2020-08-20T15:52:48.874Z
             * updateUser : string
             */

            private String address;
            private String content;
            private String createTime;
            private String createUser;
            private String icon;
            private int id;
            private String isEnabled;
            private String memo;
            private String plantform;
            private String title;
            private String type;
            private String updateTime;
            private String updateUser;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIsEnabled() {
                return isEnabled;
            }

            public void setIsEnabled(String isEnabled) {
                this.isEnabled = isEnabled;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getPlantform() {
                return plantform;
            }

            public void setPlantform(String plantform) {
                this.plantform = plantform;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }
        }
}
