package com.lldproject.splitwise.controller;

import com.lldproject.splitwise.dto.*;
import com.lldproject.splitwise.exception.UserAlreadyExistsException;
import com.lldproject.splitwise.exception.UserNotFoundException;
import com.lldproject.splitwise.model.User;
import com.lldproject.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    public UserSignUpResponseDto userSignUp(UserSignUpRequestDto userSignUpRequestDto) {
        UserSignUpResponseDto responseDto = new UserSignUpResponseDto();
        try{
            User user = userService.signup(userSignUpRequestDto.getName(), userSignUpRequestDto.getEmail(), userSignUpRequestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
            responseDto.setMessage("User signed up successfully");
        }
        catch(UserAlreadyExistsException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("User already exists");
        }

        return responseDto;
    }
    public UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {
        UserLoginResponseDto responseDto = new UserLoginResponseDto();
        try{
            boolean loginSuccess = userService.login(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
            if(loginSuccess){
                responseDto.setResponseStatus(ResponseStatus.SUCCESS);
                responseDto.setMessage("User logged in successfully");
            }
            else{
                responseDto.setResponseStatus(ResponseStatus.FAILURE);
                responseDto.setMessage("Incorrect password. Could not login");
            }
        }
        catch(UserNotFoundException e){
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            responseDto.setMessage("User not found "+e.getMessage());
        }
        return responseDto;
    }
}
