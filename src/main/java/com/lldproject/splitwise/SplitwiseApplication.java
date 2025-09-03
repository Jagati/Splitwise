package com.lldproject.splitwise;

import com.lldproject.splitwise.command.CommandExecutor;
import com.lldproject.splitwise.command.UserLoginCommand;
import com.lldproject.splitwise.command.UserSignUpCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {
    private static Scanner scanner = new Scanner(System.in);
    private CommandExecutor commandExecutor;
    @Autowired
    public SplitwiseApplication(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }
    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();
        commandExecutor.execute(input);
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

}
