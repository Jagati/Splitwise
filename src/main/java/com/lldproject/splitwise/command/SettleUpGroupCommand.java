package com.lldproject.splitwise.command;

import com.lldproject.splitwise.controller.SettleUpController;
import com.lldproject.splitwise.dto.ResponseStatus;
import com.lldproject.splitwise.dto.SettleUpGroupRequestDto;
import com.lldproject.splitwise.dto.SettleUpGroupResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SettleUpGroupCommand implements Command{
    @Autowired
    SettleUpController settleUpController;

    @Override
    public void execute(String input) {
        String words[]=input.split(" ");
        int groupId=Integer.parseInt(words[1]);

        SettleUpGroupRequestDto requestDTO=new SettleUpGroupRequestDto();
        requestDTO.setGroupId(groupId);
        SettleUpGroupResponseDto responseDto = settleUpController.settleUpGroup(requestDTO);
        System.out.println(responseDto.getMessage());
    }

    @Override
    public boolean matches(String input) {
        String words[] = input.split(" ");
        return words[0].equals("settleup") && words.length==2;
    }

}
