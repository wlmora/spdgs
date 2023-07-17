package com.business.security.user;

import com.business.security.generated.types.UserInput;

import java.util.List;

public interface IUserService {
    List<User> users(String emailFilter);
    User addUser(UserInput userInput);
    Boolean addUsers(List<UserInput> userInputs);

}
