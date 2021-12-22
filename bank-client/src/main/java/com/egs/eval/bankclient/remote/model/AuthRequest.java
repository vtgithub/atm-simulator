package com.egs.eval.bankclient.remote.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String cardNumber;
    private String authType;
    private String value;
}
