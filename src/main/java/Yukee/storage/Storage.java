package Yukee.storage;

import Yukee.task.Task;
import Yukee.task.Todo;
import Yukee.task.Deadline;
import Yukee.task.Event;
import Yukee.exception.YukeeException;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws YukeeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (type) {
                    case "T":
                        Task todo = new Todo(parts[2]);
                        if (isDone) todo.markAsDone();
                        tasks.add(todo);
                        break;
                    case "D":
                        Task deadline = new Deadline(parts[2], parts[3]);
                        if (isDone) deadline.markAsDone();
                        tasks.add(deadline);
                        break;
                    case "E":
                        Task event = new Event(parts[2], parts[3], parts[4]);
                        if (isDone) event.markAsDone();
                        tasks.add(event);
                        break;
                    default:
                        throw new YukeeException("Error loading task from file. Task type not recognized.");
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new YukeeException("Error loading data from file.");
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                String taskType = task.getTaskType();
                String isDone = task.isDone() ? "1" : "0";
                String taskDescription = task.getDescription();

                String taskLine = taskType + " | " + isDone + " | " + taskDescription;

                if (task instanceof Deadline) {
                    taskLine += " | " + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    taskLine += " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
                }
                writer.write(taskLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
