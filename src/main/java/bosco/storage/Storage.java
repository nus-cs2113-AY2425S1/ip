package bosco.storage;

import bosco.parser.DateTimeParser;
import bosco.task.Task;
import bosco.task.Todo;
import bosco.task.Deadline;
import bosco.task.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a storage object that manages
 * loading and saving tasks in a data file.
 */
public class Storage {
    private final Path path;

    /**
     * Class constructor.
     *
     * @param filePath File path of data file storing tasks.
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
        try {
            Files.createDirectories(path.getParent());
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the data file, converts each entry to the specified
     * <code>Todo</code>, <code>Deadline</code> or <code>Event</code> object,
     * and returns all the tasks in a list.
     *
     * @return <code>ArrayList</code> of all the tasks from the data file.
     * @throws IOException If the I/O operation fails.
     */
    public ArrayList<Task> loadFileContents() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner fileScanner = new Scanner(path);
        while (fileScanner.hasNext()) {
            tasks.add(getTaskFromFileLine(fileScanner.nextLine()));
        }
        return tasks;
    }

    /**
     * Converts each task in the input list into the appropriate format,
     * and writes all entries to the data file.
     *
     * @param tasks <code>ArrayList</code> of all the tasks in the task list.
     * @throws IOException If the I/O operation fails.
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path.toFile());
        for (Task task: tasks) {
            fw.write(getFileInputForTask(task) + System.lineSeparator());
        }
        fw.close();
    }

    private Task getTaskFromFileLine(String fileLine) {
        String[] stringParts = fileLine.split(" \\| ");
        boolean isDone = (stringParts[1].equals("X"));
        String taskType = stringParts[0];
        String description = stringParts[2];
        switch(taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, isDone, DateTimeParser.parseDateTime(stringParts[3]));
        case "E":
            return new Event(description, isDone, DateTimeParser.parseDateTime(stringParts[3]),
                    DateTimeParser.parseDateTime(stringParts[4]));
        default:
            throw new RuntimeException();
        }
    }

    private String getFileInputForTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + task.getStatusIcon() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return "D | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Deadline)task).getBy();
        } else {
            return "E | " + task.getStatusIcon() + " | " + task.getDescription()
                    + " | " + ((Event)task).getFrom() + " | " + ((Event)task).getTo();
        }
    }
}
