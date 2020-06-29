package com.example;

import com.example.model.Coordinates;
import com.example.model.Person;
import com.example.model.StudyGroup;

/**
 * Validates instance of StudyGroup.
 */
public class StudyGroupValidator {
    public boolean valid(StudyGroup studyGroup) {
        String name = studyGroup.getName();
        Coordinates coordinates = studyGroup.getCoordinates();
        Person person = studyGroup.getGroupAdmin();
        return  !Util.isEmpty(name)
                && coordinates != null
                && coordinates.getX() <= 278
                && coordinates.getY() <= 500
                && studyGroup.getCreationDate() != null
                && studyGroup.getStudentsCount() != null
                && studyGroup.getStudentsCount() > 0
                && studyGroup.getShouldBeExpelled() != null
                && studyGroup.getShouldBeExpelled() > 0
                && studyGroup.getAverageMark() != null
                && studyGroup.getAverageMark() > 0
                && studyGroup.getSemesterEnum() != null
                && person != null
                && !Util.isEmpty(person.getName())
                && person.getBirthday() != null
                && person.getHeight() != null
                && person.getHeight() > 0
                && person.getPassportID() != null
                && person.getNationality() != null;
    }
}
