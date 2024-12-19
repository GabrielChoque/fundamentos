package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;
    public CreateUser(UserService userService){
        this.userService = userService;

    }
    public User save(UserDto newUser) {
        User user = User.builder()
                .name(newUser.getName())
                .email(newUser.getEmail())
                .birthDate(newUser.getBirthDate())
                .build();
        return userService.save(user);
    }
}
