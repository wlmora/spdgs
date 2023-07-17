package com.business.security.user;

import com.business.security.generated.types.UserInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> users(String emailFilter) {
        if(emailFilter == null) {
            return userRepository.findAll();
        }
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getEmail().contains(emailFilter))
                .collect(Collectors.toList());
    }

    @Override
    public User addUser(UserInput userInput) {
        return userRepository.save(
                User.builder()
                        .firstname(userInput.getFirstname())
                        .lastname(userInput.getLastname())
                        .email(userInput.getEmail())
                        .password(userInput.getPassword())
                        .isEnable(false)
                        .build());
    }

    @Override
    public Boolean addUsers(List<UserInput> userInputs) {
        List<User> users = new ArrayList<>();
        for (UserInput input : userInputs) {
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
