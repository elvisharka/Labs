package com.example;

import com.example.model.StudyGroup;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Holds collection.
 */
public class CollectionHolder {

    private LocalDateTime initDateTime;
    private HashMap<String, StudyGroup> studyGroupMap;

    public CollectionHolder(LocalDateTime initDateTime, HashMap<String, StudyGroup> studyGroupMap) {
        this.initDateTime = initDateTime;
        this.studyGroupMap = studyGroupMap;
    }

    public LocalDateTime getInitDateTime() {
        return initDateTime;
    }

    public void setInitDateTime(LocalDateTime initDateTime) {
        this.initDateTime = initDateTime;
    }

    public Map<String, StudyGroup> getStudyGroupMap() {
        return studyGroupMap;
    }

    public void fillWithIds() {
        studyGroupMap.values()
                .stream()
                .filter(value -> value.getId() == 0)
                .forEach(value -> value.setId(generateRandomId()));
    }

    private int generateRandomId() {
        Random random = new Random();
        int generatedInt = random.nextInt();
        if (studyGroupMap.values()
                .stream()
                .anyMatch(value -> value.getId() == generatedInt)) {
            return generateRandomId();
        }
        return generatedInt;
    }

    public void add(String key, StudyGroup value) {
        studyGroupMap.put(key, value);
    }

    public StudyGroup remove(String key) {
        return studyGroupMap.remove(key);
    }

    public boolean isEmpty() {
        return studyGroupMap.isEmpty();
    }

    public boolean containsKey(String key) {
        return studyGroupMap.containsKey(key);
    }
}
