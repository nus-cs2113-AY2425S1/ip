package lovespiritual;

import lovespiritual.exception.lovespiritualException;
import lovespiritual.task.Deadline;
import lovespiritual.task.Event;
import lovespiritual.task.Task;
import lovespiritual.task.Todo;
import java.util.ArrayList;

public class TaskList {
    public static final String SEPARATOR = "_".repeat(30);

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void deleteTask(String input, ArrayList<Task> tasks, int taskCount) throws lovespiritualException {
        String taskNumber = input.substring("delete".length()).trim();
        if (taskNumber.isEmpty()) {
            throw new lovespiritualException("Oopsie! (⊙_⊙) Please give me a valid number!");
        }
        int indexNumber;
        try {
            indexNumber = Integer.parseInt(taskNumber) - 1;
        } catch (NumberFormatException e) {
            throw new lovespiritualException("Hmm, that's not a number! (・_・;) Try again, please!");
        }
        Task removedTask = tasks.get(indexNumber);
        if (indexNumber >= 0 && indexNumber < taskCount) {
            tasks.remove(indexNumber);
            System.out.println(SEPARATOR);
            System.out.println("Got it! (◠‿◠) This task is removed!");
            System.out.println(removedTask);
            System.out.println(SEPARATOR);
        } else {
            throw new lovespiritualException("Yikes! (≧Д≦) That number doesn't look right. Can you double-check it?");
        }
    }

    public static Task extractTasks(String line) {
        try {
            String[] parts = line.split(" \\| ");

            if (parts.length < 3) {
                throw new lovespiritualException("Error reading task from file: Incomplete task data.");
            }

            String type = parts[0];
            boolean isMarked = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new lovespiritualException("Error reading Deadline from file: Missing 'by' date.");
                }
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 5) {
                    throw new lovespiritualException("Error reading Event from file: Missing 'from' or 'to' time.");
                }
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new lovespiritualException("Error reading task from file: Unknown task type.");
            }

            if (isMarked) {
                task.mark();
            }

            return task;
        } catch (lovespiritualException e) {
            System.out.println(SEPARATOR);
            System.out.println(e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        } catch (Exception e) {
            System.out.println(SEPARATOR);
            System.out.println("Error parsing task from file: " + e.getMessage());
            System.out.println(SEPARATOR);
            return null;
        }
    }

}
