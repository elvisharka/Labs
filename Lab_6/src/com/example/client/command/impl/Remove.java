package com.example.client.command.impl;

import com.example.client.command.api.ClientCommand;
import com.example.common.Constants;
import com.example.common.dto.ServerCommandNameAndArg;

import java.util.Scanner;

public class Remove implements ClientCommand {
    @Override
    public String description() {
        return "Removes element from collection";
    }

    @Override
    public ServerCommandNameAndArg execute() {
        ServerCommandNameAndArg serverCommandNameAndArg = new ServerCommandNameAndArg();
        serverCommandNameAndArg.commandName = Constants.SERVER_COMMAND_NAME_REMOVE;
        serverCommandNameAndArg.commandArg = commandArg();
        return serverCommandNameAndArg;
    }

    private String commandArg() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter study group key to remove");
        return scanner.nextLine();
    }
}
