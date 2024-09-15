package tyrone.savemanager;

import tyrone.task.Deadline;
import tyrone.task.Event;
import tyrone.task.TaskList;
import tyrone.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReadWriter {

    private static String SAVE_FILE_NAME = "./data/Tyrone.txt";
    private static String SAVE_FILE_DIR = "./data";

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

    private static void parseLine(String line) {
        switch (line.charAt(1)) {
        case 'T':
            parseTodo(line);
            break;
        case 'D':
            parseDeadline(line);
            break;
        case 'E':
            parseEvent(line);
            break;
        }
    }

    private static void parseTodo(String line) {
        String description = line.substring(line.indexOf("] ") + 2);
        Todo newTodo = new Todo(description);
        if (line.charAt(4) == 'X') {
            newTodo.markAsDone();
        }
        TaskList.addTask(newTodo);
    }

    private static void parseDeadline(String line) {
        String description = line.substring(line.indexOf("] ") + 2, line.indexOf(" (by:"));
        String deadline = line.substring(line.indexOf(" (by:") + 6, line.indexOf(")"));
        Deadline newDeadline = new Deadline(description, deadline);
        if (line.charAt(4) == 'X') {
            newDeadline.markAsDone();
        }
        TaskList.addTask(newDeadline);
    }

    private static void parseEvent(String line) {
        String description = line.substring(line.indexOf("] ") + 2, line.indexOf(" (from:"));
        String start = line.substring(line.indexOf(" (from:") + 8, line.indexOf(" to: "));
        String end = line.substring(line.indexOf(" to:") + 5, line.indexOf(")"));
        Event newEvent = new Event(description, start, end);
        if (line.charAt(4) == 'X') {
            newEvent.markAsDone();
        }
        TaskList.addTask(newEvent);
    }
}
