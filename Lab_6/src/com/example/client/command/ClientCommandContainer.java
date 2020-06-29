package com.example.client.command;

import com.example.client.command.api.ClientCommand;
import com.example.client.command.impl.NoCommand;

import java.util.Map;

public class ClientCommandContainer {
    private final Map<String, ClientCommand> clientCommandMap;

    public ClientCommandContainer(Map<String, ClientCommand> clientCommandMap) {
        this.clientCommandMap = clientCommandMap;
    }

    public Map<String, ClientCommand> getClientCommandMap() {
        return clientCommandMap;
    }

    public ClientCommand findCommandByName(String commandName) {
        return clientCommandMap.getOrDefault(commandName, new NoCommand());
    }
}
