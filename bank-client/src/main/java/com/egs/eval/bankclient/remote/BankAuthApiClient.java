package com.egs.eval.bankclient.remote;

import com.egs.eval.bankclient.remote.model.AuthRequest;
import com.egs.eval.bankclient.remote.model.AuthTypesResponse;
import com.egs.eval.bankclient.remote.model.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank-auth-client", url = "${bank.auth.base.url}", configuration = DefaultFeignErrorDecoder.class)
public interface BankAuthApiClient {

    @GetMapping(path = "${bank.auth.getTypes.url}")
    AuthTypesResponse getAuthMechanisms();

    @PostMapping
    TokenResponse getToken(@RequestBody AuthRequest authRequest);

}