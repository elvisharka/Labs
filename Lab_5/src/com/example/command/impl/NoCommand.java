package com.example.command.impl;

import com.example.command.api.Command;

/**
 * Represents no command.
 */
public class NoCommand implements Command {
    @Override
    public String description() {
        return "no command";
    }

    @Override
    public String execute(String commandArg) {
        return "no command";
    }
}
