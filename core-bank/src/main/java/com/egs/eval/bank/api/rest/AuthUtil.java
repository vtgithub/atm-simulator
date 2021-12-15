package com.egs.eval.bank.api.rest;

public class AuthUtil {
    public static String getBearerToken(String authorizationHeader) {
        return authorizationHeader.replaceAll("Bearer ", "");
    }
}
