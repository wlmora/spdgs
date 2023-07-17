package com.business.security.user;

import com.business.security.generated.DgsConstants.MUTATION;
import com.business.security.generated.types.UserInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class UsersDatafetcher {

    private final UserService userService;

    @DgsQuery
    public List<User> users(@InputArgument String emailFilter) {
        return userService.users(emailFilter);
    }

    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.AddUser)
    public User addUser(@InputArgument("input") UserInput userInput) {
        return userService.addUser(userInput);
    }
    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.AddUsers)
    public Boolean addUsers(@InputArgument("input") List<UserInput> userInputs) {
        return userService.addUsers(userInputs);
    }
}
