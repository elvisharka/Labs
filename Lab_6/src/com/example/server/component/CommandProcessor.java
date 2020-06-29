package com.example.server.component;

import com.example.common.model.StudyGroup;
import com.example.server.command.ServerCommandContainer;
import com.example.server.command.api.ServerCommand;
import com.example.common.dto.ServerCommandNameAndArg;
import com.example.common.dto.ServerCommandProcessingResult;

import java.util.Map;

public class CommandProcessor {
    private ServerCommandContainer serverCommandContainer;

    public CommandProcessor(ServerCommandContainer serverCommandContainer) {
        this.serverCommandContainer = serverCommandContainer;
    }

    public ServerCommandProcessingResult process(ServerCommandNameAndArg serverCommandNameAndArg, Map<String, StudyGroup> studyGroupMap) {
        ServerCommand command = serverCommandContainer
                .findCommandByName(serverCommandNameAndArg
                        .commandName);
        return command.execute(serverCommandNameAndArg.commandArg);
    }
}
