package com.egs.eval.bankclient.api.rest.endpoint;

import com.egs.eval.bankclient.remote.model.AuthRequest;
import com.egs.eval.bankclient.service.AuthProxy;
import com.egs.eval.bankclient.remote.model.AuthTypesResponse;
import com.egs.eval.bankclient.remote.model.TokenResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/auth")
@Api(tags = "authentication apis")
@RequiredArgsConstructor
public class AuthController {

    private final AuthProxy authProxy;

    @GetMapping("/types")
    AuthTypesResponse getAuthTypes(){
        return authProxy.getAuthTypes();
    }

    @PostMapping
    TokenResponse getToken(@RequestBody AuthRequest authRequest){
        return authProxy.getFreshToken(authRequest);
    }
}
