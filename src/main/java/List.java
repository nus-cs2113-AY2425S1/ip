public class List {
    private final Task[] tasks;
    private int size;

    public List(int capacity) {
        this.tasks = new Task[capacity];
        this.size = 0;
    }

    public void printList() {
        PrintUtils.line();
        for (int i = 0; i < size; i++) {
            PrintUtils.print((i + 1) + ".");
            PrintUtils.print("[" + tasks[i].getStatusIcon() + "] ");
            tasks[i].printTask();
        }
        PrintUtils.line();
    }

    public void addTask(String userInput) {
        PrintUtils.line();
        tasks[size++] = new Task(userInput);
        PrintUtils.println("added: " + userInput);
        PrintUtils.line();
    }

    public void markTaskAsDone(int taskNumber)  {
        setTaskStatus(taskNumber, true);
    }

    public void markTaskAsNotDone(int taskNumber) {
        setTaskStatus(taskNumber, false);
    }

    public void setTaskStatus(int taskNumber, boolean isDone) {
        PrintUtils.line();
        if (taskNumber <= 0 || taskNumber >= size) {
            PrintUtils.print("It looks like you entered an invalid task number. " +
                    "Would you like to try again? Please enter a number between " +
                    "1 and " + size + ".");
        } else {
            if (isDone) {
                tasks[taskNumber - 1].markDone();
                PrintUtils.println("Nice! I've marked this task as done:");
            } else {
                tasks[taskNumber - 1].markNotDone();
                PrintUtils.println("Unmarked this task:");
            }
            PrintUtils.print("[" + tasks[taskNumber - 1].getStatusIcon() + "] ");
            tasks[taskNumber - 1].printTask();
        }
        PrintUtils.line();
    }
}
