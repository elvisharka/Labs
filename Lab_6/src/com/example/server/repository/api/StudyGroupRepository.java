package com.example.server.repository.api;

import com.example.common.model.StudyGroup;
import com.example.server.component.CollectionHolder;
import com.example.server.exception.RepositoryException;

import java.util.Map;

public interface StudyGroupRepository {
    Map<String, StudyGroup> findAll() throws RepositoryException;

    void saveAll(CollectionHolder collectionHolder);
}
