package com.example.command.impl;

import com.example.command.api.Command;

import java.util.Map;

/**
 * Shows all available commands.
 */
public class Help implements Command {
    private Map<String, Command> commandMap;

    public Help(Map<String, Command> commandMap) {
        this.commandMap = commandMap;
    }

    @Override
    public String description() {
        return "shows all available commands";
    }

    @Override
    public String execute(String commandArg) {
        commandMap.forEach(
                (commandName, command) -> System.out.println(commandName + " => " + command.description())
        );
        return "";
    }
}
