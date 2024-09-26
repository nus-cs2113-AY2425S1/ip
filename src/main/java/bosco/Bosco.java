package bosco;

import bosco.command.ExitCommand;
import bosco.ui.Ui;
import bosco.parser.Parser;

import bosco.command.Command;

import bosco.task.TaskList;
import bosco.task.Task;
import bosco.task.Todo;
import bosco.task.Deadline;
import bosco.task.Event;

import bosco.exception.IllegalCommandException;
import bosco.exception.EmptyDescriptionException;
import bosco.exception.MissingPrefixException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Bosco {
    private static final String FILE_PATH = "./data/bosco.txt";

    private static Ui ui;
    private static TaskList tasks;
    private static Parser parser;

    public static void main(String[] args) {
        ui = new Ui();
        tasks = new TaskList();
        parser = new Parser();

        try {
            loadFileContents(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInputString = ui.getUserInput();
                Command command = parser.parseCommand(userInputString);
                command.setData(tasks, ui);
                command.execute();
                isExit = ExitCommand.isExit(command);
            } catch (IllegalCommandException e) {
                ui.printMessages("Error: invalid command. Please try again!");
            } catch (NumberFormatException e) {
                ui.printMessages("Error: invalid index input. Please provide a number!");
            } catch (IndexOutOfBoundsException e) {
                ui.printMessages("Error: input out of bounds. List has " + tasks.getSize() + " tasks.");
            } catch (EmptyDescriptionException e) {
                ui.printMessages("Error: task description is empty. Please provide a description!");
            } catch (MissingPrefixException e) {
                ui.printMessages("Error: missing " + e.missingPrefix + " prefix.");
            }

            try {
                writeToFile(FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        executeExitProgram();
    }

    private static void loadFileContents(String filePath) throws IOException {
        Path inputPath = createFilePathIfNotExists(filePath);
        Scanner fileScanner = new Scanner(inputPath);
        while (fileScanner.hasNext()) {
            addTaskFromFileLine(fileScanner.nextLine());
        }
    }

    private static Path createFilePathIfNotExists(String filePath) {
        Path p = Paths.get(filePath);
        try {
            Files.createDirectories(p.getParent());
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

    private static void addTaskFromFileLine(String fileLine) {
        String[] stringParts = fileLine.split(" \\| ");
        boolean isDone = (stringParts[1].equals("X"));
        String taskType = stringParts[0];
        String description = stringParts[2];
        switch(taskType) {
        case "T":
            tasks.addTask(new Todo(description, isDone));
            break;
        case "D":
            tasks.addTask(new Deadline(description, isDone, stringParts[3]));
            break;
        case "E":
            tasks.addTask(new Event(description, isDone, stringParts[3], stringParts[4]));
            break;
        }
    }

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks.getAllTasks()) {
            fw.write(getFileInputForTask(task) + System.lineSeparator());
        }
        fw.close();
    }

    private static String getFileInputForTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Deadline)task).getBy();
        } else {
            return "E | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Event)task).getFrom() + " | " + ((Event)task).getTo();
        }
    }

    private static void executeExitProgram() {
        ui.printExitMessage();
        System.exit(0);
    }
}
