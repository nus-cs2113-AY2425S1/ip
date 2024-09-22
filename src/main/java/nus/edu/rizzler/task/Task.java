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

    public static Task parseSavedString(String taskDescription) throws InvalidInputException {
        if (taskDescription == null || taskDescription.trim().isEmpty()) {
            throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
        }

        taskDescription = taskDescription.trim();

        if (taskDescription.startsWith("[T]")) {
            taskDescription = taskDescription.substring(3).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            int lastSpaceIndex = taskDescription.lastIndexOf(" ");
            if (lastSpaceIndex == -1 || lastSpaceIndex == taskDescription.length() - 1) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            String taskName = taskDescription.substring(0, lastSpaceIndex).trim();
            String isDoneEmoji = taskDescription.substring(lastSpaceIndex + 1).trim();

            boolean isDone = validateIsDoneEmoji(isDoneEmoji);

            if (taskName.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            Todo todo = new Todo(taskName);
            todo.setIsDone(isDone);
            return todo;

        } else if (taskDescription.startsWith("[D]")) {
            taskDescription = taskDescription.substring(3).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            int byIndex = taskDescription.indexOf("(by:");
            int closingBracketIndex = taskDescription.indexOf(")");

            if (byIndex == -1 || closingBracketIndex == -1) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            String taskName = taskDescription.substring(0, byIndex).trim();
            String by = taskDescription.substring(byIndex + 4, closingBracketIndex).trim();
            String isDoneEmoji = taskDescription.substring(closingBracketIndex + 1).trim();

            if (isDoneEmoji.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }
            boolean isDone = validateIsDoneEmoji(isDoneEmoji);

            if (taskName.isEmpty() || by.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            Deadline deadline = new Deadline(taskName, by);
            deadline.setIsDone(isDone);
            return deadline;

        } else if (taskDescription.startsWith("[E]")) {
            taskDescription = taskDescription.substring(3).trim();
            if (taskDescription.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            int fromIndex = taskDescription.indexOf("(from:");
            int toIndex = taskDescription.indexOf("to:");
            int closingBracketIndex = taskDescription.indexOf(")");

            if (fromIndex == -1 || toIndex == -1 || closingBracketIndex == -1) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            String taskName = taskDescription.substring(0, fromIndex).trim();
            String from = taskDescription.substring(fromIndex + 6, toIndex).trim();
            String to = taskDescription.substring(toIndex + 3, closingBracketIndex).trim();
            String isDoneEmoji = taskDescription.substring(closingBracketIndex + 1).trim();

            if (isDoneEmoji.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }
            boolean isDone = validateIsDoneEmoji(isDoneEmoji);

            if (taskName.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
            }

            Event event = new Event(taskName, from, to);
            event.setIsDone(isDone);
            return event;

        } else {
            throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
        }
    }

    private static boolean validateIsDoneEmoji(String isDoneEmoji) throws InvalidInputException {
        if (isDoneEmoji.equals(emoji.getTickEmoji())) {
            return true;
        } else if (isDoneEmoji.equals(emoji.getHourglassEmoji())) {
            return false;
        } else {
            throw new InvalidInputException("ERROR: Rizzler.txt is corrupted" + emoji.getExclamationMarkEmoji());
        }
    }
}
