package com.example.command.impl;

import com.example.CollectionHolder;
import com.example.command.api.Command;

import java.util.stream.Collectors;

/**
 * Shows all elements.
 */
public class Show implements Command {

    private CollectionHolder collectionHolder;

    public Show(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String description() {
        return "shows all elements in collection";
    }

    @Override
    public String execute(String commandArg) {
        return collectionHolder.isEmpty() ? "There are no elements in collection" : collectionHolder.getStudyGroupMap()
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + " => " + entry.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
