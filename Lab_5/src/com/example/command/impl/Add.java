package com.example.command.impl;

import com.example.CollectionHolder;
import com.example.StudyGroupValidator;
import com.example.command.api.Command;
import com.example.model.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Adds specified element to collection.
 */
public class Add implements Command {

    private CollectionHolder collectionHolder;
    private StudyGroupValidator studyGroupValidator;

    public Add(CollectionHolder collectionHolder, StudyGroupValidator studyGroupValidator) {
        this.collectionHolder = collectionHolder;
        this.studyGroupValidator = studyGroupValidator;
    }

    @Override
    public String description() {
        return "Adds new element to collection.";
    }

    @Override
    public String execute(String commandArg) {
        String commandResult = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key of new study group:");
        String key = scanner.nextLine();
        System.out.println("Enter the name of new study group:");
        String groupName = scanner.nextLine();
        System.out.println("Enter coordinate x:");
        long x = getLong(scanner);
        System.out.println("Enter coordinate y:");
        long y = getLong(scanner);
        System.out.println("Enter students count:");
        long studentsCount = getLong(scanner);
        System.out.println("Enter should be expelled:");
        int shouldBeExpelled = getInt(scanner);
        System.out.println("Enter average mark:");
        double averageMark = getDouble(scanner);
        Semester semester = getSemester(scanner);
        System.out.println("Enter admin name:");
        String adminName = scanner.nextLine();
        System.out.println("Enter admin birthday:");
        LocalDateTime localDateTime = getLocalDateTime(scanner);
        System.out.println("Enter admin height:");
        long adminHeight = getLong(scanner);
        System.out.println("Enter admin passport id:");
        String passportId = scanner.nextLine();
        Country country = getCountry(scanner);
        Coordinates coordinates = new Coordinates(x, y);
        StudyGroup studyGroup = new StudyGroup(
                groupName,
                coordinates,
                ZonedDateTime.now(),
                studentsCount,
                shouldBeExpelled,
                averageMark,
                semester,
                new Person(adminName, localDateTime, adminHeight, passportId, country)
        );
        if (studyGroupValidator.valid(studyGroup)) {
            if (collectionHolder.containsKey(key)) {
                commandResult = "Such key already exists";
            } else {
                collectionHolder.add(key, studyGroup);
                collectionHolder.fillWithIds();
            }
        } else {
            commandResult = "Study group is invalid";
        }
        return commandResult;
    }

    private Country getCountry(Scanner scanner) {
        System.out.println("Enter admin nationality (one of the following values):");
        Arrays.stream(Country.values()).forEach(System.out::println);
        try {
            return Country.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            return getCountry(scanner);
        }
    }

    private Semester getSemester(Scanner scanner) {
        System.out.println("Enter semester (one of the following values):");
        Arrays.stream(Semester.values())
                .forEach(System.out::println);
        try {
            return Semester.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            return getSemester(scanner);
        }
    }

    private LocalDateTime getLocalDateTime(Scanner scanner) {
        System.out.println("Enter date time value (e.g. 2007-12-03T10:15:30):");
        String dateTimeValue = scanner.nextLine();
        try {
            return LocalDateTime.parse(dateTimeValue);
        } catch (DateTimeParseException exception) {
            return getLocalDateTime(scanner);
        }
    }

    private double getDouble(Scanner scanner) {
        System.out.println("Enter double value (e.g. 17.17):");
        String doubleValue = scanner.nextLine();
        try {
            return Double.parseDouble(doubleValue);
        } catch (NumberFormatException exception) {
            return getDouble(scanner);
        }
    }

    private int getInt(Scanner scanner) {
        System.out.println("Enter int value (e.g. 17):");
        String intValue = scanner.nextLine();
        try {
            return Integer.parseInt(intValue);
        } catch (NumberFormatException exception) {
            return getInt(scanner);
        }
    }

    private long getLong(Scanner scanner) {
        System.out.println("Enter long value (e.g. 17):");
        String longValue = scanner.nextLine();
        try {
            return Long.parseLong(longValue);
        } catch (NumberFormatException exception) {
            return getLong(scanner);
        }
    }
}
