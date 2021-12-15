package com.egs.eval.bank.dal.repository;

import com.egs.eval.bank.dal.entity.Transaction;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    @Aggregation(pipeline = {
            "{$match:{'userId' : ?0}}",
            "{$group :{'_id': null, 'total' : { $sum: '$value' } }}"
    })
    Long sumValues(String userId);

    void deleteByTransactionId(String transactionId);
}
