package Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Tasks.Task;

public class Storage {
    private static final String FILE_PATH = "src/main/Data/tasks.txt";
    private final TaskList taskList;
    private final Ui ui;

    public Storage(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void loadTasks() {
        ui.printWithSeparators("Loading tasks from file...");
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] currLine = scanner.nextLine().split("\\|");
                boolean isDone = currLine[1].trim().equals("1");
                switch (currLine[0].trim()) {
                case "T":
                    taskList.addTodoFromFile(currLine[2], isDone);
                    break;
                case "D":
                    taskList.addDeadlineFromFile(currLine[2].trim(), currLine[3].trim(), isDone);
                    break;
                case "E":
                    taskList.addEventFromFile(currLine[2].trim(), currLine[3].trim(), currLine[4].trim(), isDone);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown task type");
                }
            }
        } catch (IOException e) {
            ui.printWithSeparators("Error loading tasks: " + e.getMessage());
        }
    }

    public void saveTasks() {
        ui.printWithSeparators("Saving tasks to file...");
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : taskList.getTasks()) {
                fw.write(task.toFile() + System.lineSeparator());
            }
        } catch (IOException e) {
            ui.printWithSeparators("Error saving tasks: " + e.getMessage());
        }
    }
}