package com.egs.eval.bank.dal.repository;

import com.egs.eval.bank.dal.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByCardSet(String cardNumber);
}
