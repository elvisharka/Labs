package com.example.server.command.api;

import com.example.common.dto.ServerCommandProcessingResult;

public interface ServerCommand {
    ServerCommandProcessingResult execute(Object commandArg);
}
