package com.temp.dattex.bean;

public class UpdateBean {
    /**
     * android : {"id":"6","platform":"android","version":"1.0.1","updateLink":"http://www.baidu.com","downloadLink":"http://www.baidu.com","minUpdateVer":"1","memo":"更新说明\\\\n1、功能优化\\\\n2、修复bug\\\\n3、支持新功能\\\\n4、支持新功能\\\\n5、支持新功能\\\\n6、支持新功能\\\\n7、支持新功能\\\\n8、支持新功能","size":"100","sizeWgt":"50","forcedUpdateVer":"1.0.0","createTime":"2020-08-15 15:01:37"}
     */

    private AndroidBean android;

    public AndroidBean getAndroid() {
        return android;
    }

    public void setAndroid(AndroidBean android) {
        this.android = android;
    }

    public static class AndroidBean {
        /**
         * id : 6
         * platform : android
         * version : 1.0.1
         * updateLink : http://www.baidu.com
         * downloadLink : http://www.baidu.com
         * minUpdateVer : 1
         * memo : 更新说明\\n1、功能优化\\n2、修复bug\\n3、支持新功能\\n4、支持新功能\\n5、支持新功能\\n6、支持新功能\\n7、支持新功能\\n8、支持新功能
         * size : 100
         * sizeWgt : 50
         * forcedUpdateVer : 1.0.0
         * createTime : 2020-08-15 15:01:37
         */

        private String id;
        private String platform;
        private String version;
        private String updateLink;
        private String downloadLink;
        private String minUpdateVer;
        private String memo;
        private String size;
        private String sizeWgt;
        private String forcedUpdateVer;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUpdateLink() {
            return updateLink;
        }

        public void setUpdateLink(String updateLink) {
            this.updateLink = updateLink;
        }

        public String getDownloadLink() {
            return downloadLink;
        }

        public void setDownloadLink(String downloadLink) {
            this.downloadLink = downloadLink;
        }

        public String getMinUpdateVer() {
            return minUpdateVer;
        }

        public void setMinUpdateVer(String minUpdateVer) {
            this.minUpdateVer = minUpdateVer;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getSizeWgt() {
            return sizeWgt;
        }

        public void setSizeWgt(String sizeWgt) {
            this.sizeWgt = sizeWgt;
        }

        public String getForcedUpdateVer() {
            return forcedUpdateVer;
        }

        public void setForcedUpdateVer(String forcedUpdateVer) {
            this.forcedUpdateVer = forcedUpdateVer;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
