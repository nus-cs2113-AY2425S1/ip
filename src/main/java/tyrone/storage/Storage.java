package tyrone.storage;

import tyrone.command.exceptions.EmptyFieldException;
import tyrone.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to handle reading data from and writing data to save file
 */
public class Storage {

    //Constants used by methods to parse inputs
    public static final int START_INDEX_OFFSET_DESCRIPTION = 1;
    public static final int START_INDEX_OFFSET_START = 6;
    public static final int START_INDEX_OFFSET_END = 4;
    public static final int START_INDEX_OFFSET_DEADLINE = 4;
    public static final String PARSE_LINE_ERROR_MESSAGE = "Error parsing line. Skipping entry.";
    public static final int INPUT_START_INDEX = 2;

    //Constants to store save file name and directory
    private static final String SAVE_FILE_NAME = "./data/Tyrone.txt";
    private static final String SAVE_FILE_DIR = "./data";

    /**
     * Parses a single line of text in chatbot save file to add corresponding Task to task list.
     *
     * @param line Line of text in chatbot save file representing a previously saved task.
     */
    private void parseLine(String line, TaskList taskList) {
        boolean isDone = (line.charAt(0) == '1');
        String input = line.substring(INPUT_START_INDEX);
        String command = input.substring(0, input.indexOf(" "));
        switch (command) {
        case "todo":
            parseTodo(input, isDone, taskList);
            break;
        case "deadline":
            parseDeadline(input, isDone, taskList);
            break;
        case "event":
            parseEvent(input, isDone, taskList);
            break;
        }
    }

    /**
     * Add an Event Task to task list according to details in save file.
     *
     * @param input Line of text from chatbot save file containing task description, start and end.
     * @param isDone Whether task has been marked as done or not.
     */
    private void parseEvent(String input, boolean isDone, TaskList taskList) {
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
            taskList.addTask(newEvent);
        } catch (EmptyFieldException | StringIndexOutOfBoundsException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    /**
     * Add a Deadline Task to task list according to details in save file.
     *
     * @param input Line of text from chatbot save file containing task description and deadline.
     * @param isDone Whether task has been marked as done or not.
     */
    private void parseDeadline(String input, boolean isDone, TaskList taskList) {
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
            taskList.addTask(newDeadline);
        } catch (EmptyFieldException | StringIndexOutOfBoundsException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    /**
     * Add a Todo Task to task list according to details in save file.
     *
     * @param input Line of text from chatbot save file containing task description.
     * @param isDone Whether task has been marked as done or not.
     */
    private void parseTodo(String input, boolean isDone, TaskList taskList) {
        try {
            String description = input.substring(input.indexOf(" ") + START_INDEX_OFFSET_DESCRIPTION);
            if (!input.contains(" ") || description.isBlank()) {
                throw new EmptyFieldException();
            }
            Todo newTodo = new Todo(description);
            if (isDone) {
                newTodo.markAsDone();
            }
            taskList.addTask(newTodo);
        } catch (EmptyFieldException e) {
            System.out.println(PARSE_LINE_ERROR_MESSAGE);
        }
    }

    /**
     * Populates task list according to records saved in chatbot save file.
     */
    public void initTaskListFromSaveFile(TaskList taskList) {
        try {
            File saveFile = new File(SAVE_FILE_NAME);
            Scanner s = new Scanner(saveFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                parseLine(line, taskList);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Creates a new .txt save file in a new directory named 'data' in current directory.
     * If 'data' directory or save file already exists, does not overwrite existing file/directory.
     */
    public void createSaveFile() {
        File dir = new File(SAVE_FILE_DIR);
        dir.mkdir();
        try {
            File f = new File(SAVE_FILE_NAME);
            f.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + SAVE_FILE_NAME);
        }
    }

    /**
     * Writes a String of text to a file.
     *
     * @param fileName Name of file to write to.
     * @param textToAdd String of text to write to file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    private void writeToFile(String fileName, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates chatbot save file with records of all tasks in task list.
     * Prints error message upon encountering IOException.
     */
    public void updateSaveFile(TaskList taskList) {
        try {
            writeToFile(SAVE_FILE_NAME, taskList.getAllTaskSaveRecords());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
