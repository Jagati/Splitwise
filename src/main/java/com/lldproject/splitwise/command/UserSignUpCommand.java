package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.UserController;
import com.lldproject.splitwise.dto.UserSignUpRequestDto;
import com.lldproject.splitwise.dto.UserSignUpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSignUpCommand implements Command {
    @Autowired
    UserController userController;

    @Override
    public void execute(String input) {
        String[] words = input.split(" ");
        String username = words[1];
        String email = words[2];
        String password = words[3];
        UserSignUpRequestDto requestDto = new UserSignUpRequestDto();
        requestDto.setName(username);
        requestDto.setEmail(email);
        requestDto.setPassword(password);
        UserSignUpResponseDto responseDto = userController.userSignUp(requestDto);
        System.out.println(responseDto.getMessage());
    }
    @Override
    public boolean matches(String input) {
        String[] words = input.split(" ");
        return words[0].equals("signup") && words.length == 4;
    }
}
