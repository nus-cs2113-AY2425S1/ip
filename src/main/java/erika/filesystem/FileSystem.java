package erika.filesystem;
import erika.console.Console;
import erika.exception.FileFormatErrorException;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;
import erika.tasklist.TaskList;

import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileSystem {
    private String filePath;
    private String separator;

    public FileSystem(String filePath, String separator) {
        this.filePath = filePath;
        this.separator = separator;
    }

    private void reinitFileSystem() {
        try{
            Console.printMessage(Settings.FILENAME + " is corrupted, re-creating the file");
            writeToFile("",false);
        } catch (IOException _e) {
            Console.printMessage("IO Error encountered when re-creating " + Settings.FILENAME);
        }
    }

    public ArrayList<Task> initializeFileSystem() {
        ArrayList<Task> tasks = new ArrayList<>();
        try{
            readFromFile(tasks);
        } catch (FileNotFoundException e){
            printFileNotFoundMessage();
        } catch (FileFormatErrorException e) {
            reinitFileSystem();
        } catch (IOException e) {
            Console.printMessage("IO Error encountered when setting up fileSystem");
        } finally {
            return tasks;
        }
    }

    public void readFromFile(ArrayList<Task> tasks) throws FileNotFoundException, IOException, FileFormatErrorException {
        File f = new File(filePath);
        if(!f.exists()) {
            createDirectory(Settings.DIRECTORY);
            writeToFile("",false);
            throw new FileNotFoundException(filePath);
        }
        Scanner s = new Scanner(f);
        int lineNumber = 1;
        while (s.hasNext()) {
            parseLine(tasks, s.nextLine(), lineNumber++);
        }
    }

    public void writeToFile(String textToAdd, boolean isAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, isAppend);
        fw.write(textToAdd);
        fw.close();
    }

    public void appendTaskToFile(Task task) throws IOException {
        writeToFile(task.generateFileLine(),true);
    }

    public void updateFileSystemWithLocalTasks(TaskList tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath, false);
        String res = tasks.printFileLine();
        fw.write(res);
        fw.close();
    }

    public void printFileNotFoundMessage() {
        System.out.println("\t" + filePath + " not found. Creating new file.");
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
