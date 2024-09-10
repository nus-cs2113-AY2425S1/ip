package nus.edu.rizzler.task;

import nus.edu.rizzler.exception.InvalidInputException;
import nus.edu.rizzler.ui.Emoji;

public class Task {
    static Emoji emoji = new Emoji();
    private String taskName;
    private Boolean isDone;

    public Task(String taskName) {
        setTaskName(taskName);
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskTag() {
        return "";
    }

    public static Task parseTaskString(String taskDescription) throws InvalidInputException {
        if (taskDescription.startsWith("deadline")) {
            taskDescription = taskDescription.substring(8).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Missing task description for deadline" + emoji.getExclamationMarkEmoji());
            }

            int byIndex = taskDescription.indexOf("BY");
            if (byIndex == -1) {
                throw new InvalidInputException("ERROR: Missing 'BY [when]' for deadline" + emoji.getExclamationMarkEmoji());
            }

            String taskName = taskDescription.substring(0, byIndex).trim();
            String by = taskDescription.substring(byIndex + 2).trim();

            if (taskName.isEmpty() || by.isEmpty()) {
                throw new InvalidInputException("ERROR: Task name and deadline must both be provided " +
                        "'[task name] BY [deadline]'" + emoji.getExclamationMarkEmoji());
            }

            return new Deadline(taskName, by);

        } else if (taskDescription.startsWith("event")) {
            taskDescription = taskDescription.substring(5).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Missing task description for event" + emoji.getExclamationMarkEmoji());
            }

            int fromIndex = taskDescription.indexOf("FROM");
            int toIndex = taskDescription.indexOf("TO");
            if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
                throw new InvalidInputException("ERROR: Missing or incorrect 'FROM [start time] TO [end time]' format for event" + emoji.getExclamationMarkEmoji());
            }

            String taskName = taskDescription.substring(0, fromIndex).trim();
            String from = taskDescription.substring(fromIndex + 4, toIndex).trim();
            String to = taskDescription.substring(toIndex + 2).trim();

            if (taskName.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new InvalidInputException("ERROR: Task name, start time, and end time must all be provided " +
                        "'[task name] FROM [start time] TO [end time]'" + emoji.getExclamationMarkEmoji());
            }

            return new Event(taskName, from, to);

        } else if (taskDescription.startsWith("todo")) {
            taskDescription = taskDescription.substring(4).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Missing task description for todo" + emoji.getExclamationMarkEmoji());
            }

            return new Todo(taskDescription);

        } else {
            throw new InvalidInputException("ERROR: Invalid task format. Please follow the task format in the menu" + emoji.getExclamationMarkEmoji());
        }
    }
}
