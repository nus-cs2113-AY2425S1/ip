import javax.lang.model.type.NullType;
import java.nio.file.LinkPermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * November is a command-line chatbot.
 */
public class November {

    /**
     * Prints a long line of underscores to mark the start of a print segment
     */
    public static void beginSegment() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a long line of underscores and newline to mark the end of a print segment
     */
    public static void endSegment() {
        System.out.println("____________________________________________________________");
        System.out.println();
    }

    /**
     * Prints a numbered list of tasks with completion status indicator.
     */
    public static void printTaskList(List<Task> taskList) {
        int index = 0;
        while(index < taskList.size()){
            System.out.println(index+1 + ".[" + taskList.get(index).getStatusIcon() + "] "
                    + taskList.get(index).getDescription());
            index++;
        }
    }

    /**
     * Main method of the November chatbot.
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
            String[] sentence = input.split(" ", 2);
            String firstWord = sentence[0];
            switch (firstWord) {
            case "mark":
                // Marks a task as complete.
                int markIndex = Integer.parseInt(sentence[1]) - 1;
                taskList.get(markIndex).setComplete();
                beginSegment();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [" + taskList.get(markIndex).getStatusIcon() + "] "
                        + taskList.get(markIndex).getDescription());
                endSegment();
                break;
            case "unmark":
                // Marks a task as incomplete.
                int unmarkIndex = Integer.parseInt(sentence[1]) - 1;
                taskList.get(unmarkIndex).setIncomplete();
                beginSegment();
                System.out.println("Ok, I've marked this task as not done yet: ");
                System.out.println("  [" + taskList.get(unmarkIndex).getStatusIcon() + "] "
                        + taskList.get(unmarkIndex).getDescription());
                endSegment();
                break;
            case "list":
                // Prints a list of all tasks, indicating their completion status.
                beginSegment();
//                System.out.println("Here are the tasks in your list:");
                printTaskList(taskList);
                endSegment();
                break;
            default:
                // Creates a new Task and adds it to the task list.
                taskList.add(new Task(input));
                // Prints a statement indicating that the new task has been added.
                beginSegment();
                System.out.println("added: " + input);
                endSegment();
                break;
            }
            input = scan.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}