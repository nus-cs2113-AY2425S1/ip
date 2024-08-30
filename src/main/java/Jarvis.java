import java.util.Scanner;


public class Jarvis {
    // Constants
    static final int MAX_TASK_LENGTH = 100; // Maximum length of a string

    private static final String chatBotName = "Jarvis"; // Name of the chatbot

    private static Task[] taskList = new Task[100]; // Array to store tasks


    /**
     * Prints a break line to the console.
     */
    private static void printBreakLine() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    /**
     * Gracefully exits the program.
     *
     * @param status
     */
    private static void exit(int status) {
        System.exit(status);
    }

    /**
     *
     */
    private static void printPrompt() {
        System.out.print("  ");
    }

    /**
     * Prints the greeting messages to the console.
     */
    private static void printGreetingMsgs() {
        String[] greetings = {"Hello! I'm " + chatBotName + "\nWhat can I do for you?"}; // List of greetings

        // Print the greetings
        for (String greeting : greetings) {
            printBreakLine(); // Print a break line before each greeting
            System.out.println(greeting); // Print the greeting
        }
        printBreakLine();
    }

    private static void printGoodbyeMsgs() {
        String[] goodbyes = {"Bye. Hope to see you again soon!"}; // List of goodbye messages

        // Print the goodbye messages
        for (String goodbye : goodbyes) {
            printBreakLine();
            System.out.println(goodbye); // Print the goodbye message
        }
        printBreakLine();
        exit(0);
    }

    public static void printTasks() {
        printBreakLine();
        if (Task.getNumberOfTasks() == 0) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getNumberOfTasks(); i++) {
                System.out.print((i + 1) + ".");
                taskList[i].printTask();
            }
        }
        printBreakLine();
    }

    public static void addTask(String item) {
        printBreakLine();
        System.out.println("Added: " + item);
        if (Task.getNumberOfTasks() < MAX_TASK_LENGTH) {
            taskList[Task.getNumberOfTasks()] = new Task(item);
        } else {
            System.out.println("The list is full. Please remove some items before adding more.");
        }

        printBreakLine();

    }

    public static void readInput(Scanner in, String lineBufferString) {

        try (in) {
            printPrompt();
            lineBufferString = in.nextLine();
            String command;
            int dividerPosition = 0;

            if (lineBufferString.contains(" ")) {
                dividerPosition = lineBufferString.indexOf(" ");
                command = lineBufferString.substring(0, dividerPosition);
            } else {
                command = lineBufferString;
            }

            switch (command) {
            case "bye":
                printGoodbyeMsgs();
                break;
            case "list":
                printTasks();

                break;
            case "mark":
                int taskNumber = Integer.parseInt(lineBufferString.substring(dividerPosition + 1));
                taskList[taskNumber - 1].markAsDone();
                break;
            case "unmark":
                taskNumber = Integer.parseInt(lineBufferString.substring(dividerPosition + 1));
                taskList[taskNumber - 1].markAsUndone();
                break;
            case "":
                System.out.println("You did not enter anything. Please try again.");
                break;
            default:
                addTask(lineBufferString);

                break;
            }

            readInput(in, lineBufferString);

            // if (lineBufferString.isEmpty()) {
            //     System.out.println("You did not enter anything. Please try again.");
            //     return;
            // } else if (lineBufferString.equalsIgnoreCase("bye")) {
            //     printGoodbyeMsgs();
            // } else if (lineBufferString.equalsIgnoreCase("list")) {
            //     printTasks();
            //     readInput(in, lineBufferString);
            // } else {
            //     addTask(lineBufferString);
            //     readInput(in, lineBufferString);
            // }
        } catch (Exception e) {
            System.err.println("An error occurred. Please try again.");
        }
    }

    public static void main(String[] args) {
        String logo = """
                                  @@@@@@@@@@@@@@@@@@@@@@@
                              @@%     @@          @@      @@@
                           @@         @@          @@          @@
                        @@            @@          @@             @@
                      @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                     @                                              *@
                    @@@                                             @@@
                   @  @@@                                         @@@  @
                  @&    @@         @@@@@@@@@@@@@@@@@@@@@         @@.   @@
                  @      @@@        @@              @@         @@@      @
                  @       *@@         @@           @(         @@(       @
                  @       ,@@@@        @@        @@         @@@@        @
                  @   @@@@@  %@@         @@     @          @@& @@@@@    @
                  @@@@         @@@        /@  @@         @@@       *@@@@/
                   @@           @@@         @@          @@@           @@
                    @@            @@&                 (@@            @@
                      @       @@@@@@@@               @@@@@@@        @
                       @@ @@@@*      @@,            @@      @@@@  @&
                          @@          @@@         @@@          @@
                            @@@         @@       @@         @@#
                                @@@@     @@@   @@@     @@@@
                                       @@@@@@@@@@@@@

                     ██╗    █████╗    ██████╗   ██╗   ██╗  ██╗   ███████╗
                     ██║   ██╔══██╗   ██╔══██╗  ██║   ██║  ██║   ██╔════╝
                     ██║   ███████║   ██████╔╝  ██║   ██║  ██║   ███████╗
                ██   ██║   ██╔══██║   ██╔══██╗  ╚██╗ ██╔╝  ██║   ╚════██║
                ╚█████╔╝██╗██║  ██║██╗██║  ██║██╗╚████╔╝██╗██║██╗███████║██╗
                 ╚════╝ ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝ ╚═══╝ ╚═╝╚═╝╚═╝╚══════╝╚═╝
                                  """;

        String lineBufferString = ""; // Buffer to store the input from the user
        Scanner in = new Scanner(System.in); // Scanner object to read input from the user

        System.out.println("Hello from\n" + logo);
        printGreetingMsgs();
        readInput(in, lineBufferString);

    }
}
