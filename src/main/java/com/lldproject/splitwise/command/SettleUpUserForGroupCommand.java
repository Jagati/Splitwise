package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.SettleUpController;
import com.lldproject.splitwise.dto.SettleUpUserForGroupRequestDto;
import com.lldproject.splitwise.dto.SettleUpUserForGroupResponseDto;
import com.lldproject.splitwise.dto.SettleUpUserRequestDto;
import com.lldproject.splitwise.dto.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class SettleUpUserForGroupCommand implements Command {
    SettleUpController settleUpController;

    @Override
    public void execute(String input) {
        String words[]=input.split(" ");
        int userId=Integer.parseInt(words[1]);
        int groupId=Integer.parseInt(words[2]);

        SettleUpUserForGroupRequestDto requestDTO=new SettleUpUserForGroupRequestDto();
        requestDTO.setUserId(userId);
        requestDTO.setGroupId(groupId);
        SettleUpUserForGroupResponseDto responseDto = settleUpController.settleUpUserForAGroup(requestDTO);
        System.out.println(responseDto.getMessage());
    }

    @Override
    public boolean matches(String input) {
        String words[] = input.split(" ");
        return words[0].equals("settleupuser") && words.length==3;
    }
 }
