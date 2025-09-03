package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.UserController;
import com.lldproject.splitwise.dto.UserLoginRequestDto;
import com.lldproject.splitwise.dto.UserLoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLoginCommand implements Command {
    @Autowired
    UserController userController;
    @Override
    public void execute(String input) {
        String[] words = input.split(" ");
        String email = words[1];
        String password = words[2];

        UserLoginRequestDto requestDto = new UserLoginRequestDto();
        requestDto.setEmail(email);
        requestDto.setPassword(password);
        UserLoginResponseDto responseDto = userController.userLogin(requestDto);
        System.out.println(responseDto.getMessage());
    }
    @Override
    public boolean matches(String input) {
        String[] words = input.split(" ");
        return words[0].equals("login") && words.length == 3;
    }
}
