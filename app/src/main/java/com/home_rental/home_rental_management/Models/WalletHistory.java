package com.home_rental.home_rental_management.Models;

public class WalletHistory {
    String msg;
    String payment_amount;
    String payment_platform;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getPayment_platform() {
        return payment_platform;
    }

    public void setPayment_platform(String payment_platform) {
        this.payment_platform = payment_platform;
    }
}
