package com.example.command.impl;

import com.example.CollectionHolder;
import com.example.command.api.Command;
import com.example.model.StudyGroup;

/**
 * Removes specified element by key from collection.
 */
public class Remove implements Command {

    private CollectionHolder collectionHolder;

    public Remove(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String description() {
        return "removes element by key";
    }

    @Override
    public String execute(String commandArg) {
        String commandResult;
        if (commandArg == null || "".equals(commandArg)) {
            commandResult = "Cannot remove element: no key specified";
        } else {
            StudyGroup oldStudyGroup = collectionHolder.remove(commandArg);
            if (oldStudyGroup == null) {
                commandResult = "Cannot remove element: no such key";
            } else {
                commandResult = "Element removed successfully";
            }
        }
        return commandResult;
    }
}
