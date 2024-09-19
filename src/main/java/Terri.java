import java.util.Scanner;
import java.util.Arrays;


public class Terri {
    final static int MAXTASKS = 100;


    public static void main(String[] args) {
        String logo = "\n" +
                "$$$$$$$$\\                               $$\\ \n" +
                "\\__$$  __|                              \\__|\n" +
                "   $$ |    $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ \n" +
                "   $$ |   $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |\n" +
                "   $$ |   $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |\n" +
                "   $$ |   $$   ____|$$ |      $$ |      $$ |\n" +
                "   $$ |   \\$$$$$$$\\ $$ |      $$ |      $$ |\n" +
                "   \\__|    \\_______|\\__|      \\__|      \\__|\n" +
                "                                            \n";

        System.out.println(logo);

        System.out.println("Heya! I'm Terri.");

        printInstructions();

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Initialise Task array and counter
        Task[] taskList = new Task[MAXTASKS];
        int taskCounter = 0;

        // Continually check for user input
        while (true) {
            String userInput = scanner.nextLine();
            printDivider();

            // Isolate individual keywords in user input

            String[] keyWord = userInput.split(" ");

            // Define behaviour depending on initial keyword
            switch (keyWord[0]) {
                // Short-circuit check for exit input
                case "bye":
                    System.out.println("Bye then! See ya soon!\n");
                    printDivider();
                    scanner.close();
                    return;
                // List all tasks with completion indicators
                case "list":
                    TaskList.listTasks();
                    break;
                // Mark tasks complete
                case "mark":
                    TaskList.markDone(Integer.parseInt(keyWord[1]) - 1);
                    break;
                // Mark task not complete
                case "unmark":
                    TaskList.markNotDone(Integer.parseInt(keyWord[1]) - 1);
                    break;
                case "deadline":
                    TaskList.handleDeadline(keyWord);
                    break;
                case "event":
                    TaskList.handleEvent(keyWord);
                    break;
                case "todo":
                    TaskList.addToDo(userInput);
                    break;

                // Create new ToDo
                default:
                    TaskList.addToDo(userInput);
            }
        }
    }

    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    // Print program instructions
    private static void printInstructions() {
        System.out.println("I can log a ton of different tasks for you " +
                "use different keywords followed by a description to tell me what to do:");

        printDivider();

        System.out.println("KEYWORDS:");
        System.out.println("'todo' + (description) : logs a task with no specific due date");
        System.out.println("'deadline' + (description) + '/by (due date)' : logs a task due " +
                "at a specific point in time");
        System.out.println("'event' +  (description) + '/from (start time)' + '/to (end time)' : " +
                "logs an event occurring during a specified period");
        System.out.println("If I don't see one of the above keywords, " +
                "I'll just assume you're inputting a ToDo!");

        printDivider();

        System.out.println("UTILITY:");
        System.out.println("'list' : Generates a list of all currently logged tasks/events, " +
                "ordered oldest-newest. The number before each task is it's 'task number'");

        System.out.println("'mark' + '(task number)' : Records that a task has been completed");
        System.out.println("'unmark' + '(task number)' : Records that a task has not been completed");

        printDivider();

        System.out.println("So - what can I help you with today?");
    }

    // Return the contents of a subarray as a concatenated string
    public static String extractSubArray(String[] array, int start, int end) {
        return String.join(" ", Arrays.copyOfRange(array, start, end)).trim();
    }




}
