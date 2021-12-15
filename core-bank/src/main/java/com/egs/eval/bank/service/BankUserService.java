package com.egs.eval.bank.service;

import com.egs.eval.bank.dal.entity.Entity;
import com.egs.eval.bank.dal.entity.User;
import com.egs.eval.bank.dal.repository.UserRepository;
import com.egs.eval.bank.service.mapper.UserServiceMapper;
import com.egs.eval.bank.service.model.UserQueryModel;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class BankUserService implements UserService {

    private final UserRepository repository;
    private final UserServiceMapper mapper;

    public BankUserService(UserRepository repository, UserServiceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<String> getUserId(UserQueryModel queryModel) {
        User userAsExample = mapper.getUserFromUserQueryModel(queryModel);
        return repository.findOne(Example.of(userAsExample)).map(Entity::getId);
    }

    @Override
    public int addTodayFailedLoginAttempts(String cardNo) {
        User userAsExample = mapper.getUserFromUserQueryModel(UserQueryModel.builder().card(cardNo).build());
        return repository.findOne(Example.of(userAsExample))
                .map(this::increaseUserTodayAttempts)
                .orElseThrow(() -> new NotAuthenticatedException("cardNumber is not valid. cardNo: " + cardNo));

    }

    // -----------------------------------------------------------------------------------------------------------------

    private Integer increaseUserTodayAttempts(User user) {
        user.setTodayFailedLoginAttempts(increaseAttempts(user.getTodayFailedLoginAttempts()));
        repository.save(user);
        return user.getTodayFailedLoginAttempts();
    }

    private Integer increaseAttempts(Integer todayFailedLoginAttempts) {
        return Objects.isNull(todayFailedLoginAttempts) ? 0 : todayFailedLoginAttempts + 1;
    }
}
