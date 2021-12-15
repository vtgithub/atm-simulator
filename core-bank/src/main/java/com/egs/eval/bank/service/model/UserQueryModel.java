package com.egs.eval.bank.service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserQueryModel {
    private String pin;
    private String card;
    private String fingerprint;
}
