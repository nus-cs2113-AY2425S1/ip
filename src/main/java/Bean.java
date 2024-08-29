import java.util.Scanner;

public class Bean {
    // Constants
    private final static String SEPARATOR_LINE = "____________________________________________________________________\n";
    private final static String INDENT = "  ";
    private final static int MAX_LIST_COUNT = 100;
    private final static String LOGO = "  ┏━┓\n" +
            "  ┃ ┃\n" +
            "  ┃ ┗━━┳━━━┳━━━━┳━━━┓\n" +
            "  ┃  ┏┓┃ ┃━┫ ┏┓ ┃ ┏┓ ┓ ┏━━━━━━┓\n" +
            "  ┃  ┗┛┃ ┃━┫ ┏┓ ┃ ┃┃ ┃ ┃• ᴗ • ┫\n" +
            "  ┗━━ ━┻━━━┻━┛┗━┻━┛┗━┛ ┗━━━━━━┛\n";;

    // Print logo with greeting message
    public static void greet() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Howdy, mate! :) I am Bean, your personal assistant.\n" +
                INDENT + "Let me know how I can help you out!\n" +
                SEPARATOR_LINE
        );
    }

    // Print logo with exit message
    public static void exit() {
        System.out.println(SEPARATOR_LINE +
                LOGO +
                INDENT + "Bye bye, glad I could help! See you soon? :'(\n" +
                SEPARATOR_LINE);
    }

    public static void echo(String string) {
        System.out.println(INDENT + string + SEPARATOR_LINE);
    }

    // Print message with separator line above and below message, as well as indentation
    public static void printFormattedReply(String reply) {
        System.out.println(SEPARATOR_LINE +
                INDENT + reply + "\n" +
                SEPARATOR_LINE);
    }

    public static void printList(Task[] toDoList) {
        if (toDoList[0] == null) {
            printFormattedReply("Nothing in your TO DO LIST");
            return;
        }

        System.out.println(SEPARATOR_LINE +
                INDENT + "TO DO LIST:\n");

        for (int i = 0; i < toDoList.length; i++) {
            if (toDoList[i] == null) {
                break;
            }
            System.out.println(INDENT + (i + 1) + ".[" + toDoList[i].getStatusIcon() + "]  " + toDoList[i].description);
        }
        System.out.println(SEPARATOR_LINE);
    }

    public static void unmarkTaskAsDone (Task[] toDoList, int taskNum) {
        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(false);
        // Confirmation message
        printFormattedReply("Task " + taskNum + " '" + toDoList[taskIndex].description + "' has been marked as UNDONE");
    }

    public static void markTaskAsDone (Task[] toDoList, int taskNum) {
        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(true);
        // Confirmation message
        printFormattedReply("Task " + taskNum + " '" + toDoList[taskIndex].description + "' has been marked as DONE");
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        Task[] toDoList = new Task[MAX_LIST_COUNT];

        greet();

        while (Task.getNumberOfTasks() < MAX_LIST_COUNT) {
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                // To exit
                break;
            } else if (userInput.equals("list")) {
                printList(toDoList);
            } else if (userInput.contains("mark")) { //Either mark or unmark
                // Obtain task number by taking second word of input and trim any spaces then parse as int
                String[] words = userInput.split(" ");
                int taskNum = Integer.parseInt(words[1].trim());

                if (userInput.contains("unmark")) {
                    unmarkTaskAsDone(toDoList, taskNum);
                } else { // "mark"
                    markTaskAsDone(toDoList, taskNum);
                }
            } else {
                // Add task
                toDoList[Task.getNumberOfTasks()] = new Task(userInput);

                // Confirmation message
                printFormattedReply("Task '" + userInput + "' has been added!");
            }
        }
        exit();
    }
}