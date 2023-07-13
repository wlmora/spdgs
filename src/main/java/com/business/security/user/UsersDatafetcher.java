package com.business.security.user;

import com.netflix.graphql.dgs.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.business.security.generated.DgsConstants.MUTATION;
@DgsComponent
@RequiredArgsConstructor
public class UsersDatafetcher {

    private final UserRepository userRepository;

    @DgsQuery
    public List<User> users(@InputArgument String emailFilter) {
        if(emailFilter == null) {
            return userRepository.findAll();
        }
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().contains(emailFilter))
                .collect(Collectors.toList());
    }

    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.AddUser)
    public User addUser(@InputArgument("input") UserInput userInput) {
        return userRepository.save(
            User.builder()
                .firstname(userInput.getFirstname())
                .lastname(userInput.getLastname())
                .email(userInput.getEmail())
                .password(userInput.getPassword())
                .isEnable(false)
                .build());
    }
    @DgsData(parentType = MUTATION.TYPE_NAME, field = MUTATION.AddUsers)
    public Boolean addUsers(@InputArgument("input") List<UserInput> userInput) {

        List<User> users = new ArrayList<>();
        for (UserInput input : userInput) {
            users.add(userRepository.save(
                    User.builder()
                            .firstname(input.getFirstname())
                            .lastname(input.getLastname())
                            .email(input.getEmail())
                            .password(input.getPassword())
                            .isEnable(false)
                            .build()));
        }
        return !users.isEmpty();
    }
}
