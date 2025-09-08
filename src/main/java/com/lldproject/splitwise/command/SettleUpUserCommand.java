package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.SettleUpController;
import com.lldproject.splitwise.dto.SettleUpGroupRequestDto;
import com.lldproject.splitwise.dto.SettleUpGroupResponseDto;
import com.lldproject.splitwise.dto.SettleUpUserRequestDto;
import com.lldproject.splitwise.dto.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SettleUpUserCommand implements Command {
    SettleUpController settleUpController;

    @Override
    public void execute(String input) {
        String words[]=input.split(" ");
        int userId=Integer.parseInt(words[1]);

        SettleUpUserRequestDto requestDTO=new SettleUpUserRequestDto();
        requestDTO.setUserId(userId);
        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(requestDTO);
        System.out.println(responseDto.getMessage());
    }

    @Override
    public boolean matches(String input) {
        String words[] = input.split(" ");
        return words[0].equals("settleupuser") && words.length==2;
    }
}
