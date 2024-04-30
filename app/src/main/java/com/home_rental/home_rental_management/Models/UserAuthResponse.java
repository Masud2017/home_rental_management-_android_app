package com.home_rental.home_rental_management.Models;

public class UserAuthResponse {
    private String email;
    private Integer expires;
    private String access_token;
    private String token_type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
