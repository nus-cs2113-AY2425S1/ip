import java.util.*;
import java.io.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] parts = taskLine.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (taskType) {
                case "T":
                    task = new TodoTask(description);
                    break;
                case "D":
                    task = new DeadlineTask(description, parts[3]);
                    break;
                case "E":
                    String[] eventParts = parts[3].split(" /to ");
                    task = new EventTask(description, eventParts[0], eventParts[1]);
                    break;
                }
                if (task != null && isDone) {
                    task.setDone(true);
                }
                tasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found.");
        }
        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                String taskLine = task.getType() + " | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                if (task instanceof DeadlineTask) {
                    taskLine += " | " + ((DeadlineTask) task).getBy();
                } else if (task instanceof EventTask) {
                    taskLine += " | " + ((EventTask) task).getEventDetails();
                }
                writer.write(taskLine + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}