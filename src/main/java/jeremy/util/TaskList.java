package jeremy.util;

import jeremy.exception.InvalidTaskNumberException;
import jeremy.exception.TaskNotFoundException;
import jeremy.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void printList() {
        PrintUtils.lineBreak();
        for (Task task : tasks) {
            PrintUtils.println((tasks.indexOf(task) + 1) + ". " + task.toString());
        }
        PrintUtils.lineBreak();
    }

    public void addTask(Task task) {
        PrintUtils.lineBreak();
        tasks.add(task);
        PrintUtils.println("Got it. I've added this task:");
        PrintUtils.println(task.toString());
        PrintUtils.println("Now you have " + tasks.size() + " tasks in the list.");
        PrintUtils.lineBreak();
    }

    public void markTaskAsDone(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        try {
            setTaskStatus(argument, true);
        } catch (InvalidTaskNumberException e) {
            throw new InvalidTaskNumberException(e.getMessage());
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(e.getMessage());
        }
    }

    public void markTaskAsNotDone(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        try {
            setTaskStatus(argument, false);
        } catch (InvalidTaskNumberException e) {
            throw new InvalidTaskNumberException(e.getMessage());
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException(e.getMessage());
        }
    }

    private void setTaskStatus(String argument, boolean isDone) throws InvalidTaskNumberException, TaskNotFoundException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(argument);
        }

        try {
            if (isDone) {
                tasks.get(taskNumber - 1).markDone();
                PrintUtils.lineBreak();
                PrintUtils.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber - 1).markNotDone();
                PrintUtils.lineBreak();
                PrintUtils.println("I've unmarked this task:");
            }
            PrintUtils.println(tasks.get(taskNumber - 1).toString());
            PrintUtils.lineBreak();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new TaskNotFoundException("What. There is no task " + taskNumber + ". "
                    + "Try a number between 1 and " + tasks.size() + ".");
        }
    }
}
