package Taylor.command;

import Taylor.task.TaskList;
import Taylor.task.Task;
import Taylor.task.Todo;
import Taylor.task.Deadline;
import Taylor.task.Event;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException, TaylorException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while (line != null) {
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch (taskType) {
                    case "T" -> tasks.add(new Todo(description, isCompleted));
                    case "D" -> tasks.add(new Deadline(description, isCompleted, parts[3].trim()));
                    case "E" -> {
                        String[] time = parts[3].split("-");
                        String from = time[0].trim();
                        String to = time[1].trim();
                        tasks.add(new Event(description, isCompleted, from, to));
                    }
                    default -> throw new TaylorException("Unknown task type in file");
                }
                line = reader.readLine();
            }
            reader.close();
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        File file = new File(filePath);
        if (file.getParentFile().mkdirs()) {
            System.out.println("Directory created");
        }
        FileWriter writer = new FileWriter(file);
        writer.write(tasks.write());
        writer.close();
    }
}