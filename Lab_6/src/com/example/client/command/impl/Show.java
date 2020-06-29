package com.example.client.command.impl;

import com.example.client.command.api.ClientCommand;
import com.example.client.exception.ClientCommandExecutionInvalidValuesException;
import com.example.common.Constants;
import com.example.common.dto.ServerCommandNameAndArg;

public class Show implements ClientCommand {
    @Override
    public String description() {
        return "Shows all elements in collection";
    }

    @Override
    public ServerCommandNameAndArg execute() throws ClientCommandExecutionInvalidValuesException {
        return new ServerCommandNameAndArg(
                Constants.SERVER_COMMAND_NAME_SHOW,
                null
        );
    }
}
