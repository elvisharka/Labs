package com.example.client.command.api;

import com.example.client.exception.ClientCommandExecutionInvalidValuesException;
import com.example.common.dto.ServerCommandNameAndArg;

public interface ClientCommand {
    String description();
    ServerCommandNameAndArg execute() throws ClientCommandExecutionInvalidValuesException;
}
