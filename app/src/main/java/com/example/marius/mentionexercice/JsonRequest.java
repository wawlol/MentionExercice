package com.example.marius.mentionexercice;


import org.apache.http.client.methods.HttpGet;

public class JsonRequest {

    private static final String ACCEPT_HEADER = "application/json";
    private static final String ACCEPT_LANGUAGE_HEADER = "fr";
    private static final String TOKEN_HEADER = "Bearer ZTlhODAzMmMxZGU4NGI4NDA2OTA0MzFmOTIwZTZkY2ViMTdiYjg4YmQwNWNmNTEyMjc3NzBlOGZjMzJjNTZlOQ";

    public static HttpGet request(String mHref) {
        HttpGet request = new HttpGet(mHref);
        request.addHeader("Accept", ACCEPT_HEADER);
        request.addHeader("Accept-Language", ACCEPT_LANGUAGE_HEADER);
        request.addHeader("Authorization", TOKEN_HEADER);
        return request;
    }
}
