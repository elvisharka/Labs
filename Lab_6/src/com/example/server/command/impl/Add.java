package com.example.server.command.impl;

import com.example.server.command.api.ServerCommand;
import com.example.server.component.CollectionHolder;
import com.example.common.dto.ServerCommandProcessingResult;
import com.example.common.dto.KeyAndStudyGroup;

public class Add implements ServerCommand {

    private final CollectionHolder collectionHolder;

    public Add(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public ServerCommandProcessingResult execute(Object commandArg) {
        KeyAndStudyGroup keyAndStudyGroup = (KeyAndStudyGroup) commandArg;
        ServerCommandProcessingResult serverCommandProcessingResult;
        if (collectionHolder.containsKey(keyAndStudyGroup.key)) {
            serverCommandProcessingResult = new ServerCommandProcessingResult (
                    collectionHolder, false, "Cannot perform operation. Such element key already exists"
            );
        } else {
            collectionHolder.add(keyAndStudyGroup.key, keyAndStudyGroup.studyGroup);
            collectionHolder.fillWithIds();
            serverCommandProcessingResult = new ServerCommandProcessingResult (
                    collectionHolder, true, "Added element to collection"
            );
        }
        return serverCommandProcessingResult;
    }
}
