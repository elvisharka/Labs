package com.example.server.command.impl;

import com.example.common.dto.ServerCommandProcessingResult;
import com.example.server.command.api.ServerCommand;
import com.example.server.component.CollectionHolder;

public class Show implements ServerCommand {

    private final CollectionHolder collectionHolder;

    public Show(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public ServerCommandProcessingResult execute(Object commandArg) {
        return new ServerCommandProcessingResult (
                collectionHolder,
                true,
                "Found all elements in connection"
        );
    }
}
