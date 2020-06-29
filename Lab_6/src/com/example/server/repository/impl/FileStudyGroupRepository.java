package com.example.server.repository.impl;

import com.example.common.Constants;
import com.example.common.model.*;
import com.example.server.component.CollectionHolder;
import com.example.server.exception.RepositoryException;
import com.example.server.repository.api.StudyGroupRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileStudyGroupRepository implements StudyGroupRepository {

    private final String fileName;

    public FileStudyGroupRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, StudyGroup> findAll() throws RepositoryException {
        HashMap<String, StudyGroup> resultCollection = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(Constants.COMMA_DELIMITER);
                resultCollection.put(values[0], toStudyGroupFromFile(values));
            }
        } catch (IOException exception) {
            throw new RepositoryException();
        }
        return resultCollection;
    }

    @Override
    public void saveAll(CollectionHolder collectionHolder) {
        String csvContent = convertToCSV(collectionHolder.getStudyGroupMap());
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(csvContent);
            writer.close();
        } catch (IOException exception) {
            throw new RepositoryException();
        }
    }

    public String convertToCSV(Map<String, StudyGroup> map) {
        return map.entrySet()
                .stream()
                .map(
                        entry -> String.join (
                                ",",
                                entry.getKey(),
                                String.valueOf(entry.getValue().getId()),
                                entry.getValue().getName(),
                                String.valueOf(entry.getValue().getCoordinates().getX()),
                                String.valueOf(entry.getValue().getCoordinates().getY()),
                                entry.getValue().getCreationDate().toString(),
                                String.valueOf(entry.getValue().getStudentsCount()),
                                String.valueOf(entry.getValue().getShouldBeExpelled()),
                                String.valueOf(entry.getValue().getAverageMark()),
                                String.valueOf(entry.getValue().getSemesterEnum()),
                                String.valueOf(entry.getValue().getGroupAdmin().getName()),
                                String.valueOf(entry.getValue().getGroupAdmin().getBirthday()),
                                String.valueOf(entry.getValue().getGroupAdmin().getHeight()),
                                String.valueOf(entry.getValue().getGroupAdmin().getPassportID()),
                                String.valueOf(entry.getValue().getGroupAdmin().getNationality())
                        )
                ).collect(Collectors.joining(System.lineSeparator()));
    }

    public static StudyGroup toStudyGroupFromFile(String[] values) {
        return new StudyGroup(
                Integer.parseInt(values[1].trim()),
                values[2],
                new Coordinates(
                        Long.parseLong(values[3].trim()), Long.parseLong(values[4].trim())
                ),
                ZonedDateTime.parse(values[5].trim()),
                Long.parseLong(values[6].trim()),
                Integer.parseInt(values[7].trim()),
                Double.parseDouble(values[8].trim()),
                Semester.valueOf(values[9].trim()),
                new Person(
                        values[10].trim(),
                        LocalDateTime.parse(values[11].trim()),
                        Long.parseLong(values[12].trim()),
                        values[13].trim(),
                        Country.valueOf(values[14].trim())
                )
        );
    }
}
