package com.egs.eval.bankclient.remote.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthTypesResponse {
    private List<String> strategies;

}
