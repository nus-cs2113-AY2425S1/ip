package tyrone.savemanager;

import tyrone.command.exceptions.EmptyFieldException;
import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static final int START_INDEX_OFFSET_DESCRIPTION = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;

    private static final String SAVE_FILE_NAME = "./data/Tyrone.txt";
    private static final String SAVE_FILE_DIR = "./data";

    public static final int INPUT_START_INDEX = 2;

    public static final String PARSE_LINE_ERROR_MESSAGE = "Error parsing line. Skipping entry.";

    private static void parseLine(String line) {
        boolean isDone = (line.charAt(0) == '1');
        String input = line.substring(INPUT_START_INDEX);
        String command = input.substring(0, input.indexOf(" "));
        switch (command) {
        case "todo":
            parseTodo(input, isDone);
            break;
        case "deadline":
            parseDeadline(input, isDone);
            break;
        case "event":
            parseEvent(input, isDone);
            break;
        }
    }

    private static void parseEvent(String input, boolean isDone) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION,
                    input.indexOf(" /from"));
            String start = input.substring(input.indexOf("/from") + START_INDEX_OFFSET_START,
                    input.indexOf(" /to"));
            String end = input.substring(input.indexOf("/to") + START_INDEX_OFFSET_END);
            if (description.isBlank() || start.isBlank() || end.isBlank()) {
                throw new EmptyFieldException();
            }
            Event newEvent = new Event(description, start, end);
            if (isDone) {
                newEvent.markAsDone();
            }
            TaskList.addTask(newEvent);
        } catch (EmptyFieldException | StringIndexOutOfBoundsException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    private static void parseDeadline(String input, boolean isDone) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION,
                    input.indexOf(" /by"));
            String deadline = input.substring(input.indexOf("/by") + START_INDEX_OFFSET_DEADLINE);
            if (description.isBlank() || deadline.isBlank()) {
                throw new EmptyFieldException();
            }
            Deadline newDeadline = new Deadline(description, deadline);
            if (isDone) {
                newDeadline.markAsDone();
            }
            TaskList.addTask(newDeadline);
        } catch (EmptyFieldException | StringIndexOutOfBoundsException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    private static void parseTodo(String input, boolean isDone) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION);
            if (!input.contains(" ") || description.isBlank()) {
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            if (isDone) {
                newTodo.markAsDone();
            }
            TaskList.addTask(newTodo);
        } catch (EmptyFieldException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    public static void initTaskListFromSaveFile() {
        try {
            File saveFile = new File(SAVE_FILE_NAME);
            Scanner s = new Scanner(saveFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                parseLine(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void createSaveFile() {
        File dir = new File(SAVE_FILE_DIR);
        dir.mkdir();
        try {
            File f = new File(SAVE_FILE_NAME);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + SAVE_FILE_NAME);
        }
    }

    private static void writeToFile(String fileName, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(textToAdd);
        fw.close();
    }

    public static void updateSaveFile() {
        try {
            writeToFile(SAVE_FILE_NAME, TaskList.getAllTaskDetails());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
