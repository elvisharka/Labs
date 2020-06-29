package com.example.common.dto;

import com.example.server.component.CollectionHolder;

import java.io.Serializable;

public class ServerCommandProcessingResult implements Serializable {
    private static final long serialVersionUID = -1377226591872968089L;
    public CollectionHolder currentCollectionHolder;
    public boolean isResultOk;
    public String description;

    public ServerCommandProcessingResult(boolean isResultOk, String description) {
        this.isResultOk = isResultOk;
        this.description = description;
    }

    public ServerCommandProcessingResult(CollectionHolder currentCollectionHolder, boolean isResultOk, String description) {
        this.currentCollectionHolder = currentCollectionHolder;
        this.isResultOk = isResultOk;
        this.description = description;
    }
}
