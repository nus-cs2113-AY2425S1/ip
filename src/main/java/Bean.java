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


    private static Task[] toDoList = new Task[MAX_LIST_COUNT];

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

    // Print (single line) message with separator line above and below message, as well as indentation
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
            System.out.println(INDENT + (i + 1) + ". " + toDoList[i].toString());
        }
        System.out.println(SEPARATOR_LINE);
    }

    public static void markTaskAsDone (Task[] toDoList, int taskNum) {
        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(true);
        // Confirmation message
        printFormattedReply("Task " + taskNum + " has been marked as DONE:\n" +
                INDENT + INDENT + toDoList[taskIndex].toString());
    }

    public static void unmarkTaskAsDone (Task[] toDoList, int taskNum) {
        int taskIndex = taskNum - 1;
        toDoList[taskIndex].setStatus(false);
        // Confirmation message
        printFormattedReply("Task " + taskNum + " has been marked as UNDONE:\n" +
                INDENT + INDENT + toDoList[taskIndex].toString());
    }

    public static void addToDo (Task[] toDoList, String userInput) {
        // Extract description
        String description = userInput.split("todo ")[1].trim();
        toDoList[Task.getNumberOfTasks()] = new Todo(description);
    }

    public static void addDeadline (Task[] toDoList, String userInput) {
        // Extract description and by
        String[] parts = userInput.split("/by ");
        // parts: [0] = "deadline {description} ", [1] = " {by}"
        String description = parts[0].substring("deadline ".length()).trim();
        String by = parts[1].trim();

        toDoList[Task.getNumberOfTasks()] = new Deadline(description, by);
    }

    public static void addEvent (Task[] toDoList, String userInput) {
        // Extract description, from and to
        String[] splitDescription = userInput.split("/from ");
        // splitDescription: [0] = "event {description} ", [1] = "{from} /to {to}"
        String description = splitDescription[0].substring("events".length()).trim();
        String[] splitFromTo = splitDescription[1].split("/to ");
        // splitFromTo: [0] = "{from} ", [1] = "{to}"
        String from = splitFromTo[0].trim();
        String to = splitFromTo[1].trim();

        toDoList[Task.getNumberOfTasks()] = new Event(description, from, to);
    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        greet();

        while (Task.getNumberOfTasks() < MAX_LIST_COUNT) {
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                // To exit
                break;

            } else if (userInput.equals("list")) {
                printList(toDoList);

            } else if (userInput.startsWith("mark")) {
                // Obtain task number by taking second word of input and trim any spaces then parse as int
                String[] words = userInput.split(" ");
                int taskNum = Integer.parseInt(words[1].trim());
                markTaskAsDone(toDoList, taskNum);

            } else if (userInput.startsWith("unmark")) {
                // Obtain task number by taking second word of input and trim any spaces then parse as int
                String[] words = userInput.split(" ");
                int taskNum = Integer.parseInt(words[1].trim());
                unmarkTaskAsDone(toDoList, taskNum);

            } else if (userInput.startsWith("todo")) {
                addToDo(toDoList, userInput);

            } else if (userInput.startsWith("deadline")) {
                addDeadline(toDoList, userInput);

            } else if (userInput.startsWith("event")) {
                addEvent(toDoList, userInput);

            } else {
                printFormattedReply("Sorry, I am not equipped to respond to that yet... :(");
            }
        }
        exit();
    }
}