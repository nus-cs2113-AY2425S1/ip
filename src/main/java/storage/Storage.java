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

public class Storage {
    public static final String FILE_PATH = "./data/save.txt";
    public static final Path DATA_DIRECTORY = Paths.get("./data");

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
        } catch (IndexOutOfBoundsException | FileNotFoundException | BentoException e) {
            throw new LoadFileErrorException();
        }
    }

    public void saveTaskListQuiet(TaskList tasks, Ui ui, Parser parser) throws SaveFileErrorException {
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
            // ui.printSaveTaskListSuccessMessage();
        } catch (IOException e) {
            throw new SaveFileErrorException();
        }
    }

    public void saveTaskList(TaskList tasks, Ui ui, Parser parser) throws SaveFileErrorException {
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
            ui.printSaveTaskListSuccessMessage();
        } catch (IOException e) {
            throw new SaveFileErrorException();
        }
    }

    public void writeTaskStatus(Task task, FileWriter saveWriter) throws IOException {
        if (task.isDone()) {
            saveWriter.write(TASK_DONE_INDICATOR);
        } else {
            saveWriter.write(TASK_UNDONE_INDICATOR);
        }
    }


}
