package com.example.qualityfurnishings.model;

public class TokenModal {
    public TokenModal() {

    }


    public TokenModal(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String token;
    String id;
}
