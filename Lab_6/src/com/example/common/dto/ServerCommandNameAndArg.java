package com.example.common.dto;

import java.io.Serializable;

public class ServerCommandNameAndArg implements Serializable {
    private static final long serialVersionUID = 5617350538552006939L;
    public String commandName;
    public Object commandArg;

    public ServerCommandNameAndArg() { }

    public ServerCommandNameAndArg(String commandName, Object commandArg) {
        this.commandName = commandName;
        this.commandArg = commandArg;
    }
}
