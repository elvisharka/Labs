package com.example.command.api;

/**
 * Provides interface for Command pattern.
 */
public interface Command {
    String description();
    String execute(String commandArg);
}
