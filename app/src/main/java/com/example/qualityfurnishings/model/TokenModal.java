package com.example.qualityfurnishings.model;

public class TokenModal {
    public TokenModal() {

    }

    public TokenModal(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    String token;
}
