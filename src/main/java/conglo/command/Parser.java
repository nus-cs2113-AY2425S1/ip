package conglo.command;

import conglo.exception.CongloException;
import conglo.exception.InvalidTaskNumber;
import conglo.exception.InvalidFormat;
import conglo.exception.MissingDescription;
import conglo.exception.MissingTaskNumber;
import conglo.exception.UnknownCommand;
import conglo.storage.Storage;
import conglo.task.Deadline;
import conglo.task.Event;
import conglo.task.Task;
import conglo.task.Todo;
import conglo.task.TaskList;
import conglo.ui.Ui;

import java.util.ArrayList;

public class Parser {
    private static boolean isDelete = false;
    private static Ui ui = new Ui();

    private static void saveTasks(ArrayList<Task> taskList) {
        Storage.saveTasks(taskList);
    }

    public static void echoTask(Task task) {
        ArrayList<Task> taskList = TaskList.getTaskList();
        int size = taskList.size();
        if (isDelete) {
            ui.displayRemoved();
            size--;
        } else {
            ui.displayAdded();
        }
        String taskSuffix = " task";
        if (size > 1) {
            taskSuffix = " tasks";
        }
        System.out.println(" " + task.toFileFormat());
        System.out.println("The list has " + size + taskSuffix + " now.");
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    public static void listTasks() {
        ui.displayTaskList();
    }

    public static void markTask(String[] words) throws InvalidTaskNumber {
        ArrayList<Task> taskList = TaskList.getTaskList();
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(words[1].substring(0, 1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Please provide a task number >.<");
            return;
        }

        if (taskNumber >= taskList.size() || taskNumber < 0) {
            throw new InvalidTaskNumber();
        }
        if (words[0].equals("mark")) {
            taskList.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        } else {
            taskList.get(taskNumber).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskList.get(taskNumber).toFileFormat());
    }

    public static void addTodo(String sentence) {
        ArrayList<Task> taskList = TaskList.getTaskList();
        Todo todo = new Todo(sentence);
        taskList.add(todo);
        echoTask(todo);
        saveTasks(taskList);
    }

    public static void addDeadline(String sentence) throws InvalidFormat {
        ArrayList<Task> taskList = TaskList.getTaskList();
        if (!sentence.contains(" /by ")) {
            throw new InvalidFormat("deadline");
        }
        String[] words = sentence.split(" /by ");
        Deadline deadline = new Deadline(words[0], words[1]);
        taskList.add(deadline);
        echoTask(deadline);
        saveTasks(taskList);
    }

    public static void addEvent(String sentence) throws InvalidFormat {
        ArrayList<Task> taskList = TaskList.getTaskList();
        if (!sentence.contains(" /from ") || !sentence.contains(" /to ")) {
            throw new InvalidFormat("event");
        }
        String[] words = sentence.split(" /from | /to ");
        Event event = new Event(words[0], words[1], words[2]);
        taskList.add(event);
        echoTask(event);
        saveTasks(taskList);
    }

    public static void deleteTask(String word) throws InvalidTaskNumber {
        ArrayList<Task> taskList = TaskList.getTaskList();
        int index;
        try {
            index = Integer.parseInt(word) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid format! Please provide a task number >.<");
            return;
        }
        if (index >= taskList.size() || index < 0) {
            throw new InvalidTaskNumber();
        }
        isDelete = true;
        echoTask(taskList.get(index));
        taskList.remove(index);
        isDelete = false;
        saveTasks(taskList);
    }

    public static void processCommand(String command) throws CongloException {
        String[] words = command.split(" ", 2);
        switch(words[0]) {
        case "bye":
            break;
        case "list":
            if (words.length > 1) {
                throw new InvalidFormat("list");
            }
            listTasks();
            break;
        case "unmark":
        case "mark":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new MissingTaskNumber(words[0]);
            }
            markTask(words);
            break;
        case "delete":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new MissingTaskNumber(words[0]);
            }
            deleteTask(words[1]);
            break;
        case "todo":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new MissingDescription("Todo");
            }
            addTodo(words[1]);
            break;
        case "deadline":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new MissingDescription("deadline");
            }
            addDeadline(words[1]);
            break;
        case "event":
            if (words.length == 1 || words[1].isEmpty()) {
                throw new MissingDescription("event");
            }
            addEvent(words[1]);
            break;
        default:
            throw new UnknownCommand();
        }
    }
}
