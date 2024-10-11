import java.util.ArrayList;

/**
 * Store and handle Tasks of the ChatBot
 */
public class TaskList {
    private static ArrayList<Task> tasks;
    private static Ui ui;
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String DELETE = "delete";

    public TaskList(Ui userInterface) {
        tasks = new ArrayList<>();
        ui = userInterface;
    }

    /**
     * create a todo task with user specified description. Then, add into taskList
     *
     * @param line contains user command
     */
    public void createTodo(String line) {
        try{
            int beginIndex = 5;
            String taskName = getTaskName(line, beginIndex, -2, "todo");
            Task task = new Todo(taskName);
            addTask(task);
        } catch (EmptyTaskException e) {
            ui.printEmptyTaskException();
        } catch (EmptyByOrFromException ignored) {
        }
    }

    /**
     * create a deadline task with user specified description and deadline time. Then, add into taskList
     *
     * @param line contains user command
     */
    public void createDeadline(String line) {
        try {
            int beginIndex = 9;
            int byIndex = line.indexOf("/by");
            String taskName = getTaskName(line, beginIndex, byIndex, "deadline");
            Task task = new Deadline(taskName, line.substring(byIndex + 4));
            addTask(task);
        } catch (EmptyTaskException e) {
            ui.printEmptyTaskException();
        } catch (EmptyByOrFromException e) {
            ui.printEmptyByOrFromException("deadline", "by");
        }
    }

    /**
     * create a event task with user specified description and event duration. Then, add into taskList
     *
     * @param line contains user command
     */
    public void createEvent(String line) {
        try {
            int beginIndex = 6;
            int fromIndex = line.indexOf("/from");
            int toIndex = line.indexOf("/to");
            String taskName = getTaskName(line, beginIndex, fromIndex, "event");
            String from = getFrom(line, fromIndex, toIndex);
            Task task = new Event(taskName, from, line.substring(toIndex + 4));
            addTask(task);
        } catch (EmptyTaskException e) {
            ui.printEmptyTaskException();
        } catch (EmptyByOrFromException e) {
            ui.printEmptyByOrFromException("event", "from");
        } catch (EmptyToException e) {
            ui.printEmptyToException();
        }
    }

    /**
     * mark a user specified task in taskList as done
     *
     * @param line contains user command
     */
    public static void mark(String line) {
        try {
            int beginIndex = 5;
            int index = getIndex(tasks, line, beginIndex, "mark");
            markTask(index);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException(MARK);
        } catch (IndexOutOfRangeException e) {
            ui.printIndexOutOfRangeException();
        } catch (TaskSameStateException e) {
            ui.TaskSameStateException(MARK);
        }
    }

    /**
     * unmark a user specified task in taskList as not done yet
     *
     * @param line contains user command
     */
    public static void unmark(String line) {
        try {
            int beginIndex = 7;
            int index = getIndex(tasks, line, beginIndex, "unmark");
            unmarkTask(index);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException(UNMARK);
        } catch (IndexOutOfRangeException e) {
            ui.printIndexOutOfRangeException();
        } catch (TaskSameStateException e) {
            ui.TaskSameStateException(UNMARK);
        }
    }

    /**
     * helper function to unmark a task as not done yet
     *
     * @param index of task in taskList to be unmark as not done yet
     */
    public static void unmarkTask(int index) {
        tasks.get(index - 1).setDone(false);
        ui.printUnmarkTask(tasks, index);
    }

    /**
     * helper function to mark a task as done
     *
     * @param index of task in taskList to be marked as done
     */
    public static void markTask(int index) {
        tasks.get(index - 1).setDone(true);
        ui.printMarkTask(tasks, index);
    }

    /**
     * delete a user specified task in taskList
     *
     * @param line contains user command
     */
    public static void deleteTask(String line) {
        try {
            int index = getIndex(tasks, line, 7, "delete");
            ui.printDeleteTask(tasks, index);
            tasks.remove(index - 1);
            Storage.decrementCount();
        } catch (NumberFormatException e) {
            ui.printNumberFormatException(DELETE);
        } catch (IndexOutOfRangeException e) {
            ui.printIndexOutOfRangeException();
        } catch (TaskSameStateException ignore) { // won't fall into this case
            ui.printError();
        }
    }

    /**
     * find tasks in taskList with description containing user specified substring
     *
     * @param line contains user command
     */
    public static void find(String line) {
        line = line.substring(5);
        ui.printFind();
        for (int i = 0; i < Storage.getCount(); i++) {
            if (getTask(i).toString().contains(line)) {
                ui.printFindTask(i);
            }
        }
    }

    /**
     * helper function to get index of task to be mark or unmark
     *
     * @param tasks is tasksList containing all tasks
     * @param line contains user command
     * @param beginIndex starting index of the description
     * @param func command calling this function, can be mark or unmark
     * @throws NumberFormatException if user input cannot be converted to an index
     * @throws IndexOutOfRangeException if user specified index is not in range of taskList
     * @throws TaskSameStateException if the task has same state(marked, unmarked) already
     */
    private static int getIndex(ArrayList<Task> tasks, String line, int beginIndex, String func)
            throws NumberFormatException, IndexOutOfRangeException, TaskSameStateException {
        try {
            int index = Integer.parseInt(line.substring(beginIndex));
            String state;
            if (index <= 0 || index > Storage.getCount()) {
                throw new IndexOutOfRangeException();
            }
            if (func == "mark") {
                state = "X";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
            } else if (func == "unmark"){
                state = " ";
                if (tasks.get(index - 1).getStatusIcon() == state) {
                    throw new TaskSameStateException();
                }
            }
            return index;
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    /**
     * helper function to get the starting time of event
     *
     * @param line contains user command
     * @param fromIndex starting index of the starting time of the event
     * @param toIndex starting index of the ending time of the event
     * @throws EmptyToException if ending time is empty
     * @throws EmptyByOrFromException if starting time is empty
     */
    private static String getFrom(String line, int fromIndex, int toIndex) throws EmptyToException, EmptyByOrFromException {
        if (fromIndex + 6 >= toIndex && toIndex != -1) {
            throw new EmptyByOrFromException();
        } else if (toIndex == -1 || toIndex + 4 > line.length() - 1) {
            throw new EmptyToException();
        } else {
            return line.substring(fromIndex + 6, toIndex).trim();
        }
    }

    /**
     * helper function to add task to taskList
     *
     * @param task to be added
     */
    private static void addTask(Task task) {
        tasks.add(task);
        Ui.showAddTask();
    }

    /**
     * get task with index in taskList
     *
     * @param index of task in taskList
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * helper function to get the task description
     *
     * @param line contains user command
     * @param beginIndex starting index of the task description
     * @param endIndex ending index of the task description (if whole substring starting from beginIndex is included, then enIndex = -2)
     * @throws EmptyByOrFromException if task is of type deadline or event but with empty deadline time or event starting time
     * @throws EmptyTaskException if task description is empty
     */
    private static String getTaskName(String line, int beginIndex, int endIndex, String command) throws EmptyByOrFromException, EmptyTaskException {
        if (endIndex == -1) {
            throw new EmptyByOrFromException();
        } else if (endIndex == -2 && beginIndex + 1 > line.length()) {
            throw new EmptyTaskException();
        } else if (endIndex == -2 && line.substring(beginIndex).trim() != "") {
            return line.substring(beginIndex).trim();
        } else {
            String trimName = line.substring(beginIndex, endIndex).trim();
            String ByOrFrom = line.substring(endIndex).trim();
            if (ByOrFrom.equals("/by") && command.equals("deadline")) {
                throw new EmptyByOrFromException();
            }
            if (ByOrFrom.equals("/from") && command.equals("event")) {
                throw new EmptyByOrFromException();
            }
            if (trimName != "") {
                return trimName;
            } else {
                throw new EmptyTaskException();
            }
        }
    }

}
