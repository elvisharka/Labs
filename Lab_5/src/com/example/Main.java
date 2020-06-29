package com.example;

import com.example.command.CommandContainer;
import com.example.command.api.Command;
import com.example.command.impl.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class.
 */
public class Main {

    public static void main(String[] args) {
        if (args != null && args.length == 1 && args[0] != null && !"".equals(args[0])) {
            executeWithSafeArgs(args);
        } else {
            System.out.println(Constants.LOG_PREFIX_ERROR
                    + ": there is not exactly 1 program argument (name of file expected)");
        }

    }

    private static void executeWithSafeArgs(String[] args) {
        String fileName = args[0];
        System.out.println("File name from args: " + fileName);
        try {
            execute(fileName);
        } catch (IOException exception) {
            System.out.println("Cannot find the file: " + exception.getMessage());
        }
    }

    private static void execute(String fileName) throws IOException {
        CollectionHolder collectionHolder = new CollectionHolder(
                LocalDateTime.now(), Util.readAllFromFile(new File(fileName))
        );
        collectionHolder.fillWithIds();
        System.out.println("There is file content:");
        collectionHolder.getStudyGroupMap().forEach(((s, studyGroup) -> System.out.println(s + " => " + studyGroup)));
        CommandContainer commandContainer = initContainer(collectionHolder);
        System.out.println("Choose name of command to execute:");
        commandContainer.getCommands().forEach(
                (commandName, command) -> System.out.println(commandName + " => " + command.description())
        );
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            execute(Util.toCommandNameAndArg(scanner.nextLine()), commandContainer);
        }
    }

    private static void execute(CommandNameAndArg commandNameAndArg, CommandContainer commandContainer) {
        System.out.println("Command start");
        if (commandNameAndArg.commandArg == null || "".equals(commandNameAndArg.commandArg)) {
            executeCommandWithNoArg(commandNameAndArg.commandName, commandContainer);
        } else {
            executeCommandWithArg(commandNameAndArg, commandContainer);
        }
        System.out.println("Command finish");
    }

    private static void executeCommandWithArg(CommandNameAndArg commandNameAndArg, CommandContainer commandContainer) {
        Command command = commandContainer.findCommandByName(commandNameAndArg.commandName);
        System.out.println(command.execute(commandNameAndArg.commandArg));
    }

    private static void executeCommandWithNoArg(String commandName, CommandContainer commandContainer) {
        Command command = commandContainer.findCommandByName(commandName);
        System.out.println(command.execute(null));
    }

    private static CommandContainer initContainer(CollectionHolder collectionHolder) {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("show", new Show(collectionHolder));
        commandMap.put("add_in_one_line", new AddInOneLine(collectionHolder, new StudyGroupValidator()));
        commandMap.put("add", new Add(collectionHolder, new StudyGroupValidator()));
        commandMap.put("remove", new Remove(collectionHolder));
        commandMap.put("help", new Help(commandMap));
        commandMap.put("no_command", new NoCommand());
        return new CommandContainer(commandMap);
    }
}
