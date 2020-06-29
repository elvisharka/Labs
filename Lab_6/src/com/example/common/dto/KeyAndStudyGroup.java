package com.example.common.dto;

import com.example.common.model.StudyGroup;

import java.io.Serializable;

public class KeyAndStudyGroup implements Serializable {
    private static final long serialVersionUID = -433469611193262219L;
    public String key;
    public StudyGroup studyGroup;

    public KeyAndStudyGroup(String key, StudyGroup studyGroup) {
        this.key = key;
        this.studyGroup = studyGroup;
    }
}
