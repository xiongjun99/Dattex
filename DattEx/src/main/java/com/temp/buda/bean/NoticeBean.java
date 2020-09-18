package com.temp.buda.bean;

import java.util.List;

public class NoticeBean {
        /**
         * size : 100
         * total : 1
         * rows : [{"id":1,"title":"测试文章标题","type":1,"author":"佚名","source":"baidu","orders":1,"isEnabled":1,"isSticky":1,"publishTime":"2020-09-09 11:40:59","memo":"78","createUserId":83,"createTime":"2020-09-09 11:41:07"}]
         * start : 0
         * summber : null
         */

        private int size;
        private int total;
        private int start;
        private Object summber;
        private List<RowsBean> rows;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public Object getSummber() {
            return summber;
        }

        public void setSummber(Object summber) {
            this.summber = summber;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 1
             * title : 测试文章标题
             * type : 1
             * author : 佚名
             * source : baidu
             * orders : 1
             * isEnabled : 1
             * isSticky : 1
             * publishTime : 2020-09-09 11:40:59
             * memo : 78
             * createUserId : 83
             * createTime : 2020-09-09 11:41:07
             * content
             */

            private int id;
            private String title;
            private int type;
            private String author;
            private String source;
            private int orders;
            private int isEnabled;
            private int isSticky;
            private String publishTime;
            private String memo;
            private int createUserId;
            private String createTime;
            private String content;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }


            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public int getIsEnabled() {
                return isEnabled;
            }

            public void setIsEnabled(int isEnabled) {
                this.isEnabled = isEnabled;
            }

            public int getIsSticky() {
                return isSticky;
            }

            public void setIsSticky(int isSticky) {
                this.isSticky = isSticky;
            }

            public String getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
}
