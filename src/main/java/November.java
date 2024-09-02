import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * November is a command-line chatbot.
 */
public class November {

    /**
     * Prints a long line of underscores to mark the start of a print segment.
     */
    public static void beginSegment() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a long line of underscores and newline to mark the end of a print segment.
     */
    public static void endSegment() {
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints a numbered list of tasks with a completion status indicator.
     *
     * @param taskList The list of tasks to print, each task displaying its completion status.
     */
    public static void printTaskList(List<Task> taskList) {
        int index = 0;
        System.out.println("Here are the tasks in your list:");
        while(index < taskList.size()){
            System.out.print(index + 1 + ".");
            printTask(taskList.get(index));
            index++;
        }
    }

    public static void printTask(Task task) {
        System.out.println("[" + task.getTaskIcon() + "][" + task.getStatusIcon() + "] " + task.getDescription());
    }

    public static void printAddedTask(List<Task> taskList) {
        System.out.print("Got it. I've added this task:" + System.lineSeparator() + "  ");
        printTask(taskList.get(taskList.size() - 1));
    }

    public static void printTaskCount(List<Task> taskList) {
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Main method of the November chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        beginSegment();
        System.out.println("Hello! I'm November.");
        System.out.println("What can I do for you?");
        endSegment();

        List<Task> taskList = new ArrayList<>();

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while(!input.equals("bye")) {
            String[] sentences = {input, input};
            String firstWord = input;
            String sentence = input;
            if(input.contains(" ")) {
                sentences = input.split(" ", 2);
                firstWord = sentences[0];
                sentence = sentences[1];
            }

            switch (firstWord) {
            case "mark":
                // Marks a task as complete.
                int markIndex = Integer.parseInt(sentences[1]) - 1;
                taskList.get(markIndex).setComplete();
                beginSegment();
                System.out.print("Nice! I've marked this task as done: " + System.lineSeparator() + "  ");
                printTask(taskList.get(markIndex));
                endSegment();
                break;
            case "unmark":
                // Marks a task as incomplete.
                int unmarkIndex = Integer.parseInt(sentences[1]) - 1;
                taskList.get(unmarkIndex).setIncomplete();
                beginSegment();
                System.out.println("Ok, I've marked this task as not done yet: " + System.lineSeparator() + "  ");
                printTask(taskList.get(unmarkIndex));
                endSegment();
                break;
            case "list":
                // Prints a list of all tasks, indicating their completion status.
                beginSegment();
                printTaskList(taskList);
                endSegment();
                break;
            case "todo":
                Todo todoTask = new Todo(sentence);
                taskList.add(todoTask);

                beginSegment();
                todoTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            case "deadline":
                String deadlineDescription = sentence.substring(0, sentence.indexOf("/by "));
                String by = sentence.substring(sentence.indexOf("/by ") + 4);

                Deadline deadlineTask = new Deadline(deadlineDescription, by);
                taskList.add(deadlineTask);

                beginSegment();
                deadlineTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            case "event":
                String eventDescription = sentence.substring(0, sentence.indexOf("/from "));
                String start = sentence.substring(sentence.indexOf("/from ") + 6, sentence.indexOf("/to "));
                String end = sentence.substring(sentence.indexOf("/to ") + 4);

                Event eventTask = new Event(eventDescription, start, end);
                taskList.add(eventTask);

                beginSegment();
                eventTask.printTask();
                printTaskCount(taskList);
                endSegment();
                break;
            default:
                beginSegment();
                System.out.println("Error: Unrecognized input");
                endSegment();
                break;
            }
            input = scan.nextLine();
        }

        beginSegment();
        System.out.println("Bye! Hope to see you again soon!");
        endSegment();
    }
}