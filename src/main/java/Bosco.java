import java.util.Scanner;

public class Bosco {
    private static final String DIVIDER = "\t____________________________________________________________";
    private static final int MAX_TASKS = 100;

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    private static void printWelcomeMessage() {
        System.out.println(DIVIDER);
        System.out.println("\t Hello! I'm Bosco APD." + System.lineSeparator() + "\t What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void printExitMessage() {
        System.out.println(DIVIDER);
        System.out.println("\t Bye! Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String userInput;
        String[] words;
        Task selectedTask;

        printWelcomeMessage();

        while (true) {
            userInput = in.nextLine();
            words = userInput.split(" ");
            if (words[0].equals("bye")) {
                break;
            }
            System.out.println(DIVIDER);
            switch (words[0]) {
            case "list":
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t " + (i + 1) + "." + tasks[i]);
                }
                break;
            case "mark":
                selectedTask = tasks[Integer.parseInt(words[1]) - 1];
                selectedTask.markAsDone();
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t   " + selectedTask);
                break;
            case "unmark":
                selectedTask = tasks[Integer.parseInt(words[1]) - 1];
                selectedTask.markAsNotDone();
                System.out.println("\t OK, I've marked this task as not done yet:");
                System.out.println("\t   " + selectedTask);
                break;
            default:
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("\t added: " + userInput);
                break;
            }
            System.out.println(DIVIDER);
        }

        printExitMessage();
    }
}
