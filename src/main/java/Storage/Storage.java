package Storage;

import Parser.Parser;
import TaskList.*;
import commands.Deadline;
import commands.Event;
import commands.Todo;
import constants.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage{

    private final Parser parser;
    private final TaskList taskList;

    public Storage() {
        this.parser = new Parser();
        this.taskList = new TaskList();
    }

    /**
     * Print previously stored tasks from the txt file.
     * Create new txt file if file is not found at the file path.
     */
    public void loadFileData() {
        try {
            loadExistingData();
            printFileContents();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    private void loadExistingData() {
        try {
            File file = new File(Utils.FILE_PATH);
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                String storedTask = s.nextLine();
                String[] storedTaskSubstrings = storedTask.split("\\| ");
                String command = storedTaskSubstrings[0];

                switch (command) {
                    case "T":
                       loadExistingTodo(storedTaskSubstrings);
                        break;
                    case "D":
                        loadExistingDeadline(storedTaskSubstrings);
                        break;
                    case "E":
                        loadExistingEvent(storedTaskSubstrings);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            createFile();
        }
    }


    public void loadExistingTodo(String[] storedTaskSubstrings){
        String description = storedTaskSubstrings[2].trim();
        Todo todo = new Todo(description);
        parser.checkComplete(storedTaskSubstrings, todo);

        taskList.addTask(todo);
    }

    private void loadExistingDeadline(String[] storedTaskSubstrings){
        String description = storedTaskSubstrings[2].trim();
        String by = storedTaskSubstrings[3].trim();
        Deadline deadline = new Deadline(description, by);
        parser.checkComplete(storedTaskSubstrings,deadline);

        taskList.addTask(deadline);
    }

    private void loadExistingEvent(String[] storedTaskSubstrings){
        String description = storedTaskSubstrings[2].trim();
        String time = storedTaskSubstrings[3].trim();
        String start = time.split("-")[0];
        String end = time.split("-")[1];

        Event event = new Event(description, start, end);
        parser.checkComplete(storedTaskSubstrings,event);

        taskList.addTask(event);
    }

    /**
     * Print previously stored tasks from the txt file
     */
    private void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Create txt file if file not found at the file path
     */
    private void createFile() {
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            //Create file is the file doesn't exist at the particular directory
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    /**
     * Saves the new task into a txt file. Input is generated from the createDeadlineTxt(),
     * createEventTxt(),createTodoTxt() methods.These methods are found in the respective
     * deadline, event and todo classes respectively.
     *
     * @param input String of text that is going to be stored in the txt file
     */
    public void saveNewData(String input) {
        try {
            appendToFile(input);
        } catch (IOException e) {
            System.out.println("Something went wrong while saving the data: " + e.getMessage());
        }
    }

    /**
     * Writes the input from saveNewData() method into the txt file
     */
    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(Utils.FILE_PATH, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }
}
