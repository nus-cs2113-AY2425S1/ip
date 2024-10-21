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
import java.util.Arrays;
import java.util.Scanner;

import static appal.common.Messages.LOAD_SAVED_TASKS_MESSAGE;
import static appal.common.Utils.COMMA_SEPARATOR;
import static appal.common.Utils.FILE_DIRECTORY;
import static appal.common.Utils.FILE_PATH;
import static appal.common.Utils.LINE_BREAK;
import static appal.common.Utils.TASK_MARKED_VALUE;
import static appal.common.Utils.TASK_INDEX;

/**
 * Storage class handles the saving of tasks
 * and loading of previously saved tasks.
 */
public class Storage {
    private Parser parser;

    /**
     * Class constructor.
     */
    public Storage() {
        parser = new Parser();
    }

    /**
     * Loads previously saved tasks from a text file into current task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @throws FileNotFoundException if saved tasks text file is not found.
     * @throws AppalException if error occurs while extracting or executing a command from the saved tasks file.
     */
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

    /**
     * Initiates the process of loading previously saved tasks into current task list.
     *
     * @param taskList Current task list tracked by Appal.
     * @param ui Ui instance for Appal to show messages.
     * @throws NoSavedTasksException if saved tasks text file is not found, or error occurs while loading tasks.
     */
    public void loadExistingTasksData(TaskList taskList, Ui ui) throws NoSavedTasksException {
        try {
            loadFileContents(taskList, ui);
        } catch (FileNotFoundException | AppalException e) {
            throw new NoSavedTasksException();
        }
        ui.printMessage(LOAD_SAVED_TASKS_MESSAGE);
    }

    /**
     * Stores tasks from current task list into a saved tasks text file for future use.
     *
     * @param taskList Current task list tracked by Appal.
     * @throws SaveTasksErrorException if error occurs while saving the tasks into the text file.
     */
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
