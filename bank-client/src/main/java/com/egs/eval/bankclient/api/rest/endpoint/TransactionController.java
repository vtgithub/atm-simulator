package com.egs.eval.bankclient.api.rest.endpoint;

import com.egs.eval.bankclient.remote.model.BalanceResponse;
import com.egs.eval.bankclient.remote.model.PredefinedValueResponse;
import com.egs.eval.bankclient.remote.model.TransactionRequest;
import com.egs.eval.bankclient.remote.model.TransactionResponse;
import com.egs.eval.bankclient.service.TransactionProxy;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/public/v1/transaction")
@Api(tags = "transaction apis")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionProxy proxy;
    private final HttpServletRequest request;

    @PostMapping("/withdraw")
    public TransactionResponse doWithdraw(@RequestBody TransactionRequest transactionRequest){
        return proxy.withdraw(request.getHeader("Authorization"), transactionRequest);
    }

    @PostMapping("/deposit")
    public TransactionResponse doDeposit(@RequestBody TransactionRequest transactionRequest){
        return proxy.deposit(request.getHeader("Authorization"), transactionRequest);
    }

    @PostMapping("/rollback/{transactionId}")
    public TransactionResponse doRollback(@PathVariable("transactionId") String transactionId){
        return proxy.rollback(request.getHeader("Authorization"), transactionId);
    }

    @GetMapping("/withdraw/predefines")
    public List<PredefinedValueResponse> getPredefinedValues(){
        return proxy.getPredefinedValues(request.getHeader("Authorization"));
    }

    @SneakyThrows
    @GetMapping("/balance")
    public BalanceResponse getBalance(){
        return proxy.getBalance(request.getHeader("Authorization"));
    }
}
