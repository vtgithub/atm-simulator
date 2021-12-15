package com.egs.eval.bank.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FailureModel {
    private int numberOfAttempts;
    private String message;
}
