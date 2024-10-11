import exception.FlashException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addDeadline(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseDeadline(input);
            String description = parsedInput[0];
            String by = parsedInput[1];
            Task task = new Deadline(description, by);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    public void addEvent(String input) throws FlashException {
        try {
            String[] parsedInput = Parser.parseEvent(input);
            String description = parsedInput[0];
            String from = parsedInput[1];
            String to = parsedInput[2];
            Task task = new Event(description, from, to);
            tasks.add(task);
            UI.displayTaskAdded(tasks, task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new FlashException("Uh-oh! Description for task.Event Needed!! Cannot be left empty.");
        }
    }

    public void deleteTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            UI.displayTaskDeleted(tasks, task);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new FlashException("Uh-oh! task.Task number is needed for deletion. Enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public void addTodo(String input) throws FlashException {
        if (input.length() <= 5) {
            throw new FlashException("Uh-oh! Description for Todo Needed!! Cannot be left empty.");
        }

        String description = Parser.parseTodo(input);
        if (description.isEmpty()) {
            throw new FlashException("Uh-oh! Description for task.ToDo Needed!! Cannot be left empty.");
        }

        Task task = new ToDo(description);
        tasks.add(task);
        UI.displayTaskAdded(tasks, task);
    }

    public void unMarkTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = this.tasks.get(taskNumber);
            task.markNotDone();
            UI.displayTaskUnmarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public void markTask(String input) throws FlashException {
        try {
            int taskNumber = Parser.parseTaskNumber(input);
            Task task = this.tasks.get(taskNumber);
            task.markDone();
            UI.displayTaskMarked(task);
        } catch (Exception e) {
            throw new FlashException("Invalid task number. Please enter a valid task number.");
        }
    }

    public void listMatchedTasks(String input) throws FlashException {
        try{
            String keyword = Parser.parseKeyword(input);
            int index = 1;
            List<Task> matchedTasks = new ArrayList<>();
            for (Task task : this.tasks) {
                if(task.getDescription().contains(keyword)) {
                    matchedTasks.add(task);
                }
            }
            UI.displayMatchedTasks(matchedTasks);
        } catch (Exception e) {
            throw new FlashException("Invalid Keyword");
        }
    }
}
