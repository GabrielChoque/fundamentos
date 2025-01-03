package com.fundamentos.springboot.fundamentos.caseuse;

import com.fundamentos.springboot.fundamentos.dto.UserDto;
import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {
    private UserService userService;
    public UpdateUser(UserService userService){
        this.userService = userService;
        
    }

    public User update(UserDto newUser, Long id) {
        return userService.update(newUser, id);
    }
}
