package com.egs.eval.bank.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthTypesResponse {
    private List<String> strategies;
}
