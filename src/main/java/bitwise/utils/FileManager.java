package bitwise.utils;

import bitwise.constants.Constants;
import bitwise.exceptions.InvalidFormatException;
import bitwise.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static void createFileIfNotExists() throws IOException{
        File dir = new File(Constants.FILE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File tasks = new File(dir, Constants.FILE_NAME);
        tasks.createNewFile();
    }

    public static void saveTasksList(ArrayList<Task> tasksList, int numberOfTasks) throws IOException{
        createFileIfNotExists();
        FileWriter fw = new FileWriter(Constants.FILE_PATH);
        for (int i = 0; i < numberOfTasks; i++) {
            fw.write(tasksList.get(i).toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void saveTask(Task task) throws IOException{
        createFileIfNotExists();
        FileWriter fw = new FileWriter(Constants.FILE_PATH, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    public static int getTasks(ArrayList<Task> tasksList) throws IOException{
        File f = new File(Constants.FILE_PATH);
        int numberOfTasks = 0;
        if (!f.exists()) {
            return 0;
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String toProcess = line.substring(line.indexOf(" ") + 1);
            Task newTask;
            switch(line.substring(Constants.PARSE_ICON_INDEX, line.indexOf(" "))) {
            case Constants.ICON_TODO:
                newTask = TextParser.handleTodo(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            case Constants.ICON_EVENT:
                newTask = TextParser.handleEvent(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            case Constants.ICON_DEADLINE:
                newTask = TextParser.handleDeadline(toProcess);
                tasksList.add(newTask);
                numberOfTasks++;
                break;
            default:
                throw new InvalidFormatException("File corrupt: " + line);
            }
        }
        return numberOfTasks;
    }
}
