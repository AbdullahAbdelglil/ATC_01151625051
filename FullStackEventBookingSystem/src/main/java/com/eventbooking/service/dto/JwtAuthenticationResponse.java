package com.eventbooking.service.dto;

public class JwtAuthenticationResponse {

    private String accessToken;

    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static JwtAuthenticationResponse emptyResponse() {
        return new JwtAuthenticationResponse();
    }
}
