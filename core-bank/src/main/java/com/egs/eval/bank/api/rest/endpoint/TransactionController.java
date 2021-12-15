package com.egs.eval.bank.api.rest.endpoint;

import com.egs.eval.bank.api.rest.TransactionFacade;
import com.egs.eval.bank.api.rest.model.BalanceResponse;
import com.egs.eval.bank.api.rest.model.PredefinedValueResponse;
import com.egs.eval.bank.api.rest.model.TransactionRequest;
import com.egs.eval.bank.api.rest.model.TransactionResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@Api(tags = "transaction apis")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionFacade facade;
    private final HttpServletRequest request;
    @PostMapping("/withdraw")
    public TransactionResponse doWithdraw(@RequestBody TransactionRequest transactionRequest){
        return facade.withdraw(transactionRequest, request.getHeader("Authorization"));
    }

    @PostMapping("/deposit")
    public TransactionResponse doDeposit(@RequestBody TransactionRequest transactionRequest){
        return facade.deposit(transactionRequest, request.getHeader("Authorization"));
    }

    @GetMapping("/withdraw/predefines")
    public List<PredefinedValueResponse> getPredefinedValues(){
        return facade.getPredefinedValueList();
    }

    @GetMapping("/balance")
    public BalanceResponse getBalance(){
        return facade.getBalance(request.getHeader("Authorization"));
    }
}
