package melchizedek.task;

import melchizedek.Parser;
import melchizedek.Ui;
import melchizedek.exceptions.InvalidTaskNumberException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class that handles the task list, including adding and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> allTasks;
    private static final int INVALID_INDEX = -1;

    /**
     * Constructor to create an instance of TaskList.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    /**
     * Method to get number of tasks in the task list.
     *
     * @return Size of the task list.
     */
    public int getTaskCount() {
        return allTasks.size();
    }

    /**
     * Method to get the printable string of a task from its index in the task list.
     *
     * @param index Index of task in the task list
     * @return Printable String of task
     */
    public String getTaskToString(int index) {
        return allTasks.get(index).toString();
    }

    /**
     * Method to mark a task as done, and print out a confirmation of the task being marked.
     *
     * @param index Index of task in the task list
     * @throws InvalidTaskNumberException
     */
    public void markTaskAsDone(int index) throws InvalidTaskNumberException {
        if (index > allTasks.size() || index < 1) {
            throw new InvalidTaskNumberException();
        }

        if (allTasks.get(index - 1).getMarkAsDone()) {
            Ui.printTaskHasAlreadyBeenMarked();
        } else {
            allTasks.get(index - 1).markTaskAsDone();
            Ui.printTaskMarkedAsDone();
        }
        Ui.printTask(getTaskToString(index - 1));
    }

    /**
     * Method to unmark a task as done, and print out a confirmation of the task being unmarked.
     *
     * @param index Index of task in the task list
     * @throws InvalidTaskNumberException
     */
    public void unmarkTaskAsDone(int index) throws InvalidTaskNumberException {
        if (index > allTasks.size() || index < 1) {
            throw new InvalidTaskNumberException();
        }
        if (!allTasks.get(index - 1).getMarkAsDone()) {
            Ui.printTaskHasAlreadyBeenUnmarked();
        } else {
            allTasks.get(index - 1).unmarkTaskAsDone();
            Ui.printTaskUnmarkedAsDone();
        }
        Ui.printTask(getTaskToString(index - 1));
    }

    /**
     * Method to add a todo to the task list, and print out a confirmation.
     *
     * @param tokens User input
     */
    public void addTodo(String[] tokens) {
        String description = Parser.joinStringArray(tokens, " ");

        allTasks.add(new Todo(description, false));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    /**
     * Method to add a deadline to the task list, and print out a confirmation.
     *
     * @param tokens User input
     */
    public void addDeadline(String[] tokens) {
        int byIndex = INVALID_INDEX;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equalsIgnoreCase("/by")) {
                byIndex = i;
                break;
            }
        }

        String description = null;
        LocalDate byDate = null;
        LocalTime byTime = null;
        try {
            description = Parser.joinStringArray(tokens, 0, byIndex, " ");
            byDate = Parser.parseDate(tokens[byIndex + 1]);
            if (tokens.length > byIndex + 2) {
                byTime = Parser.parseTime(Parser.joinStringArray(tokens, byIndex + 2, tokens.length, " "));
            }
        } catch (IllegalArgumentException e) {
            if (byIndex == INVALID_INDEX) {
                Ui.printUnableToProcessWithoutKey("\"/by\"");
                Ui.printDeadlineExample();
                return;
            }
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseException();
            return;
        }

        allTasks.add(new Deadline(description, false, byDate, byTime));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    /**
     * Method to add an event to the task list, and print out a confirmation.
     *
     * @param tokens User input
     */
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

        String description;
        LocalDate fromDate;
        LocalTime fromTime = null;
        LocalDate toDate;
        LocalTime toTime = null;
        try {
            description = Parser.joinStringArray(tokens, 0, fromIndex, " ");
            fromDate = Parser.parseDate(tokens[fromIndex + 1]);
            toDate = Parser.parseDate(tokens[toIndex + 1]);
            if (toIndex > fromIndex + 2) {
                fromTime = Parser.parseTime(Parser.joinStringArray(tokens, fromIndex + 2, toIndex, " "));
            }
            if (tokens.length > toIndex + 2) {
                toTime = Parser.parseTime(Parser.joinStringArray(tokens, toIndex + 2, tokens.length, " "));
            }
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
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseException();
            return;
        }

        allTasks.add(new Event(description, false, fromDate, fromTime, toDate, toTime));
        int taskCount = getTaskCount();
        Ui.printAddedTask(getTaskToString(taskCount - 1), taskCount);
    }

    /**
     * Method to delete a task from the task list, and print out a confirmation.
     *
     * @param index Index of task in the task list
     * @throws InvalidTaskNumberException
     */
    public void deleteTask(int index) throws InvalidTaskNumberException {
        if (index > allTasks.size() || index < 1) {
            throw new InvalidTaskNumberException();
        }

        String taskString = allTasks.get(index - 1).toString();
        allTasks.remove(index - 1);

        Ui.printDeletedTask(taskString);
        Ui.printNumberOfTasks(allTasks.size());
    }

    /**
     * Method to find tasks that contain the keyword,
     * and prints out the list of all those tasks.
     *
     * @param tokens User input
     */
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

    /**
     * Method to load a todo from the save file.
     *
     * @param tokens File input
     */
    public void loadTodo(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        allTasks.add(new Todo(description, isDone));
    }

    /**
     * Method to load a deadline from the save file.
     *
     * @param tokens File input
     */
    public void loadDeadline(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        LocalDate byDate;
        LocalTime byTime = null;
        try {
            byDate  = Parser.parseDate(tokens[2]);
            if (tokens.length > 3) {
                byTime = Parser.parseTime(tokens[3]);
            }
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseException();
            return;
        }

        allTasks.add(new Deadline(description, isDone, byDate, byTime));
    }

    /**
     * Method to load an event from the save file.
     *
     * @param tokens User input
     */
    public void loadEvent(String[] tokens) {
        boolean isDone = tokens[0].equals("1");
        String description = tokens[1];
        LocalDate fromDate;
        LocalTime fromTime = null;
        LocalDate toDate;
        LocalTime toTime = null;
        try {
            fromDate  = Parser.parseDate(tokens[2]);
            if (!tokens[3].equalsIgnoreCase("null")) {
                fromTime = Parser.parseTime(tokens[3]);
            }
            toDate  = Parser.parseDate(tokens[4]);
            if (tokens.length > 5) {
                toTime = Parser.parseTime(tokens[5]);
            }
        } catch (DateTimeParseException e) {
            Ui.printDateTimeParseException();
            return;
        }
        allTasks.add(new Event(description, isDone, fromDate, fromTime, toDate, toTime));
    }

    /**
     * Method to convert task list to the save file format.
     *
     * @return The full formatted task list
     */
    public String taskListToFile() {
        String output = "";
        for (Task task : allTasks) {
            output = output + task.taskToFile() + System.lineSeparator();
        }
        return output;
    }
}
