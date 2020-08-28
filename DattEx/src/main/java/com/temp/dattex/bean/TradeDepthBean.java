package com.temp.dattex.bean;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradeDepthBean {

    /**
     * symbol : btcusdt
     * asks : [{"11794.78":7.108973},{"11794.96":0.261733},{"11794.97":0.086585},{"11795.18":0.139009},{"11795.25":0.042398}]
     * bids : [{"11794.78":7.108973},{"11794.96":0.261733},{"11794.97":0.086585},{"11795.18":0.139009},{"11795.25":0.042398}]
     */

    private String symbol;
    private List<Map<String,String>> asks;
    private List<Map<String,String>> bids;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Map<String,String>> getAsks() {
        return asks;
    }

    public void setAsks(List<Map<String,String>> asks) {
        this.asks = asks;
    }

    public List<Map<String,String>> getBids() {
        return bids;
    }

    public void setBids(List<Map<String,String>> bids) {
        this.bids = bids;
    }

    public static class AsksBean {
        @SerializedName("11794.78")
        private double _$_117947883; // FIXME check this code
        @SerializedName("11794.96")
        private double _$_1179496251; // FIXME check this code
        @SerializedName("11794.97")
        private double _$_1179497252; // FIXME check this code
        @SerializedName("11795.18")
        private double _$_1179518230; // FIXME check this code
        @SerializedName("11795.25")
        private double _$_11795252; // FIXME check this code
        public double get_$_117947883() {
            return _$_117947883;
        }
        public void set_$_117947883(double _$_117947883) {
            this._$_117947883 = _$_117947883;
        }

        public double get_$_1179496251() {
            return _$_1179496251;
        }

        public void set_$_1179496251(double _$_1179496251) {
            this._$_1179496251 = _$_1179496251;
        }

        public double get_$_1179497252() {
            return _$_1179497252;
        }

        public void set_$_1179497252(double _$_1179497252) {
            this._$_1179497252 = _$_1179497252;
        }

        public double get_$_1179518230() {
            return _$_1179518230;
        }

        public void set_$_1179518230(double _$_1179518230) {
            this._$_1179518230 = _$_1179518230;
        }

        public double get_$_11795252() {
            return _$_11795252;
        }

        public void set_$_11795252(double _$_11795252) {
            this._$_11795252 = _$_11795252;
        }
    }

    public static class BidsBean {
        @SerializedName("11794.78")
        private double _$_1179478301; // FIXME check this code
        @SerializedName("11794.96")
        private double _$_117949628; // FIXME check this code
        @SerializedName("11794.97")
        private double _$_117949718; // FIXME check this code
        @SerializedName("11795.18")
        private double _$_1179518135; // FIXME check this code
        @SerializedName("11795.25")
        private double _$_117952550; // FIXME check this code

        public double get_$_1179478301() {
            return _$_1179478301;
        }

        public void set_$_1179478301(double _$_1179478301) {
            this._$_1179478301 = _$_1179478301;
        }

        public double get_$_117949628() {
            return _$_117949628;
        }

        public void set_$_117949628(double _$_117949628) {
            this._$_117949628 = _$_117949628;
        }

        public double get_$_117949718() {
            return _$_117949718;
        }

        public void set_$_117949718(double _$_117949718) {
            this._$_117949718 = _$_117949718;
        }

        public double get_$_1179518135() {
            return _$_1179518135;
        }

        public void set_$_1179518135(double _$_1179518135) {
            this._$_1179518135 = _$_1179518135;
        }

        public double get_$_117952550() {
            return _$_117952550;
        }

        public void set_$_117952550(double _$_117952550) {
            this._$_117952550 = _$_117952550;
        }
    }
}
