package aegis;

import aegis.task.Task;
import aegis.task.Deadline;
import aegis.task.Event;
import aegis.task.Todo;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws AegisException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new AegisException(" No task file found. A new file will be created.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new AegisException(" Error reading from file.");
        }

        return taskList;
    }

    private Task parseTask(String line) throws AegisException {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            Todo todo = new Todo(parts[2]);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Deadline deadline = new Deadline(parts[2], parts[3]);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            Event event = new Event(parts[2], parts[3], parts[4]);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        default:
            throw new AegisException("File format corrupted");
        }
    }

    public void save(ArrayList<Task> taskList) throws AegisException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new AegisException(" Error saving tasks to file.");
        }
    }
}
