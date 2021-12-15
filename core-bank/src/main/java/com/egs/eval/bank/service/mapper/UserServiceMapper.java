package com.egs.eval.bank.service.mapper;

import com.egs.eval.bank.dal.entity.User;
import com.egs.eval.bank.service.model.UserQueryModel;
import org.mapstruct.*;

import java.util.Set;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        imports = Set.class
)
public interface UserServiceMapper {

    @Mapping(target = "cardSet", expression = "java((queryModel.getCard() == null) ? null : Set.of(queryModel.getCard()))")
    User getUserFromUserQueryModel(UserQueryModel queryModel);
}
