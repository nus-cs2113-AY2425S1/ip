package bron.command;

import bron.exception.*;
import bron.task.Deadline;
import bron.task.Event;
import bron.task.Task;
import bron.task.ToDo;
import bron.storage.FileStorage;

import java.util.ArrayList;

public class CommandHandler {
    private ArrayList<Task> tasks;
    private FileStorage storage;  // Add FileStorage object

    public CommandHandler(ArrayList<Task> tasks, int taskCount) {
        this.tasks = tasks;
        this.storage = new FileStorage();

    }

    public void handleCommand(Command command, String line) {
        try {
            switch (command) {
            case BYE:
                printByeMessage();
                break;
            case LIST:
                handleList();
                break;
            case MARK:
                handleMark(parseIndex(line));
                storage.save(tasks);
                break;
            case DELETE:
                deleteTask(parseIndex(line));
                storage.save(tasks);
                break;
            case UNMARK:
                handleUnmark(parseIndex(line));
                storage.save(tasks);
                break;
            case TODO:
                handleTodo(line);
                storage.save(tasks);
                break;
            case DEADLINE:
                handleDeadline(line);
                storage.save(tasks);
                break;
            case EVENT:
                handleEvent(line);
                storage.save(tasks);
                break;
            default:
                throw new InvalidCommandException();
            }
        } catch (BronException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printByeMessage() {
        System.out.println("Catch you on the flip cuh");
    }

    private int parseIndex(String line) {
        String[] parts = line.split(" ");
        return Integer.parseInt(parts[1]) - 1;
    }

    private void handleList() {
        System.out.println("You got " + tasks.size() + " task(s)");
        int count = 1;
        for (Task task : tasks) {
            System.out.println(count++ + ". " + task);
        }
    }

    private void deleteTask(int index) throws TaskIndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new TaskIndexOutOfBoundsException();
        }

        System.out.println("You deleted \n" + tasks.get(index));
        tasks.remove(index);
        System.out.println("You have " + tasks.size() + " task(s)");
    }

    private void handleMark(int index) throws TaskIndexOutOfBoundsException {
        tasks.get(index).markAsDone();
        System.out.println("Good shit kid! I've marked this task as done:");
        System.out.println("  " + tasks.get(index));
    }

    private void handleUnmark(int index) throws TaskIndexOutOfBoundsException {
        tasks.get(index).markAsNotDone();
        System.out.println("Get yo shit together son, this task aint done yet:");
        System.out.println("  " + tasks.get(index));
    }

    private void handleTodo(String line) throws EmptyTodoDescriptionException {
            if (!line.contains(" ") || line.substring(line.indexOf(" ") + 1).trim().isEmpty()) {
                throw new EmptyTodoDescriptionException();
            }

            String taskDescription = line.substring(line.indexOf(" ") + 1).trim();
            ToDo todo = new ToDo(taskDescription);
            tasks.add(todo);

            System.out.println("Got it. I've added this task:");
            System.out.println(todo);
            System.out.println("You got " + tasks.size() + " task(s)");
    }

    private void handleDeadline(String line) throws InvalidDeadlineFormatException {
            if (!line.contains("/by")) {
                throw new InvalidDeadlineFormatException("The deadline must include '/by' followed by the date.");
            }

            String[] deadlineParts = line.split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[1].trim().isEmpty()) {
                throw new InvalidDeadlineFormatException("Please provide a valid deadline after '/by'.");
            }

            String taskDescription = deadlineParts[0].trim();
            if (taskDescription.length() < 9) {
                throw new InvalidDeadlineFormatException("The description of a deadline cannot be empty.");
            }
            taskDescription = taskDescription.substring(9).trim();  // Extract the task description
            String byWhen = deadlineParts[1].trim();

            Deadline deadline = new Deadline(taskDescription, byWhen);
            tasks.add(deadline);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + deadline);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void handleEvent(String line) throws InvalidEventFormatException {
            if (!line.contains("/from") || !line.contains("/to")) {
                throw new InvalidEventFormatException("The event must include '/from' and '/to' to specify the time.");
            }

            String[] eventParts = line.split("/from", 2);
            if (eventParts.length < 2 || eventParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid event timings after '/from' and '/to'.");
            }

            String taskDescription = eventParts[0].trim();
            if (taskDescription.length() < 6) {
                throw new InvalidEventFormatException("The description of an event cannot be empty.");
            }

            taskDescription = taskDescription.substring(6).trim();
            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new InvalidEventFormatException("Please provide valid start and end times.");
            }

            String fromTime = timeParts[0].trim();
            String toTime = timeParts[1].trim();

            Event event = new Event(taskDescription, fromTime, toTime);
            tasks.add(event);

            System.out.println("Got it. I've added this task:");
            System.out.println("  " + event);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
