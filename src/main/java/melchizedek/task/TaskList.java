package melchizedek.task;

import melchizedek.Parser;
import melchizedek.Ui;
import melchizedek.exceptions.InvalidTaskNumberException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> allTasks;
    private static final int INVALID_INDEX = -1;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public int getTaskCount() {
        return allTasks.size();
    }

    public String getTaskToString(int index) {
        return allTasks.get(index).toString();
    }

    public void markTaskAsDone(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }

        if (allTasks.get(id - 1).isMarkAsDone()) {
            Ui.printTaskHasAlreadyBeenMarked();
        } else {
            allTasks.get(id - 1).markTaskAsDone();
            Ui.printTaskMarkedAsDone();
        }
        Ui.printTask(getTaskToString(id - 1));
    }

    public void unmarkTaskAsDone(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }
        if (!allTasks.get(id - 1).isMarkAsDone()) {
            Ui.printTaskHasAlreadyBeenUnmarked();
        } else {
            allTasks.get(id - 1).unmarkTaskAsDone();
            Ui.printTaskUnmarkedAsDone();
        }
        Ui.printTask(getTaskToString(id - 1));
    }

    public void addTodo(String[] tokens) {
        String description = Parser.joinStringArray(tokens, " ");

        allTasks.add(new Todo(description));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    public void addDeadline(String[] tokens) {
        int byIndex = INVALID_INDEX;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byIndex = i;
                break;
            }
        }

        String description = null;
        String by = null;
        try {
            description = Parser.joinStringArray(tokens, 0, byIndex, " ");
            by = Parser.joinStringArray(tokens, byIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            if (byIndex == INVALID_INDEX) {
                Ui.printUnableToProcessWithoutKey("\"/by\"");
                Ui.printDeadlineExample();
                return;
            }
        }

        allTasks.add(new Deadline(description, by));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    public void addEvent(String[] tokens) {
        int fromIndex = INVALID_INDEX;
        int toIndex = INVALID_INDEX;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/from")) {
                fromIndex = i;
            } else if (tokens[i].equalsIgnoreCase("/to")) {
                toIndex = i;
                break;
            }
        }

        String description = null;
        String from = null;
        String to = null;
        try {
            description = Parser.joinStringArray(tokens, 0, fromIndex, " ");
            from = Parser.joinStringArray(tokens, fromIndex + 1, toIndex, " ");
            to = Parser.joinStringArray(tokens, toIndex + 1, tokens.length, " ");
        } catch (IllegalArgumentException e) {
            if (fromIndex == INVALID_INDEX && toIndex == INVALID_INDEX) {
                Ui.printUnableToProcessWithoutKey("\"/from\" and \"/to\"");
            }
            else if (fromIndex == INVALID_INDEX) {
                Ui.printUnableToProcessWithoutKey("\"/from\"");
            } else if (toIndex == INVALID_INDEX) {
                Ui.printUnableToProcessWithoutKey("\"/to\"");
            }
            Ui.printEventExample();
            return;
        }

        allTasks.add(new Event(description, from, to));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    public void deleteTask(int id) throws InvalidTaskNumberException {
        if (id > allTasks.size() || id < 1) {
            throw new InvalidTaskNumberException();
        }

        String taskString = allTasks.get(id - 1).toString();
        allTasks.remove(id - 1);

        Ui.printDeletedTask(taskString);
        Ui.printNumberOfTasks(allTasks.size());
    }

    public void findKeyword(String[] tokens) {
        String keyword = Parser.joinStringArray(tokens, " ");
        ArrayList<String> filteredTaskList = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.containsKeyword(keyword)) {
                filteredTaskList.add(task.toString());
            }
        }

        Ui.printFilteredTaskList(filteredTaskList);
    }

    public void loadTodo(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        allTasks.add(new Todo(description, isDone));
    }

    public void loadDeadline(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        String by  = tokens[2];
        allTasks.add(new Deadline(description, isDone, by));
    }

    public void loadEvent(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        String from  = tokens[2];
        String to = tokens[3];
        allTasks.add(new Event(description, isDone, from, to));
    }

    public String taskListToFile() {
        String output = "";
        for (Task task : allTasks) {
            output = output + task.taskToFile() + System.lineSeparator();
        }
        return output;
    }
}
