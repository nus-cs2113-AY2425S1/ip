package nus.edu.rizzler.manager;

import nus.edu.rizzler.exception.RizzlerException;
import nus.edu.rizzler.task.Deadline;
import nus.edu.rizzler.task.Event;
import nus.edu.rizzler.task.Task;
import nus.edu.rizzler.task.Todo;
import nus.edu.rizzler.ui.Emoji;

import java.util.ArrayList;

public class TaskList {
    private Emoji emoji = new Emoji();
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String csvString) {
        tasks = new ArrayList<>();
        loadTasks(csvString);
    }

    public int getSize() {
        return tasks.size();
    }

    public String toString() {
        int taskCount = getSize();
        if (taskCount == 0) {
            return "Nothing in the pipeline yet! Let's get to work! "
                    + emoji.getRocketEmoji() + emoji.getHundredPointsEmoji();
        }

        StringBuilder tasksString = new StringBuilder();
        for (int taskIndex = 0; taskIndex < taskCount; taskIndex++) {
            Task task = tasks.get(taskIndex);

            if (taskIndex > 0){
                tasksString.append("\n");
            }
            tasksString.append(String.format("%d. %s", taskIndex + 1, task));
        }
        return tasksString.toString();
    }

    public String addTodo(String taskName, Boolean isDone) {
        Todo todo = new Todo(taskName, isDone);
        tasks.add(todo);
        return todo.toString();
    }

    public String addDeadline(String taskName, Boolean isDone, String by) {
        Deadline deadline = new Deadline(taskName, isDone, by);
        tasks.add(deadline);
        return deadline.toString();
    }

    public String addEvent(String taskName, Boolean isDone, String from, String to) {
        Event event = new Event(taskName, isDone, from, to);
        tasks.add(event);
        return event.toString();
    }

    public String updateTaskStatus(int taskIndex, boolean isDone) {
        if (taskIndex >= this.getSize()) {
            throw new RizzlerException("Update task status failed. Task index is out of range.");
        }

        Task task = tasks.get(taskIndex);
        task.setIsDone(isDone);
        return task.toString();
    }

    public String deleteTask(int taskIndex) {
        if (taskIndex >= this.getSize()) {
            throw new RizzlerException("Delete Task failed. Task index is out of range.");
        }

        Task task = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        return task.toString();
    }

    public String toCSVString() {
        StringBuilder csvString = new StringBuilder();

        for (int taskIndex = 0; taskIndex < getSize(); taskIndex++) {
            Task task = tasks.get(taskIndex);
            if (taskIndex != 0){
                csvString.append(System.lineSeparator());
            }
            csvString.append(task.toCSV());
        }
        return csvString.toString();
    }

    public void loadTasks(String csvString) throws RizzlerException {
        try{
            String[] taskStrings = csvString.split("\n");
            for (String taskString : taskStrings) {
                String[] taskArguments = taskString.split(", ");
                String taskType = taskArguments[0];
                Boolean isDone = Boolean.parseBoolean(taskArguments[1]);

                switch (taskType) {
                case "T": addTodo(taskArguments[2], isDone);
                    break;
                case "D": addDeadline(taskArguments[2], isDone, taskArguments[3]);
                    break;
                case "E": addEvent(taskArguments[2], isDone, taskArguments[3], taskArguments[4]);
                    break;
                default: throw new RizzlerException("Rizzler.java is corrupted. Could not" +
                        " recover previously saved tasks");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RizzlerException("Rizzler.java is corrupted. Saved tasks have missing fields.");
        } catch (Exception e) {
            throw new RizzlerException("Rizzler.java is corrupted. Could not " +
                    "recover previously saved tasks" + e.getMessage());
        }
    }
}
