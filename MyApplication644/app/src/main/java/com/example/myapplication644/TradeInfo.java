package com.example.myapplication644;

import com.google.firebase.auth.FirebaseUser;

public class TradeInfo {

    private String feature_acc, name_trade, min_request;
    private String uId, name_trader;

    public TradeInfo() {
    }

    public TradeInfo(String feature_acc, String name_trade, String min_request, String uId, String name_trader) {
        this.feature_acc = feature_acc;
        this.name_trade = name_trade;
        this.min_request = min_request;
        this.uId = uId;
        this.name_trader = name_trader;
    }

    public TradeInfo(String s, String s1, String s2) {
    }

    public String getFeature_acc() {
        return feature_acc;
    }

    public void setFeature_acc(String feature_acc) {
        this.feature_acc = feature_acc;
    }

    public String getName_trade() {
        return name_trade;
    }

    public void setName_trade(String name_trade) {
        this.name_trade = name_trade;
    }

    public String getMin_request() {
        return min_request;
    }

    public void setMin_request(String min_request) {
        this.min_request = min_request;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName_trader() {
        return name_trader;
    }

    public void setName_trader(String name_trader) {
        this.name_trader = name_trader;
    }
}
