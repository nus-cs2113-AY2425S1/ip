package bob.storage;

import bob.task.Task;
import bob.task.ToDo;
import bob.task.Deadline;
import bob.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final int MAX_TASKS = 100;
    private static final String SEPARATOR = "\\|";
    private static final int MIN_TASK_LENGTH = 3;
    private static final int MIN_DEADLINE_LENGTH = 4;
    private static final int MIN_EVENT_LENGTH = 4;
    private static final int MIN_EVENT_TIME_LENGTH = 2;
    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath).toString();
    }

    public Task[] load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        createDirectoryIfNotExists(file);
        createFileIfNotExists(file);

        try (Scanner scanner = new Scanner(file)) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                try {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return tasks.toArray(new Task[MAX_TASKS]);
    }

    private void createDirectoryIfNotExists(File file) {
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            System.out.println("Folder has not existed yet, creating a new folder.");
            if (!parentDirectory.mkdirs()) {
                System.out.println("Folder failed to be created.");
            }
        }
    }

    private void createFileIfNotExists(File file) {
        if (!file.exists()) {
            System.out.println("File has not existed yet, creating a new file.");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    private Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split(SEPARATOR);
        if (parts.length < MIN_TASK_LENGTH) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }

        String type = parts[0].trim();
        boolean isDone;
        try {
            isDone = Integer.parseInt(parts[1].trim()) == 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid done status: " + parts[1].trim());
        }
        String description = parts[2].trim();

        Task task;
        switch (type) {
        case TASK_TODO:
            task = new ToDo(description);
            break;
        case TASK_DEADLINE:
            if (parts.length < MIN_DEADLINE_LENGTH) {
                throw new IllegalArgumentException("Invalid deadline format: " + line);
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case TASK_EVENT:
            if (parts.length < MIN_EVENT_LENGTH) {
                throw new IllegalArgumentException("Invalid event format: " + line);
            }
            String[] eventTimes = parts[3].trim().split("-");
            if (eventTimes.length != MIN_EVENT_TIME_LENGTH) {
                throw new IllegalArgumentException("Invalid event times: " + parts[3].trim());
            }
            task = new Event(description, eventTimes[0].trim(), eventTimes[1].trim());
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    public void save(Task[] tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                writer.write(taskToString(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    public void appendTask(Task task) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(taskToString(task) + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("An error occurred while appending the task: " + e.getMessage());
        }
    }

    private String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof ToDo) {
            sb.append(TASK_TODO);
        } else if (task instanceof Deadline) {
            sb.append(TASK_DEADLINE);
        } else if (task instanceof Event) {
            sb.append(TASK_EVENT);
        }
        sb.append(" | ").append(task.getStatusIcon().equals("X") ? "1" : "0");
        sb.append(" | ").append(task.getDescription());
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).getFrom());
            sb.append("-").append(((Event) task).getTo());
        }
        return sb.toString();
    }
}
