package com.example;

import com.example.common.Constants;
import com.example.common.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;

/**
 * Util class.
 */
public class Util {

//    public static HashMap<String, StudyGroup> readAllFromFile(File file) throws IOException {
//        HashMap<String, StudyGroup> resultCollection = new HashMap<>();
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(Constants.COMMA_DELIMITER);
//                resultCollection.put(values[0], toStudyGroupFromFile(values));
//            }
//        }
//        return resultCollection;
//    }

//    public static StudyGroup toStudyGroup(String[] values) {
//        return new StudyGroup(
//                values[1].trim(),
//                new Coordinates(
//                        Long.parseLong(values[2].trim()), Long.parseLong(values[3].trim())
//                ),
//                ZonedDateTime.now(),
//                Long.parseLong(values[4].trim()),
//                Integer.parseInt(values[5].trim()),
//                Double.parseDouble(values[6].trim()),
//                Semester.valueOf(values[7].trim()),
//                new Person(
//                        values[8].trim(),
//                        LocalDateTime.parse(values[9].trim()),
//                        Long.parseLong(values[10].trim()),
//                        values[11].trim(),
//                        Country.valueOf(values[12].trim())
//                )
//        );
//    }

//    public static StudyGroup toStudyGroupFromFile(String[] values) {
//        return new StudyGroup(
//                values[1].trim(),
//                new Coordinates(
//                        Long.parseLong(values[2].trim()), Long.parseLong(values[3].trim())
//                ),
//                ZonedDateTime.parse(values[4].trim()),
//                Long.parseLong(values[5].trim()),
//                Integer.parseInt(values[6].trim()),
//                Double.parseDouble(values[7].trim()),
//                Semester.valueOf(values[8].trim()),
//                new Person(
//                        values[9].trim(),
//                        LocalDateTime.parse(values[10].trim()),
//                        Long.parseLong(values[11].trim()),
//                        values[12].trim(),
//                        Country.valueOf(values[13].trim())
//                )
//        );
//    }

//    public static ServerCommandNameAndArg toCommandNameAndArg(String line) {
//        if (line.contains(" ")) {
//            return new ServerCommandNameAndArg(
//                    line.substring(0, line.indexOf(" ")).trim(),
//                    line.substring(line.indexOf(" ")).trim()
//            );
//        } else {
//            return new ServerCommandNameAndArg(line.trim());
//        }
//    }

    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }
}
