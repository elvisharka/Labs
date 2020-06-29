package com.example.client.command.impl;

import com.example.common.Constants;
import com.example.client.StudyGroupValidator;
import com.example.client.command.api.ClientCommand;
import com.example.client.exception.ClientCommandExecutionInvalidValuesException;
import com.example.common.model.*;
import com.example.common.dto.KeyAndStudyGroup;
import com.example.common.dto.ServerCommandNameAndArg;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Add implements ClientCommand {
    private final StudyGroupValidator studyGroupValidator;

    public Add(StudyGroupValidator studyGroupValidator) {
        this.studyGroupValidator = studyGroupValidator;
    }

    @Override
    public String description() {
        return "Adds element to collection";
    }

    @Override
    public ServerCommandNameAndArg execute() throws ClientCommandExecutionInvalidValuesException {
        ServerCommandNameAndArg serverCommandNameAndArg = new ServerCommandNameAndArg();
        serverCommandNameAndArg.commandName = Constants.SERVER_COMMAND_NAME_ADD;
        serverCommandNameAndArg.commandArg = commandArg().orElseThrow(ClientCommandExecutionInvalidValuesException::new);
        return serverCommandNameAndArg;
    }

    public Optional<KeyAndStudyGroup> commandArg() {
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
        return Optional.ofNullable(
                studyGroupValidator.valid(studyGroup) ? new KeyAndStudyGroup(key, studyGroup) : null
        );
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
