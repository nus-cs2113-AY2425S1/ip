import sleepy.Task.Deadline;
import sleepy.Task.Event;
import sleepy.Task.Task;
import sleepy.Task.Todo;
import java.util.ArrayList;

public class TaskManager {
    public static final String EVENT = "event";
    public static final String DEADLINE = "deadline";
    public static final String TODO = "todo";
    private ArrayList<Task> tasks;
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //add different tasks based on input, else throw exception
    public void addTask(String input) throws SleepyException {
        if (input.startsWith(EVENT)) {
            addEvent(input);
        } else if (input.startsWith(DEADLINE)) {
            addDeadline(input);
        } else if (input.startsWith(TODO)) {
            addTodo(input);
        } else {
            throw new SleepyException("Unknown command. "
                    + "Please start with 'list', 'mark', 'unmark', 'delete', 'event', 'deadline', or 'todo'.\n");
        }
    }

    private void addEvent(String input) throws SleepyException {
        //splitting and checking if there are at least 2 parts (description and event time)
        String[] parts = input.substring(5).split(" /from ");
        if (parts.length != 2) {
            throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
        }

        String description = parts[0].trim();
        String[] toParts = parts[1].split(" /to ");
        //splitting and checking if event has a from and to component
        if (toParts.length != 2 || toParts[0].trim().isEmpty() || toParts[1].trim().isEmpty()) {
            throw new SleepyException("Invalid event format. Please use: event description /from time /to time\n");
        }

        if (description.isEmpty()) {
            throw new SleepyException("Invalid event format. The event description cannot be empty\n");
        }

        String from = toParts[0].trim();
        String to = toParts[1].trim();
        Task task = new Event(description, from, to);
        addTaskToList(task);
    }

    private void addDeadline(String input) throws SleepyException {
        String[] parts = input.substring(8).split(" /by ");
        //checking if deadline has a description and by component
        if (parts.length != 2) {
            throw new SleepyException("Invalid deadline format. Please use: deadline description /by date\n");
        }

        String description = parts[0].trim();
        if (description.isEmpty()) {
            throw new SleepyException("Invalid deadline format. The deadline description cannot be empty. "
                    + "Please use: deadline description /by date\n");
        }

        String by = parts[1].trim();
        try {
            Task task = new Deadline(description, by);
            addTaskToList(task);
        } catch (IllegalArgumentException e) {
            System.out.println(LINE_SEPARATOR + e.getMessage() + "\n" + LINE_SEPARATOR);
        }
    }

    private void addTodo(String input) throws SleepyException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new SleepyException("Invalid todo format. Todo description cannot be empty\n");
        }
        Task task = new Todo(description);
        addTaskToList(task);
    }

    private void addTaskToList(Task task) {
        tasks.add(task);
        System.out.println(LINE_SEPARATOR
                + "added...\n"
                + " " + tasks.get(tasks.size() - 1).toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list...all the best, im going back to bed\n"
                + LINE_SEPARATOR);
    }

    public void deleteTask(int taskIndex) throws SleepyException{
        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new SleepyException("Invalid task number.");
        }

        System.out.println(LINE_SEPARATOR
                + "I've removed the task...\n"
                + " " + tasks.get(taskIndex - 1).toString() + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list...can I go and sleep now\n"
                +LINE_SEPARATOR);
        tasks.remove(taskIndex - 1);
    }

    public void listTasks() {
        if (!tasks.isEmpty()) {
            System.out.println(LINE_SEPARATOR
                    + "Stop bothering me, u have unfinished tasks...:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println(LINE_SEPARATOR);
        } else {
            System.out.println(LINE_SEPARATOR
                    + "You're done for the day...go snooze");
            System.out.println(LINE_SEPARATOR);
        }
    }

    public void markTask(int taskNumber) throws SleepyException {
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println(LINE_SEPARATOR
                    + "Yay...good for you:\n"
                    + " " + tasks.get(taskNumber - 1).toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("this tasks doesn't even exist...\n");

        }
    }

    public void unmarkTask(int taskNumber) throws SleepyException {
        if (taskNumber <= tasks.size() && taskNumber > 0) {
            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.println(LINE_SEPARATOR
                    + "Could you stop making me do extra work:\n"
                    + " " + tasks.get(taskNumber - 1).toString() + "\n"
                    + LINE_SEPARATOR);
        } else {
            throw new SleepyException("ummm...the task doesn't even exist...maybe you should get some sleep\n");
        }
    }

    public void findTask(String keyword) throws SleepyException {
        if (keyword.isEmpty()) {
            throw new SleepyException("Keyword cannot be empty.");
        }

        System.out.println(LINE_SEPARATOR);
        System.out.println("Here are the matching tasks in your list:");

        boolean taskFound = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            //making it easier to find keywords by changing all to lowercase
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println((i + 1) + "." + task);
                taskFound = true;
            }
        }

        if (!taskFound) {
            System.out.println("No tasks matching the keyword were found.");
        }

        System.out.println(LINE_SEPARATOR);
    }
}
