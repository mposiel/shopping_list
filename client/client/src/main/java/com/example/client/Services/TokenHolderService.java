package com.example.client.Services;

public class TokenHolderService {
    private static TokenHolderService instance;
    private String token;

    private TokenHolderService() {}

    public static synchronized TokenHolderService getInstance() {
        if (instance == null) {
            instance = new TokenHolderService();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token;
    }

}