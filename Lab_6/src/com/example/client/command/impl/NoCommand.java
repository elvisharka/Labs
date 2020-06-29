package com.example.client.command.impl;

import com.example.client.command.api.ClientCommand;
import com.example.common.dto.ServerCommandNameAndArg;

public class NoCommand implements ClientCommand {
    @Override
    public String description() {
        return "No command";
    }

    @Override
    public ServerCommandNameAndArg execute() {
        return null;
    }
}
