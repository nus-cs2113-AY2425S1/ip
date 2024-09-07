import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * November is a command-line chatbot that manages tasks.
 */
public class November {

    private static final String SEPARATOR = "____________________________________________________________";
    private static final String EXIT_COMMAND = "bye";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE = "/by ";
    private static final String EVENT_COMMAND = "event";
    private static final String EVENT_START = "/from ";
    private static final String EVENT_END = "/to ";

    private static final String INIT_SENTENCE = "Hello! I'm November." + System.lineSeparator()
            + "What can I do for you?";
    private static final String EXIT_SENTENCE = "Bye! Hope to see you again soon!";
    private static final String LIST_SENTENCE = "Here are the tasks in your list:";
    private static final String MARK_SENTENCE = "Nice! I've marked this task as done: ";
    private static final String UNMARK_SENTENCE = "Ok, I've marked this task as not done yet: ";

    /**
     * Prints a line of underscores to mark the start of a print segment.
     */
    public static void beginSegment() {
        System.out.println(SEPARATOR);
    }

    /**
     * Prints a line of underscores followed by a newline to mark the end of a print segment.
     */
    public static void endSegment() {
        System.out.println(SEPARATOR + System.lineSeparator());
    }

    /**
     * Prints a numbered list of tasks with their completion status.
     *
     * @param taskList The list of tasks to print, with each task displaying its completion status.
     */
    public static void printTaskList(List<Task> taskList) {
        int index = 0;
        System.out.println(LIST_SENTENCE);
        while (index < taskList.size()) {
            System.out.print(index + 1 + ".");
            printTask(taskList.get(index));
            index++;
        }
    }

    /**
     * Prints the details of a single task.
     *
     * @param task The task to print.
     */
    public static void printTask(Task task) {
        System.out.println("[" + task.getTaskIcon() + "][" + task.getStatusIcon() + "] "
                + task.getDescription());
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param taskList The list of tasks.
     */
    public static void printTaskCount(List<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * The main method of the November chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        beginSegment();
        System.out.println(INIT_SENTENCE);
        endSegment();

        List<Task> taskList = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        // Continue reading user input until the exit command is entered.
        while (!input.equals(EXIT_COMMAND)) {
            String[] sentence = {input, input};
            String command = input;
            String details = input;

            // Split the input into command and details if applicable.
            if (input.contains(" ")) {
                sentence = input.split(" ", 2);
                command = sentence[0];
                details = sentence[1];
            }

            switch (command) {
            case MARK_COMMAND:
                // Marks a task as complete.
                int markIndex = Integer.parseInt(sentence[1]) - 1;
                taskList.get(markIndex).setComplete();
                beginSegment();
                System.out.print(MARK_SENTENCE + System.lineSeparator() + "  ");
                printTask(taskList.get(markIndex));
                endSegment();
                break;
            case UNMARK_COMMAND:
                // Marks a task as incomplete.
                int unmarkIndex = Integer.parseInt(sentence[1]) - 1;
                taskList.get(unmarkIndex).setIncomplete();
                beginSegment();
                System.out.print(UNMARK_SENTENCE + System.lineSeparator() + "  ");
                printTask(taskList.get(unmarkIndex));
                endSegment();
                break;
            case LIST_COMMAND:
                // Prints a list of all tasks, indicating their completion status.
                beginSegment();
                printTaskList(taskList);
                endSegment();
                break;
            case TODO_COMMAND:
                // Adds a new todo task to the task list.
                Todo todoTask = new Todo(details);
                taskList.add(todoTask);
                beginSegment();
                todoTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            case DEADLINE_COMMAND:
                // Adds a new deadline task to the task list.
                String deadlineDescription = details.substring(0, details.indexOf(DEADLINE));
                String by = details.substring(details.indexOf(DEADLINE) + DEADLINE.length());
                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskList.add(deadlineTask);
                beginSegment();
                deadlineTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            case EVENT_COMMAND:
                // Adds a new event task to the task list.
                String eventDescription = details.substring(0, details.indexOf(EVENT_START));
                String start = details.substring(details.indexOf(EVENT_START) + EVENT_START.length(),
                        details.indexOf(EVENT_END));
                String end = details.substring(details.indexOf(EVENT_END) + EVENT_END.length());
                Event eventTask = new Event(eventDescription, start, end);
                taskList.add(eventTask);
                beginSegment();
                eventTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            default:
                // Echoes back unrecognized input.
                beginSegment();
                System.out.println(input);
                endSegment();
                break;
            }

            input = scan.nextLine();
        }

        beginSegment();
        System.out.println(EXIT_SENTENCE);
        endSegment();
    }
}
