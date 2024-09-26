package appal.storage;

import appal.commands.Command;
import appal.exception.AppalException;
import appal.exception.NoSavedTasksException;
import appal.exception.SaveTasksErrorException;
import appal.parser.Parser;
import appal.task.Task;
import appal.task.TaskList;
import appal.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

import static appal.common.Messages.LOAD_SAVED_TASKS_MESSAGE;

public class Storage {
    // Integer constants for specific type of tasks
    public static final int TASK_INDEX = 1;

    // Constants for file reading
    public static final String FILE_PATH = "./data/saved_tasks.txt";
    public static final Path FILE_DIRECTORY = Paths.get("./data");
    public static final String COMMA_SEPARATOR = ", ";
    public static final String LINE_BREAK = "\n";
    public static final String TASK_MARKED_VALUE = "1";

    private Parser parser;

    public Storage() {
        parser = new Parser();
    }

    public void loadFileContents(TaskList taskList, Ui ui) throws FileNotFoundException, AppalException {
        File savedTasks = new File(FILE_PATH); // create a File for the given file path
        Scanner fileReader = new Scanner(savedTasks); // create a Scanner using the File as the source
        int savedTasksCount = 0;
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            String[] words = line.split(COMMA_SEPARATOR);
            boolean isTaskMarked = words[0].equals(TASK_MARKED_VALUE);
            String[] taskDetails = Arrays.copyOfRange(words, TASK_INDEX, words.length);
            Command command = parser.extractCommand(taskDetails, false);
            command.execute(taskList, ui, this);
            taskList.markTask(savedTasksCount, isTaskMarked);
            savedTasksCount += 1;
        }
        Task.setTotalTasks(savedTasksCount);
    }

    public void loadExistingTasksData(TaskList taskList, Ui ui) throws NoSavedTasksException {
        try {
            loadFileContents(taskList, ui);
        } catch (FileNotFoundException | AppalException e) {
            throw new NoSavedTasksException();
        }
        ui.printMessage(LOAD_SAVED_TASKS_MESSAGE);
    }

    public void saveTasksToFile(TaskList taskList) throws SaveTasksErrorException {
        try {
            Files.createDirectories(FILE_DIRECTORY);
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < Task.getTotalTasks(); i++) {
                fw.write(taskList.getTask(i).getStatusValue() + COMMA_SEPARATOR +
                        taskList.getTask(i).getTaskInfo());
                fw.write(LINE_BREAK);
            }
            fw.close();
        } catch (IOException e) {
            throw new SaveTasksErrorException();
        }
    }
}
