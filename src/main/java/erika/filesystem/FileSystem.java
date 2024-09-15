package erika.filesystem;
import erika.exception.FileFormatErrorException;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem {
    private String filePath;
    private String separator;
    private static boolean dirtyBit = false;
    public FileSystem(String filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;
    }

    public ArrayList<Task> readFromFile() throws FileNotFoundException, IOException, FileFormatErrorException {
        File f = new File(filePath);
        if(!f.exists()) {
            createDirectory(Settings.DIRECTORY);
            writeToFile("",false);
            throw new FileNotFoundException(filePath);
        }
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        int lineNumber = 1;
        while (s.hasNext()) {
            parseLine(tasks, s.nextLine(), lineNumber++);
        }
        return tasks;
    }

    public void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }

    public void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.generateFileLine());
        fw.close();
    }

    public void updateFileSystemWithLocalTasks(ArrayList<Task> tasks) throws IOException{
        if (!dirtyBit) {
            return;
        }
        FileWriter fw = new FileWriter(filePath, false);
        for (Task t : tasks) {
            String res = t.generateFileLine();
            fw.write(res);
        }
        fw.close();
    }

    public void printFileNotFoundMessage() {
        System.out.println("\t" + filePath + " not found. Creating new file.");
    }

    public static void setDirtyBit() {
        dirtyBit = true;
    }

    public static boolean getDirtyBit() {
        return dirtyBit;
    }

    private void createDirectory(String directoryName) {
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void parseLine(ArrayList<Task> tasks, String line, int lineNumber) throws FileFormatErrorException {
        String[] tokens = line.split(separator);
        if (tokens.length < 3) {
            throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid line, too few entries");
        }
        String description = tokens[2];
        switch (tokens[0]) {
        case "T":
            if (tokens.length != 3) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Todo line!");
            }
            Todo todo = new Todo(description);
            todo.setMark(tokens[1].equals("1"));
            tasks.add(todo);
            break;

        case "D":
            if (tokens.length != 4) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Deadline line!");
            }
            Deadline deadline = new Deadline(description, tokens[3]);
            deadline.setMark(Boolean.parseBoolean(tokens[1]));
            tasks.add(deadline);
            break;
        case "E":
            if (tokens.length != 5) {
                throw new FileFormatErrorException("Error on line " + lineNumber + ": invalid Event line!");
            }
            Event event = new Event(description, tokens[3], tokens[4]);
            event.setMark(Boolean.parseBoolean(tokens[1]));
            tasks.add(event);
            break;
        }
    }
}
