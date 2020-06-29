package com.example.server.component;

import com.example.common.model.StudyGroup;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Holds collection.
 */
public class CollectionHolder implements Serializable {

    private static final long serialVersionUID = 1617192873347696281L;

    private static final int MIN_RANDOM_ID = 1;
    private static final int MAX_RANDOM_ID = 10_000_000;

    private LocalDateTime initDateTime;
    private final Map<String, StudyGroup> studyGroupMap;

    public CollectionHolder(LocalDateTime initDateTime, Map<String, StudyGroup> studyGroupMap) {
        this.initDateTime = initDateTime;
        this.studyGroupMap = studyGroupMap;
    }

    public Map<String, StudyGroup> getStudyGroupMap() {
        return studyGroupMap;
    }

    public void fillWithIds() {
        studyGroupMap.values()
                .stream()
                .filter(value -> value.getId() == 0)
                .forEach(value -> value.setId(generateRandomId(MIN_RANDOM_ID, MAX_RANDOM_ID)));
    }

    private int generateRandomId(int minRandomId, int maxRandomId) {
        int generatedInt = ThreadLocalRandom.current().nextInt(MIN_RANDOM_ID, MAX_RANDOM_ID + 1);
        if (studyGroupMap.values()
                .stream()
                .anyMatch(value -> value.getId() == generatedInt)) {
            return generateRandomId(minRandomId, maxRandomId);
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
