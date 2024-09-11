public class TaskList {
    private final Task[] list;
    private int size;

    public TaskList() {
        list = new Task[100]; // Constraint given that maximum number of tasks is 100
        size = 0;
    }

    public void addTask(Task newTask) {
        System.out.println("I've added this to your list: ");
        newTask.printTask();
        list[size] = newTask;
        size++;
    }

    public void printTaskList() {
        System.out.println("Here is your list of tasks:");
        for (int i = 1; i <= size; i++) {
            System.out.printf("%d. ", i);
            list[i - 1].printTask();
        }
    }

    public void attemptToMarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list[index - 1].markAsDone();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToUnmarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list[index - 1].markAsNotDone();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }
}
