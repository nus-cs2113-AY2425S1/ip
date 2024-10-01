package jeremy.util;

import jeremy.exception.InvalidTaskNumberException;
import jeremy.exception.TaskNotFoundException;
import jeremy.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public TaskList(TaskList taskList) {
        this.tasks = taskList.tasks;
        this.ui = new Ui();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void printList() {
        ui.lineBreak();
        for (Task task : tasks) {
            ui.println((tasks.indexOf(task) + 1) + "." + task.toString());
        }
        ui.lineBreak();
    }

    public void addTask(Task task) {
        tasks.add(task);

        // Doesn't print if task is being loaded
        if (!task.isDone()) {
            ui.lineBreak();
            ui.println("Got it. I've added this task:");
            ui.println(task.toString());
            ui.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.lineBreak();
        }
    }

    public void deleteTask(String argument) throws InvalidTaskNumberException, TaskNotFoundException {
        int index;
        Task task;
        try {
            index = Integer.parseInt(argument);
            task = tasks.get(index - 1);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(argument);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException("What. There is no task " + argument + ". "
                    + "Try a number between 1 and " + tasks.size() + ".");
        }

        ui.lineBreak();
        tasks.remove(task);
        ui.println("Ok, deleted this task:");
        ui.println(task.toString());
        ui.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.lineBreak();
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
                ui.lineBreak();
                ui.println("Nice! I've marked this task as done:");
            } else {
                tasks.get(taskNumber - 1).markNotDone();
                ui.lineBreak();
                ui.println("I've unmarked this task:");
            }
            ui.println(tasks.get(taskNumber - 1).toString());
            ui.lineBreak();
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new TaskNotFoundException("What. There is no task " + taskNumber + ". "
                    + "Try a number between 1 and " + tasks.size() + ".");
        }
    }
}
