import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method for loading tasks from a file.
     * @return
     * @throws PlopBotException
     */
    public ArrayList<Task> load() throws PlopBotException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            if (Files.exists(Paths.get(filePath))) {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                for (String line : lines) {
                    tasks.add(parseTaskFromString(line));
                }
            }
        } catch (IOException e) {
            throw new PlopBotException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Method for saving tasks to a file.
     * @param tasks
     * @throws PlopBotException
     */
    public void save(ArrayList<Task> tasks) throws PlopBotException {
        try {
            Files.createDirectories(Paths.get(filePath).getParent());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(convertTaskToString(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new PlopBotException("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Parser for loading tasks from strings.
     * @param line
     * @return
     * @throws PlopBotException
     */
    private Task parseTaskFromString(String line) throws PlopBotException {
        String[] parts = line.split("\\|");
        if (parts.length < 3) {
            throw new PlopBotException("Invalid task format: " + line);
        }
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task;
        switch (type) {
            case "T":
                task = new Task(description);
                break;
            case "D":
                if (parts.length < 4) throw new PlopBotException("Invalid deadline format: " + line);
                LocalDate deadline = LocalDate.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Task(description, deadline);
                break;
            case "E":
                if (parts.length < 5) throw new PlopBotException("Invalid event format: " + line);
                LocalDateTime startTime = LocalDateTime.parse(parts[3].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                LocalDateTime endTime = LocalDateTime.parse(parts[4].trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                task = new Task(description, startTime, endTime);
                break;
            default:
                throw new PlopBotException("Unknown task type: " + type);
        }
        if (isDone) task.toggleStatus();
        return task;
    }

    /**
     * Converts tasks to strings for saving.
     * @param task
     * @return
     */
    private String convertTaskToString(Task task) {
        String baseString = String.format("%s | %d | %s",
                task.getTypeIcon(), task.getStatus() ? 1 : 0, task.getName());
        switch (task.getTypeIcon()) {
            case "D":
                return baseString + " | " + task.getDeadline().format(DateTimeFormatter.ISO_LOCAL_DATE);
            case "E":
                return baseString + " | " + task.getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                        " | " + task.getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            default:
                return baseString;
        }
    }
}
