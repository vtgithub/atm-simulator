package com.egs.eval.bank.api.rest.endpoint;

import com.egs.eval.bank.api.rest.AuthenticationFacade;
import com.egs.eval.bank.api.rest.model.AuthDto;
import com.egs.eval.bank.api.rest.model.TokenResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/v1/auth")
@Api(tags = "authentication apis")
@RequiredArgsConstructor
public class PublicAuthenticationController {

    private final AuthenticationFacade authenticationFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenResponse authenticate(@Validated @RequestBody AuthDto authDto){
        return authenticationFacade.authenticate(authDto);
    }
}
