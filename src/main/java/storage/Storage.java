package storage;

import commands.Command;
import exception.BentoException;
import exception.LoadFileErrorException;
import exception.SaveFileErrorException;
import tasks.Task;
import tasks.TaskList;
import ui.Parser;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static constants.Regex.TASK_DONE_INDICATOR;
import static constants.Regex.TASK_STATUS_DELIMITER;
import static constants.Regex.TASK_UNDONE_INDICATOR;

/**
 * This class handles loading and saving of tasks to and from the save file.
 */
public class Storage {

    /** Path to save file */
    public static final String FILE_PATH = "./data/save.txt";

    /** Directory where data is saved */
    public static final Path DATA_DIRECTORY = Paths.get("./data");

    /**
     * Loads tasks from the saved file into the provided TaskList.
     *
     * @param tasks  The TaskList where tasks are loaded into.
     * @param ui     The Ui instance to handle user interface display.
     * @param parser The Parser instance to interpret the commands.
     * @throws LoadFileErrorException if the file cannot be found or an error occurs during loading.
     */
    public void loadTaskList(TaskList tasks, Ui ui, Parser parser) throws LoadFileErrorException {
        try {
            File saveFile = new File(FILE_PATH);
            Scanner fileScanner = new Scanner(saveFile);
            int currentTask = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                boolean isTaskDone = parser.getTaskDone(line);
                String userCommandString = parser.getCommandString(line);
                Command userCommand = parser.getCommand(userCommandString, false);
                userCommand.execute(tasks, ui, this);
                tasks.updateTask(isTaskDone, currentTask);
                currentTask++;
            }
            ui.listTasks(tasks);
        } catch (IndexOutOfBoundsException |
                 FileNotFoundException | BentoException e) {
            throw new LoadFileErrorException();
        }
    }

    /**
     * Saves tasks from the provided TaskList into the save file.
     * If {@code isQuiet} is false, a success message is printed after saving.
     *
     * @param tasks   The TaskList containing tasks to be saved.
     * @param ui      The Ui instance to handle user interface display.
     * @param parser  The Parser instance to interpret the commands.
     * @param isQuiet If true, the method suppresses the success message; otherwise, it prints the message.
     * @throws SaveFileErrorException if the file cannot be written or an error occurs during saving.
     */
    public void saveTaskList(TaskList tasks, Ui ui, Parser parser, boolean isQuiet) throws SaveFileErrorException {
        try {
            // Create data directory if it does not exist
            Files.createDirectories(DATA_DIRECTORY);
            FileWriter saveWriter = new FileWriter(FILE_PATH);
            for (Task task : tasks.getTasks()) {
                saveWriter.write(task.getTaskAsCommand());
                saveWriter.write(TASK_STATUS_DELIMITER);
                writeTaskStatus(task, saveWriter);
                saveWriter.write(System.lineSeparator());
            }
            saveWriter.close();

            // Print success message if isQuiet is false
            if (!isQuiet) {
                ui.printSaveTaskListSuccessMessage();
            }
        } catch (IOException e) {
            throw new SaveFileErrorException();
        }
    }

    /**
     * Writes the task's status (done/undone) to the file.
     *
     * @param task       The task whose status is to be written.
     * @param saveWriter The FileWriter to write the status to.
     * @throws IOException if writing to the file fails.
     */
    public void writeTaskStatus(Task task, FileWriter saveWriter) throws IOException {
        if (task.isDone()) {
            saveWriter.write(TASK_DONE_INDICATOR);
        } else {
            saveWriter.write(TASK_UNDONE_INDICATOR);
        }
    }
}
