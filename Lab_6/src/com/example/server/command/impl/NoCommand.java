package com.example.server.command.impl;

import com.example.server.command.api.ServerCommand;
import com.example.common.dto.ServerCommandProcessingResult;


/**
 * Represents no command.
 */
public class NoCommand implements ServerCommand {

    @Override
    public ServerCommandProcessingResult execute(Object commandArg) {
        return new ServerCommandProcessingResult(true, "No such command");
    }
}
