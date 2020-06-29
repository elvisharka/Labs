package com.example.command.impl;

import com.example.CollectionHolder;
import com.example.StudyGroupValidator;
import com.example.Util;
import com.example.command.api.Command;
import com.example.model.StudyGroup;

/**
 * Adds specified in command arg element to collection.
 */
public class AddInOneLine implements Command {

    private CollectionHolder collectionHolder;
    private StudyGroupValidator studyGroupValidator;

    public AddInOneLine(CollectionHolder collectionHolder, StudyGroupValidator studyGroupValidator) {
        this.collectionHolder = collectionHolder;
        this.studyGroupValidator = studyGroupValidator;
    }

    @Override
    public String description() {
        return "Adds new element to collection. Element value is in one console line";
    }

    @Override
    public String execute(String commandArg) {
        String commandResult;
        if (commandArg != null && commandArg.contains(",")) {
            try {
                String[] commandArgs = commandArg.split(",");
                String key = toStudyGroupKey(commandArgs);
                if (collectionHolder.containsKey(key)) {
                    commandResult = "Cannot add the element. There is already such key";
                } else {
                    StudyGroup studyGroup = toStudyGroupValue(commandArgs);
                    if (studyGroupValidator.valid(studyGroup)) {
                        collectionHolder.add(key, studyGroup);
                        commandResult = "Element added successfully";
                    } else {
                        commandResult = "Cannot add the element. Some values specified for the element are invalid.";
                    }
                }
            } catch (RuntimeException exception) {
                commandResult = "Cannot add the element. Invalid values for element (example: some_key,First_group,12,45,2018-09-16T08:00:00+00:00[Europe/London],33,45,78.55,THIRD,Petrov,2007-12-03T10:15:30,177,17046RU,RUSSIA)";
            }
        } else {
            commandResult = "Cannot add element: no element specified or invalid values for element";
        }
        return commandResult;
    }

    private StudyGroup toStudyGroupValue(String[] commandArgs) {
        return Util.toStudyGroup(commandArgs);
    }

    private String toStudyGroupKey(String[] commandArgs) {
        return commandArgs[0].trim();
    }

}
