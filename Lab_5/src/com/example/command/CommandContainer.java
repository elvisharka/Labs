package com.example.command;

import com.example.command.api.Command;
import com.example.command.impl.NoCommand;

import java.util.Map;

/**
 * Used for commands holding.
 */
public class CommandContainer {

    private Map<String, Command> commands;

    public CommandContainer(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public void setCommands(Map<String, Command> commands) {
        this.commands = commands;
    }

    public Command findCommandByName(String commandName) {
        return commands.entrySet()
                .stream()
                .filter(entry -> commandName.equals(entry.getKey()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(new NoCommand());
    }
}
