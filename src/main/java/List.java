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
            if (tasks[i].isDone()) {
                PrintUtils.print("[X] ");
            } else {
                PrintUtils.print("[ ] ");
            }
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

    public void markTaskAsDone(int taskNumber) {
        PrintUtils.line();
        if (taskNumber < 0 || taskNumber >= tasks.length) {
            PrintUtils.print("Invalid task number: " + taskNumber);
        } else {
            tasks[taskNumber - 1].markDone();
            PrintUtils.println("Nice! I've marked this task as done:");
            PrintUtils.print("[X] ");
            tasks[taskNumber - 1].printTask();
        }
        PrintUtils.line();
    }

    public void markTaskAsNotDone(int taskNumber) {
        PrintUtils.line();
        if (taskNumber < 0 || taskNumber >= tasks.length) {
            PrintUtils.print("Invalid task number: " + taskNumber);
        } else {
            tasks[taskNumber - 1].markNotDone();
            PrintUtils.print("[ ] ");
            tasks[taskNumber - 1].printTask();
        }
        PrintUtils.line();
    }
}
