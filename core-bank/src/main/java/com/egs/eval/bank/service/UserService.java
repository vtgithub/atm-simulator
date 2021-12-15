package com.egs.eval.bank.service;

import com.egs.eval.bank.service.model.UserQueryModel;

import java.util.Optional;

public interface UserService {
    Optional<String> getUserId(UserQueryModel queryModel);

    /**
     *
     * @param cardNo
     * @return number of today attempts
     */
    int addTodayFailedLoginAttempts(String cardNo);
}
