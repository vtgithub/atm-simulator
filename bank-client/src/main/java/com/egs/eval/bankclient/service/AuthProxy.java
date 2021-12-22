package com.egs.eval.bankclient.service;

import com.egs.eval.bankclient.remote.model.AuthRequest;
import com.egs.eval.bankclient.remote.model.AuthTypesResponse;
import com.egs.eval.bankclient.remote.model.TokenResponse;

public interface AuthProxy {

    AuthTypesResponse getAuthTypes();
    TokenResponse getFreshToken(AuthRequest authRequest);
}
