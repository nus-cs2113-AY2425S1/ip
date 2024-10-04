package Storage;
import Parser.Parser;
import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import constants.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage{

    private Parser parser = new Parser();

    public Storage() {

    }

    public ArrayList<Task> loadExistingData() {
        ArrayList<Task> items = new ArrayList<>();
        try {
            storeExistingData(items);
        } catch (FileNotFoundException e) {
            createFile();
        }
        return items;
    }

    public void printFileContents() throws FileNotFoundException {
        File f = new File(Utils.FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private void storeExistingData(ArrayList<Task> items) throws FileNotFoundException {
        try {
            File file = new File(Utils.FILE_PATH);
            Scanner s = new Scanner(file);

            while (s.hasNextLine()) {
                String storedTask = s.nextLine();
                String[] storedTaskSubstrings = storedTask.split("\\| ");
                String command = storedTaskSubstrings[0].trim();

                switch (command) {
                    case "T":
                        loadExistingTodo(storedTaskSubstrings,items);
                        break;
                    case "D":
                        loadExistingDeadline(storedTaskSubstrings,items);
                        break;
                    case "E":
                        loadExistingEvent(storedTaskSubstrings,items);
                        break;
                }
            }

        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    public void saveNewData(ArrayList<Task> items) {
        try {
            clearFile();
            for (Task item : items) {
                writeToFile(item.createTaskTxt());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(Utils.FILE_PATH, true);
        fw.write(System.lineSeparator() + textToAdd);
        fw.close();
    }

    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter(Utils.FILE_PATH, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while clearing the file: " + e.getMessage());
        }
    }

    public static void createFile() {
        try {
            File file = new File(Utils.FILE_PATH);

            // Check if the parent directory exists
            if (!file.getParentFile().exists()) {
                // Create the directory if it doesn't exist
                file.getParentFile().mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + file.getAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    public void loadExistingDeadline(String[] storedTaskSubstrings,ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        String by = storedTaskSubstrings[3].trim();
        Deadline deadline = new Deadline(description, by);
        parser.checkComplete(storedTaskSubstrings, deadline);

        items.add(deadline);
    }

    public void loadExistingEvent(String[] storedTaskSubstrings, ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        String time = storedTaskSubstrings[3].trim();
        String start = time.split("-")[0];
        String end = time.split("-")[1];

        Event event = new Event(description, start, end);
        parser.checkComplete(storedTaskSubstrings, event);

        items.add(event);
    }

    public void loadExistingTodo(String[] storedTaskSubstrings, ArrayList<Task> items) {
        String description = storedTaskSubstrings[2].trim();
        Todo todo = new Todo(description);
        parser.checkComplete(storedTaskSubstrings, todo);

        items.add(todo);
    }

}
