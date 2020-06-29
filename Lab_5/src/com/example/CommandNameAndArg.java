package com.example;

/**
 * Represents command name and arg.
 */
public class CommandNameAndArg {
    public String commandName;
    public String commandArg;

    public CommandNameAndArg(String commandName) {
        this.commandName = commandName;
    }

    public CommandNameAndArg(String commandName, String commandArg) {
        this.commandName = commandName;
        this.commandArg = commandArg;
    }
}
