package com.egs.eval.bank.api.rest.model;

import com.egs.eval.bank.service.model.AuthenticationMechanism;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class AuthDto {
    @ApiModelProperty(example = "'1111222233334444'", required = true)
    @NotBlank(message = "cardNumber can not be blank")
    private String cardNumber;
    @ApiModelProperty(example = "'PIN'", required = true)
    @NotNull(message = "authType can not be null")
    private AuthenticationMechanism authType;
    @ApiModelProperty(example = "'qwertyuiop'", required = true)
    @NotBlank(message = "authType can not be blank")
    private String value;
}
