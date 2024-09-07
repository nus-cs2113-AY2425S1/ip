public class TaskList {
    private final Task[] tasks;
    private int size;

    public TaskList(int capacity) {
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    public void printList() {
        PrintUtils.lineBreak();
        for (int i = 0; i < size; i++) {
            PrintUtils.print((i + 1) + ".");
            PrintUtils.println(tasks[i].toString());
        }
        PrintUtils.lineBreak();
    }

    public void addTask(Task task) {
        PrintUtils.lineBreak();
        tasks[size++] = task;
        PrintUtils.println("Got it. I've added this task:");
        PrintUtils.println(task.toString());
        PrintUtils.println("Now you have " + size + " tasks in the list.");
        PrintUtils.lineBreak();
    }

    public void markTaskAsDone(String argument)  {
        setTaskStatus(argument, true);
    }

    public void markTaskAsNotDone(String argument) {
        setTaskStatus(argument, false);
    }

    private void setTaskStatus(String argument, boolean isDone) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            PrintUtils.lineBreak();
            PrintUtils.println("Wow, " + argument + " is not even a number.");
            PrintUtils.lineBreak();
            return;
        }

        try {
            if (isDone) {
                tasks[taskNumber - 1].markDone();
                PrintUtils.lineBreak();
                PrintUtils.println("Nice! I've marked this task as done:");
            } else {
                tasks[taskNumber - 1].markNotDone();
                PrintUtils.lineBreak();
                PrintUtils.println("I've unmarked this task:");
            }
            PrintUtils.println(tasks[taskNumber - 1].toString());
            PrintUtils.lineBreak();
        } catch (NullPointerException e) {
            PrintUtils.lineBreak();
            PrintUtils.println("What. There is no task " + taskNumber + ". "
                    + "Try a number between 1 and " + this.size + ".");
            PrintUtils.lineBreak();
        }
    }
}
