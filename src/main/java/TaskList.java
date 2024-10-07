import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.ui = new Ui();
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void printList () {
        ui.printEnclosure();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        ui.printEnclosure();
        return;
    }

    public void toMark (String input, boolean shouldMark) throws DianaException {
        try {
            String substring = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(substring) - 1;

            if (taskNum < 0) {
                throw new DianaException("Task number cannot be negative");
            }

            if (taskNum >= tasks.size()) {
                throw new DianaException("Task number must be less than " + tasks.size());
            }

            Task task = tasks.get(taskNum);
            if (shouldMark) {
                task.markAsDone();
                ui.printEnclosure();
                System.out.println("Nice! I've marked this task as done\n" + task.toString());
                ui.printEnclosure();
            } else {
                task.markAsNotDone();
                ui.printEnclosure();
                System.out.println("Okay, I've marked this task as not done yet\n" + task.toString());
                ui.printEnclosure();
            }
        } catch (NumberFormatException e) {
            System.out.println("Number specified must be an integer");
            System.out.println("For eg: Mark 1 NOT Mark One");
        }
    }

    public void printAddedTask (String description) {
        ui.printEnclosure();
        System.out.println("Got it! I've added this task");
        System.out.println(description);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        printList();
    }

    public void addTodo (String input) throws DianaException  {
        String description = input.substring("todo".length()).trim();
        if (description.isEmpty()) {
            throw new DianaException("Description of todo task cannot be empty");
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddedTask(description);
    }

    public void addDeadline (String input) throws DianaException {
        if (input.contains("/by")) {
            String[] parts = input.split("/by", 2);
            String description = parts[0].substring("deadline".length()).trim();
            String by = parts[1].trim(); // trim removes white space
            Task deadline = new Deadline(description, by);
            tasks.add(deadline);
            printAddedTask(description);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: " +
                    "deadline <description> /by <time>");
        }
    }

    public void addEvent (String input) throws DianaException {
        if (input.contains("/from") && input.contains("/to")) {
            String[] parts = input.split("/from", 2);
            String description = parts[0].substring("event".length()).trim();
            String[] fromto = parts[1].split("/to", 2);
            String from = fromto[0].trim();
            String to = fromto[1].trim();
            Task event = new Event(description, from, to);
            tasks.add(event);
            printAddedTask(description);
        } else {
            throw new DianaException("Format Invalid. Please use the following format: " +
                    "event <description> /from <start time> /to <end time>");
        }
    }

    public void deleteTask (String input) throws DianaException {

        if (tasks.isEmpty()) {
            throw new DianaException("Task list is empty");
        }

        try {
            String substring = input.substring(input.indexOf(" ") + 1);
            int taskNum = Integer.parseInt(substring) - 1;

            if (taskNum < 0) {
                throw new DianaException("Task number cannot be negative");
            }

            if (taskNum >= tasks.size()) {
                throw new DianaException("Task number must be less than " + tasks.size());
            }

            System.out.println("Task: " + tasks.get(taskNum).toString() + " has been deleted");
            tasks.remove(taskNum);

        } catch (NumberFormatException e) {
            System.out.println("Number specified must be an integer");
        }
    }
}
