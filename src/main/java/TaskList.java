import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskList class manages a list of tasks, including adding, deleting, and marking tasks.
 * It supports three types of tasks: ToDo, Deadline, and Event.
 */
public class TaskList {

    public static final int MAX_TASKS = 100;

    /**
     * The Task class represents a general task with a description, status (done or not done), and task number.
     */
    public static class Task {
        protected String description;
        protected boolean isDone;
        protected int taskNum;

        /**
         * Constructs a new Task.
         *
         * @param description the description of the task
         * @param taskNum the task number
         */
        public Task(String description, int taskNum) {
            this.description = description;
            this.isDone = false;
            this.taskNum = taskNum;
        }

        /**
         * Returns the status icon for the task.
         *
         * @return "[X]" if the task is done, "[ ]" if it is not done
         */
        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        /**
         * Returns the string representation of the task.
         *
         * @return a formatted string showing the status icon and task description
         */
        @Override
        public String toString() {
            return tasks[taskNum].getStatusIcon() + " " + tasks[taskNum].description;
        }
    }

    /**
     * The Deadline class represents a task with a deadline.
     */
    public static class Deadline extends TaskList.Task {
        protected String by;

        /**
         * Constructs a new Deadline task.
         *
         * @param description the description of the deadline task
         * @param taskNum the task number
         * @param by the deadline date/time
         */
        private Deadline(String description, int taskNum, String by) {
            super(description, taskNum);
            this.by = by;
        }

        /**
         * Returns the string representation of the Deadline task.
         *
         * @return a formatted string showing task details and deadline
         */
        @Override
        public String toString() {
            return (this.taskNum + 1) + ".[D]" + super.toString() + " BY: " + by;
        }
    }

    /**
     * The ToDo class represents a task without any date or time constraint.
     */
    public static class ToDo extends TaskList.Task {

        /**
         * Constructs a new ToDo task.
         *
         * @param description the description of the to-do task
         * @param taskNum the task number
         */
        private ToDo(String description, int taskNum) {
            super(description, taskNum);
        }

        /**
         * Returns the string representation of the ToDo task.
         *
         * @return a formatted string showing task details
         */
        @Override
        public String toString() {
            return (this.taskNum + 1) + ".[T]" + super.toString();
        }
    }

    /**
     * The Event class represents a task that spans a time period.
     */
    public static class Event extends TaskList.Task {
        protected String from;
        protected String to;

        /**
         * Constructs a new Event task.
         *
         * @param description the description of the event task
         * @param taskNum the task number
         * @param from the start time of the event
         * @param to the end time of the event
         */
        private Event(String description, int taskNum, String from, String to) {
            super(description, taskNum);
            this.from = from;
            this.to = to;
        }

        /**
         * Returns the string representation of the Event task.
         *
         * @return a formatted string showing task details and event time period
         */
        @Override
        public String toString() {
            return (this.taskNum + 1) + ".[E]" + super.toString() + " FROM: " + from + " TO: " + to;
        }
    }

    public static int listCount = 0;

    public static TaskList.Task[] tasks;

    /**
     * Initializes the task list, sets up storage, and prints the initial user interface entry.
     *
     * @throws IOException if an I/O error occurs during initialization
     */
    public static void init() throws IOException {
        Ui.printEntry();
        tasks = new TaskList.Task[MAX_TASKS];
        Storage.init();
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param taskDesc the task description containing both the description and deadline
     */
    public static void addDeadline(String taskDesc) {
        try {
            String[] words = taskDesc.split(" /by ");
            TaskList.Task newTask = new TaskList.Deadline(words[0].substring(9), listCount, words[1]);
            tasks[listCount] = newTask;
            listCount += 1;
            Ui.printAddConfirm(newTask);
        } catch (ArrayIndexOutOfBoundsException|StringIndexOutOfBoundsException|IOException e) {
            Ui.printInvalidDeadline();
        }
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param taskDesc the description of the to-do task
     */
    public static void addTodo(String taskDesc) {
        try {
            if (taskDesc.substring(5).isEmpty()) {
                Ui.printInvalidTodo();
            } else {
                TaskList.Task newTask = new TaskList.ToDo(taskDesc.substring(5), listCount);
                tasks[listCount] = newTask;
                listCount += 1;
                Ui.printAddConfirm(newTask);
            }
        } catch (StringIndexOutOfBoundsException|IOException e) {
            Ui.printInvalidTodo();
        }
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param taskDesc the description of the event task containing both the description, start, and end times
     */
    public static void addEvent(String taskDesc) {
        String[] words = taskDesc.split(" /from | /to ");
        try {
            TaskList.Task newTask = new TaskList.Event(words[0].substring(6), listCount, words[1], words[2]);
            tasks[listCount] = newTask;
            listCount += 1;
            Ui.printAddConfirm(newTask);
        } catch (ArrayIndexOutOfBoundsException|StringIndexOutOfBoundsException|IOException e) {
            Ui.printInvalidEvent();
        }
    }

    /**
     * Deletes a task from the task list based on the command input.
     *
     * @param command the command string specifying the task to delete
     * @throws IOException if an I/O error occurs during deletion
     */
    public static void deleteTask(String command) throws IOException {
        try {
            String numberString = command.split(" ")[1];
            int toBeDeleted;
            toBeDeleted = Integer.parseInt(numberString) - 1;
            if (listCount == 0) {
                Ui.printEmptyList();
            } else if (toBeDeleted >= listCount) {
                Ui.printNonExistentTask();
            } else {
                Ui.printDeleteConfirm(tasks[toBeDeleted]);
                while (toBeDeleted < listCount - 1) {
                    tasks[toBeDeleted] = tasks[toBeDeleted + 1];
                    tasks[toBeDeleted].taskNum -= 1;
                    toBeDeleted += 1;
                }
                listCount -= 1;
            }
        } catch (NumberFormatException|ArrayIndexOutOfBoundsException|IOException e) {
            Ui.printInvalidDelete();
        }
        Storage.clear();
        Storage.save();
    }

    /**
     * Lists all the tasks in the task list.
     */
    public static void list() {
        if (listCount == 0) {
            Ui.printEmptyList();
        } else {
            Ui.printSeparator();
            String toBePrinted = "The list has " + listCount + " task";
            if (listCount == 1) {
                toBePrinted += ".";
            } else {
                toBePrinted += "s.";
            }
            System.out.println(toBePrinted);
            for (int i = 0; i < listCount; i += 1) {
                System.out.println(tasks[i].toString());
            }
            Ui.printSeparator();
        }
    }

    /**
     * Marks a task as done.
     *
     * @param toBeMarked the task number to be marked as done
     * @throws IOException if an I/O error occurs during marking
     */
    public static void mark(int toBeMarked) throws IOException {
        if (tasks[toBeMarked].isDone) {
            Ui.printAlreadyMarked();
        } else {
            tasks[toBeMarked].isDone = true;
            Ui.printMarkConfirm(tasks[toBeMarked]);
        }
    }

    /**
     * Unmarks a task as not done.
     *
     * @param toBeUnmarked the task number to be unmarked
     * @throws IOException if an I/O

    error occurs during unmarking
     */
    public static void unmark(int toBeUnmarked) throws IOException {
        if (!tasks[toBeUnmarked].isDone) {
            Ui.printAlreadyUnmarked();
        } else {
            tasks[toBeUnmarked].isDone = false;
            Ui.printUnmarkConfirm(tasks[toBeUnmarked]);
        }
    }

    /**
     * Adds a task from a previously saved task list.
     *
     * @param taskType the type of task ("T" for ToDo, "D" for Deadline, "E" for Event)
     * @param taskStatus the status of the task ("X" if the task is done, " " if not)
     * @param taskDesc the description of the task
     */
    public static void addTaskFromSave(String taskType, String taskStatus, String taskDesc) {
        switch (taskType) {
        case "T" -> {
            TaskList.Task newTask = new TaskList.ToDo(taskDesc, listCount);
            tasks[listCount] = newTask;
        }
        case "D" -> {
            String[] words = taskDesc.split(" /by ");
            TaskList.Task newTask = new TaskList.Deadline(words[0], listCount, words[1]);
            tasks[listCount] = newTask;
        }
        case "E" -> {
            String[] words = taskDesc.split(" /from | /to ");
            TaskList.Task newTask = new TaskList.Event(words[0], listCount, words[1], words[2]);
            tasks[listCount] = newTask;
        }
        }
        listCount += 1;
        if (taskStatus.equals("X")) {
            tasks[listCount - 1].isDone = true;
        }
    }

    public static void find(String keyword) {
        try {
            keyword = keyword.substring(5);
            ArrayList<Integer> matchingTaskIndices = new ArrayList<>();
            for (int i = 0; i < listCount; i += 1) {
                if (tasks[i].description.contains(keyword)) {
                    matchingTaskIndices.add(i);
                }
            }
            if (matchingTaskIndices.isEmpty()) {
                Ui.printNoTaskFound();
            } else {
                Ui.printSeparator();
                System.out.println("The following tasks match:");
                for (int i : matchingTaskIndices) {
                    System.out.println(tasks[i].toString());
                }
                Ui.printSeparator();
            }
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printInvalidEvent();
        }
    }

}