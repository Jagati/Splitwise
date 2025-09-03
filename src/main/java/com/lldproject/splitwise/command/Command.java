package com.lldproject.splitwise.command;

import org.springframework.stereotype.Component;

@Component
public interface Command {
    void execute(String input);
    boolean matches(String input);
}
