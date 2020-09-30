package com.temp.buda.bean;

import java.util.List;

public class BannerItemBean {
        /**
         * size : 2
         * total : 0
         * rows : [{"id":11,"url":"/img/e118734876559b368f1527e44e609b78.png","weight":1,"type":0,"enable":1},{"id":10,"url":"/img/9f0b485ed342714d34253709fece8b8f.png","weight":0,"type":0,"enable":1}]
         * start : 1
         * summber : null
         */

        private int size;
        private int total;
        private int start;
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

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * id : 11
             * url : /img/e118734876559b368f1527e44e609b78.png
             * weight : 1
             * type : 0
             * enable : 1
             */

            private int id;
            private String url;
            private String link;
            private int weight;
            private int type;
            private int enable;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }


            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
}
