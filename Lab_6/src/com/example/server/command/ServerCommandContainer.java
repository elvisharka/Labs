package com.example.server.command;

import com.example.server.command.api.ServerCommand;
import com.example.server.command.impl.NoCommand;

import java.util.Map;

/**
 * Used for commands holding.
 */
public class ServerCommandContainer {

    private final Map<String, ServerCommand> commands;

    public ServerCommandContainer(Map<String, ServerCommand> commands) {
        this.commands = commands;
    }

    public Map<String, ServerCommand> getCommands() {
        return commands;
    }

    public ServerCommand findCommandByName(String commandName) {
        return commands.getOrDefault(commandName, new NoCommand());
    }
}
