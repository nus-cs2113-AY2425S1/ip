package jeremy.util;

import jeremy.command.Command;
import jeremy.exception.InvalidStorageException;
import jeremy.task.Todo;
import jeremy.task.Deadline;
import jeremy.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String PATH = "./data/";
    private static final String FILE_NAME = "Jeremy.txt";
    private static final String SEPARATOR = " \\| ";

    public Storage() {}

    public void createTask(String[] parts, TaskList taskList) {
        try {
            String commandStr = parts[0];
            boolean isNotDone = parts[1].equals("0");
            boolean isDone = parts[1].equals("1");

            if (!isNotDone && !isDone) {
                throw new InvalidStorageException("Corrupted storage :(");
            }

            Command command = Command.fromIcon(commandStr);

            switch (command) {
            case TODO:
                taskList.addTask(new Todo(parts[2], isDone));
                break;
            case DEADLINE:
                taskList.addTask(new Deadline(parts[2] + " /by " + parts[3], isDone));
                break;
            case EVENT:
                taskList.addTask(new Event(parts[2] + " /from " + parts[3]
                        + " /to " + parts[4], isDone));
                break;
            default:
                // should already be thrown by fromIcon method, but just in case
                throw new InvalidStorageException("Corrupted storage :(");
            }
        } catch (Exception ignored) {
            // ignore lines with incorrect format, but continue execution of program
        }
    }

    public TaskList load() throws FileNotFoundException {
        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(PATH + FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileNotFoundException(e.getMessage());
            }
        }

        Scanner scanner = new Scanner(file);
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(SEPARATOR);

            if (parts.length >= 3) {
                createTask(parts, taskList);
            }
        }

        return taskList;
    }

    public void save (TaskList tasks) {
        File file = new File(PATH + FILE_NAME);

        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(tasks.getTask(i).toStorageString() + System.lineSeparator());
            }
        } catch (IOException e) {
            Ui ui = new Ui();
            ui.println("Unable to save data: " + e.getMessage());
        }
    }
}
