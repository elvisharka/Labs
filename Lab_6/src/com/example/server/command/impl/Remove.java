package com.example.server.command.impl;

import com.example.common.dto.ServerCommandProcessingResult;
import com.example.common.model.StudyGroup;
import com.example.server.command.api.ServerCommand;
import com.example.server.component.CollectionHolder;

public class Remove implements ServerCommand {

    private final CollectionHolder collectionHolder;

    public Remove(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public ServerCommandProcessingResult execute(Object commandArg) {
        String studyGroupKey = (String) commandArg;
        StudyGroup studyGroup = collectionHolder.remove(studyGroupKey);
        return studyGroup == null ? new ServerCommandProcessingResult (
                collectionHolder, false, "There is no such key provided in collection"
        ) : new ServerCommandProcessingResult (
                collectionHolder, true, "Element removed successfully"
        );
    }
}
