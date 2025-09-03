package com.lldproject.splitwise.command;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandExecutor {
    private List<Command> commands;

    public CommandExecutor(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
        this.commands.add(new UserSignUpCommand());
        this.commands.add(new UserLoginCommand());
    }

    public void add(Command command) {
        commands.add(command);
    }
    public void remove(Command command) {
        commands.remove(command);
    }
    public void execute(String input) {
        for(Command command : commands) {
            if(command.matches(input)) {
                command.execute(input);
            }
        }

    }
}
