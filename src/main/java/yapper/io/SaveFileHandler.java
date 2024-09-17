package yapper.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import yapper.exceptions.ErrorHandler;
import yapper.exceptions.YapperException;
import yapper.tasks.*;

public class SaveFileHandler {
    public static final String SAVE_FILE_PATH = "./data/duke.txt";

    // TODO
    public void checkIfFileExists() {
        // create file if it doesn't exist
        File file = new File("./data");
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    // TODO
    public static void storeTasksData(List<Task> tasks) {
        System.out.println(StringStorage.BEFORE_SAVING_STRING + SAVE_FILE_PATH);
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE_PATH);
            for (Task task : tasks) {
                fileWriter.write(task.taskToString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("error during saving: " + e.getMessage()); // ?
        }
        System.out.println(StringStorage.AFTER_SAVING_STRING);
    }

    // TODO
    public static void loadTasksData(TaskHandler taskHandler) {
        System.out.println(StringStorage.BEFORE_LOADING_STRING + SAVE_FILE_PATH);
        try {
            File file = new File(SAVE_FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = parseFileInput(taskData);
                if (task != null) {
                    taskHandler.addTask(task);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list."); // ?
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        System.out.println(StringStorage.AFTER_LOADING_STRING);
    }

    private static Task parseFileInput(String taskData) {
        try {
            String[] taskParts = StringStorage.splitByDelimiter(taskData);

            String taskType = taskParts[0];
            try {
                ErrorHandler.checkIfTaskTypeValid(taskType); // indirectly checks if missing
            } catch (YapperException e) {
                StringStorage.printWithDividers(e.getMessage());
                return null;
            }

            String taskStatus = taskParts[1];
            try {
                ErrorHandler.checkIfTaskStatusValid(taskStatus); // indirectly checks if missing
            } catch (YapperException e) {
                StringStorage.printWithDividers(e.getMessage());
                return null;
            }
            boolean isDone = taskStatus.equals(StringStorage.NOT_DONE_SYMBOL);

            String taskDesc = taskParts[2];
            switch (taskType) {
                case StringStorage.TODO_SYMBOL:
                    InputStringHandler.validateTodoInstruction(taskDesc);
                    return new Todo(taskDesc, isDone);
                case StringStorage.DEADLINE_SYMBOL:
                    InputStringHandler.validateDeadlineInstruction(taskDesc, taskParts[3]);
                    return new Deadline(taskDesc, isDone, taskParts[3]);
                case StringStorage.EVENT_SYMBOL:
                    InputStringHandler.validateEventInstruction(taskDesc, taskParts[3], taskParts[4]);
                    return new Event(taskDesc, isDone, taskParts[3], taskParts[4]);
            }
        } catch (Exception e) {
            System.out.println("data unrecognised, skipping line: " + taskData); // ?
            return null;
        }
        return null; // ?
    }

}
