public class TaskManager {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    public static final int MAX_TASKS = 100;

    private Task[] tasks;
    private int count;

    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        count = 0;
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void markTask(String line) {

        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].setMarkAsDone();

        System.out.println("Great! This task is marked as done: ");
        System.out.println(tasks[taskNumber]);
        System.out.println("Well done! ;)");
        System.out.println(HORIZONTAL_LINE);
    }

    public void unmarkTask(String line) {

        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].setMarkAsNotDone();

        System.out.println("Ok, This task is marked as not done yet: ");
        System.out.println(tasks[taskNumber]);
        System.out.println(HORIZONTAL_LINE);
    }

    public static int extractDigit(String input) {
        String numberString = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberString);
    }

    public void printTodo(String line) {

        String todoDesc = line.replaceFirst("todo", "").trim();

        tasks[count] = new Todo(todoDesc);

        System.out.println("Okay, I've added this todo: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);

        count++;
    }

    public void printDeadline(String line) {

        String[] parts = line.replaceFirst("deadline", "").split("/by");
        String description = parts[0].trim();
        String by = parts[1].trim();

        tasks[count] = new Deadline(description, by);

        System.out.println("Okay, I've added this deadline: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);

        count++;
    }

    public void printEvent(String line) {

        String[] eventParts = line.replaceFirst("event", "").split("/from|/to");
        String eventDesc = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();

        tasks[count] = new Event(eventDesc, from, to);

        System.out.println("Okay, I've added this event: ");
        System.out.println(tasks[count]);
        printNumTasks(tasks, count);
        System.out.println(HORIZONTAL_LINE);

        count++;
    }

    public void printNumTasks(Task[] tasks, int count) {
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
    }
}
