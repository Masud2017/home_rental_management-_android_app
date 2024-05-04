package com.home_rental.home_rental_management.Models;

public class TransactionHistory {
    private int id;
    private int user_id_second;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id_second() {
        return user_id_second;
    }

    public void setUser_id_second(int user_id_second) {
        this.user_id_second = user_id_second;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
