public class List {
    private final Task[] tasks;
    private int size;

    public List(int capacity) {
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

    public void addTask(String userInput) {
        PrintUtils.lineBreak();
        tasks[size++] = new Task(userInput);
        PrintUtils.println("Ok, I've added: " + userInput);
        PrintUtils.lineBreak();
    }

    public void markTaskAsDone(int taskNumber)  {
        setTaskStatus(taskNumber, true);
    }

    public void markTaskAsNotDone(int taskNumber) {
        setTaskStatus(taskNumber, false);
    }

    public void setTaskStatus(int taskNumber, boolean isDone) {
        if (taskNumber < 1 || taskNumber > size) {
            PrintUtils.lineBreak();
            PrintUtils.println("Sorry, there is no task " + taskNumber + "."
            + "Try a number between 1 and " + size + ".");
            PrintUtils.lineBreak();
            return;
        }

        PrintUtils.lineBreak();
        if (isDone) {
            tasks[taskNumber - 1].markDone();
            PrintUtils.println("Nice! I've marked this task as done:");
        } else {
            tasks[taskNumber - 1].markNotDone();
            PrintUtils.println("I've unmarked this task:");
        }
        PrintUtils.println(tasks[taskNumber - 1].toString());
        PrintUtils.lineBreak();
    }
}
