public class Task {
    final static String MARK_COMMAND_STRING = "mark";
    final static String UNMARK_COMMAND_STRING = "unmark";
    final static String LIST_COMMAND_STRING = "list";

    protected String description; // Description of the task
    private boolean isDone;     // Status of the task

    private static int numberOfTasks = 0; // Number of tasks in the list

    /**
     * Constructor for the Task class.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;

        numberOfTasks++;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the description of the task.
     *
     * @param newDescription
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDoneState(boolean bool) {
        this.isDone = bool;
    }

    public boolean getDoneState() {
        return this.isDone;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "✓" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;

        System.out.println("Nice! I've marked this task as done: \n  " + toString());

    }

    public static void markAsDone(Task[] taskList, int taskNumber) {
        taskList[taskNumber - 1].markAsDone(); // Mark the task as done
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;

        System.out.print("OK, I've marked this task as not done yet: \n  ");
        this.toString();
    }

    public static void markAsUndone(Task[] taskList, int taskNumber) {
        taskList[taskNumber - 1].markAsUndone(); // Mark the task as not done
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return number of tasks in the list
     */
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    public static void printNumberOfTasks() {
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Prints the task to the console.
     *
     * @return task to be printed
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public static void printAllTasks(Task[] taskList) {
        if (getNumberOfTasks() == 0) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                System.out.println((i + 1) + "." + taskList[i].toString());
            }
        }
    }
}
