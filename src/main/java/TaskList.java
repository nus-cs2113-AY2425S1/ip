import exceptions.IllegalCommandException;
import exceptions.IncompleteCommandException;
import tasks.*;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int taskCount = 0;
    private static final int TODO_CHAR_COUNT = 4;
    private static final int DEADLINE_CHAR_COUNT = 8;
    private static final int EVENT_CHAR_COUNT = 5;
    private static final int FIND_CHAR_COUNT = 4;

    // may not need
    public TaskList() {
    }

    public void listTask() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        Ui.printHorizontalLine();
    }

    public void markTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.markAsDone();
            Ui.printMarkedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void unmarkTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            t.unmark();
            Ui.printUnmarkedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void addTodo(String line) {
        String description = line.substring(TODO_CHAR_COUNT + 1).trim();
        Task t =  new Todo(description);
        tasks.add(t);
        Ui.printAddedTaskMessage();
        Storage.saveTaskList(tasks);
    }

    public void addDeadline(String line) {
        try {
            String[] twoParts = line.substring(DEADLINE_CHAR_COUNT + 1).trim().split(" /by ");
            if (twoParts.length != 2) {
                Ui.printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = twoParts[0];
            String by = twoParts[1];
            Task t = new Deadline(description, by);
            tasks.add(t);
            Ui.printAddedTaskMessage();
            Storage.saveTaskList(tasks);
        } catch (IncompleteCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void addEvent(String line) {
        try {
            String[] threeParts = line.substring(EVENT_CHAR_COUNT + 1).trim().split(" /from | /to ");
            if (threeParts.length != 3) {
                Ui.printHorizontalLine();
                throw new IncompleteCommandException("You are missing some parameters! ");
            }
            String description = threeParts[0];
            String from = threeParts[1];
            String to = threeParts[2];
            Task t = new Event(description, from, to);
            tasks.add(t);
            Ui.printAddedTaskMessage();
            Storage.saveTaskList(tasks);
        } catch (IncompleteCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void deleteTask(int taskNumber) {
        try {
            int index = taskNumber - 1;
            if (index >= taskCount | index < 0) {
                Ui.printHorizontalLine();
                throw new IllegalCommandException("This number is not valid.");
            }
            Task t = tasks.get(index);
            tasks.remove(index);
            taskCount--;
            Ui.printDeletedMessage(t);
            Storage.saveTaskList(tasks);
        } catch (IllegalCommandException e) {
            Ui.printExceptionMessage(e);
        }
    }

    public void findTask(String input) {
        boolean found = false;
        boolean foundMessageHeader = false;
        String keyword = input.substring(FIND_CHAR_COUNT + 1).trim();
        for (int i = 0; i < taskCount; i++) {
            Task task = tasks.get(i);
            String description = task.getDescription().toLowerCase();
            if (description.contains(keyword.toLowerCase())) {
                if (!foundMessageHeader) {
                    Ui.printFoundMessageHeader();
                    foundMessageHeader = true;
                }
                System.out.println((i + 1) + ". " + task);
                found = true;
            }
        }
        if (!found) {
            Ui.printMissingMessage();
        }
        Ui.printHorizontalLine();
    }

}
